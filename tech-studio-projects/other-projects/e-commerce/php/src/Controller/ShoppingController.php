<?php

// src/Controller/ShoppingController.php

namespace App\Controller;

use App\Service\Catalog;
use App\Service\Order;
use App\Service\ShoppingCart;
use App\Service\Database;
use App\Service\WebForm;
use App\Service\WebPage;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;

class ShoppingController extends AbstractController
{
    private $catalog;
    private $database;
    private $session;
    private $webForm;

    public function __construct(Catalog $catalog, Database $database, SessionInterface $session)
    {
        $this->catalog = $catalog;
        $this->database = $database;
        $this->session = $session;
        $this->webForm = $webForm;
    }

    #[Route('/shipping', name: 'shipping_info')]
    public function shippingInfo(ParameterBagInterface $params): Response
    {
        $shippingConfig = $params->get('shipping_info');

        // Fetch shipping data from the database
        $connect = $this->getDoctrine()->getConnection();
        $sql = "SELECT vers_name, vers_strasse, vers_ort, sta, vers_postl, telefon, email FROM bestellung WHERE bestell_nr='{$_SESSION['bestell_nr']}'";
        $result = $connect->executeQuery($sql);
        $shippingData = $result->fetchAssociative();

        return $this->render('shipping_info.html.twig', [
            'page' => $shippingConfig,
            'shipping' => $shippingData,
        ]);
    }

    #[Route('/catalog', name: 'show_catalog')]
    public function showCatalog(Request $request): Response
    {
        // Dummy data for demonstration
        $page = [
            'title' => 'Catalog Page',
            'top' => 'Welcome to Our Catalog!',
            'top2' => 'Select a category to view products.',
            'bottom' => 'Thank you for visiting our catalog.'
        ];

        $food_categories = [
            'Fruits' => ['Apples', 'Bananas', 'Oranges'],
            'Vegetables' => ['Carrots', 'Potatoes', 'Broccoli']
        ];

        return $this->render('catalog_index_page.html.twig', [
            'page' => $page,
            'food_categories' => $food_categories
        ]);
    }

    #[Route('/form', name: 'form')]
    public function showForm(Request $request): Response
    {
        $data = []; // Get data from request or initialize as needed
        $this->webForm->setFieldsNotRequired(['optional_field']);

        if ($request->isMethod('POST')) {
            $data = $request->request->all();
            $this->webForm->setData($data);

            $this->webForm->trimData();
            $this->webForm->stripTagsFromData();

            $blanks = $this->webForm->checkForBlanks();
            $errors = $this->webForm->verifyData();

            if ($blanks || $errors) {
                // Handle errors or blanks
                // Return form with errors
                return $this->webForm->displayForm();
            }
        }
    }

    #[Route('/shopping', name: 'shopping')]
    public function handleRequest(Request $request): Response
    {
        $post = $request->request->all();

        if (isset($post['Products']) && isset($post['interest'])) {
            try {
                $this->catalog->selectCatalog('baumarkt');
                $this->catalog->displayAllofType($post['interest'], 2);
            } catch (\Exception $e) {
                return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
            }
        } elseif (isset($post['Cart'])) {
            if ($post['Cart'] === 'Warenkorb aktualisieren') {
                try {
                    $this->shoppingCart->updateCart($post);
                } catch (\Exception $e) {
                    return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
                }
            } elseif ($post['Cart'] === 'In den Warenkorb') {
                try {
                    foreach ($post as $field => $value) {
                        if (preg_match("/item/", $field) && $value > 0) {
                            $catNo = substr($field, 4);
                            $item = new Item($catNo, $value);
                            $this->shoppingCart->addItem($item);
                        }
                    }
                } catch (\Exception $e) {
                    return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
                }
                
                try {
                    $cart = new ShoppingCart(); // Assuming ShoppingCart has a displayCart method
                    $cart->displayCart("fields_cart-oo.inc", "table_page-oo.inc");
                } catch (\Exception $e) {
                    return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
                }
            }
        } elseif (isset($post['Ship'])) {
            try {
                $this->database->useDatabase('baumarkt');
                $order = new Order($this->database->getConnection(), "bestellung");
                
                if ($this->session->has('bestell_nr')) {
                    $order->selectOrder($this->session->get('bestell_nr'));
                } else {
                    $order->createOrder();
                }
                
                $info = $order->getOrderInfo();
                $form = new WebForm("single_form.inc", "fields_ship_info.inc", $info);
                return $this->render('form.html.twig', ['form' => $form->displayForm()]);
            } catch (\Exception $e) {
                return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
            }
        } elseif (isset($post['Summary'])) {
            try {
                $form = new WebForm("single_form.inc", "fields_ship_info.inc", $post);
                $blanks = $form->checkForBlanks();

                if (is_array($blanks)) {
                    $message = "Die folgenden Felder sind leer. Bitte geben Sie die erforderlichen Daten ein: ";
                    foreach ($blanks as $value) {
                        $message .= "$value, ";
                    }
                    $form->trimData();
                    $form->stripTagsFromData();

                    try {
                        $errors = $form->verifyData();
                    } catch (\Exception $e) {
                        return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
                    }

                    if (is_array($errors)) {
                        $message .= implode("<br> ", $errors);
                        return $this->render('form.html.twig', ['form' => $form->displayForm(), 'message' => $message]);
                    }

                    try {
                        $order = new Order($this->database->getConnection(), "bestellung");
                        $order->selectOrder($this->session->get('bestell_nr'));
                        $order->updateOrderInfo($post);

                        $cart = new ShoppingCart();
                        $order->addCart($cart);

                        $order->displayOrderSummary("fields_summary-oo.inc", "summary_page.inc");
                    } catch (\Exception $e) {
                        return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
                    }
                }
            } catch (\Exception $e) {
                return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
            }
        } elseif (isset($post['Final'])) {
            try {
                $order = new Order($this->database->getConnection(), "bestellung");
                if ($post['Final'] === "Bestellung aufgeben") {
                    $order->setSubmitted();
                    $order->sendToShipping();
                    $order->sendEmailToCustomer();
                    $confirmPage = new WebPage("confirm_page.inc", $post);
                    return $this->render('page.html.twig', ['page' => $confirmPage->displayPage()]);
                } else {
                    $order->cancel();
                    $unappPage = new WebPage("not_accepted_page.inc", $post);
                    return $this->render('page.html.twig', ['page' => $unappPage->displayPage()]);
                }
            } catch (\Exception $e) {
                return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                $this->catalog->selectCatalog("baumarkt");
                return $this->render('catalog.html.twig', ['categories' => $this->catalog->displayCategories()]);
            } catch (\Exception $e) {
                return new Response($e->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
            }
        }

        return $this->webForm->displayForm();
    }

    public function render(string $view, array $parameters = [], ?Response $response = null): Response
    {
        // This is a placeholder; Symfony's actual render logic is handled by the AbstractController
        return parent::render($view, $parameters, $response);
    }
}

?>
