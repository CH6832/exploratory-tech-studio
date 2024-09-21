<?php

// src/Controller/ShippingController.php

namespace App\Controller;

use App\Service\StateService;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;

class ShippingController extends AbstractController
{
    private $stateService;
    private $session;
    private $shoppingCart;

    public function __construct(SessionInterface $session, ShoppingCart $shoppingCart, StateService $stateService)
    {
        $this->session = $session;
        $this->shoppingCart = $shoppingCart;
        $this->stateService = $stateService;
    }

    #[Route('/shipping', name: 'shipping')]
    public function index(Request $request): Response
    {
        // Define page and form data
        $page = [
            'title' => 'The DIY store: Shipping data',
            'top' => 'Place an order: Shipping data',
            'top2' => 'If you have any questions or problems, please contact admin@baumarkt.de',
            'bottom' => 'If you have any questions or problems, please contact admin@baumarkt.de',
        ];

        $shipInfo = [
            'email' => 'E-Mail Address',
            'vers_name' => 'First-/Lastname',
            'vers_strasse' => 'Street',
            'vers_ort' => 'City',
            'sta' => 'State',
            'vers_postl' => 'Zip code',
            'telefon' => 'Phone',
            'cc_type' => 'credit card type',
            'cc_number' => 'credit card number',
            'cc_exp' => 'Valid until'
        ];

        $ccTypes = [
            'visa' => 'Visa',
            'mc' => 'Mastercard',
            'amex' => 'American Express',
        ];

        $months = [
            1 => 'January', 2 => 'February', 3 => 'March', 4 => 'April', 5 => 'May',
            6 => 'June', 7 => 'July', 8 => 'August', 9 => 'September', 10 => 'October',
            11 => 'November', 12 => 'December'
        ];

        $today = time();
        $elements = ['submit' => 'Proceed'];
        
        // Handle form submission or load default data
        $data = [];
        if ($request->isMethod('POST')) {
            $data = $request->request->all();
        } else {
            // Load existing data (e.g., from the database or session)
            // Placeholder for demonstration
            $data = [
                'email' => '',
                'vers_name' => '',
                'vers_strasse' => '',
                'vers_ort' => '',
                'sta' => '',
                'vers_postl' => '',
                'telefon' => '',
                'cc_type' => '',
                'cc_number' => '',
                'cc_exp_mo' => '',
                'cc_exp_da' => '',
                'cc_exp_yr' => ''
            ];
        }

        return $this->render('shipping/index.html.twig', [
            'page' => $page,
            'ship_info' => $shipInfo,
            'cc_types' => $ccTypes,
            'months' => $months,
            'today' => $today,
            'elements' => $elements,
            'data' => $data
        ]);
    }

    #[Route('/shipping', name: 'shipping')]
    public function shipping(): Response
    {
        $stateCodes = $this->stateService->getStateCodes();
        $stateNames = $this->stateService->getStateNames();

        return $this->render('shipping.html.twig', [
            'state_codes' => $stateCodes,
            'state_names' => $stateNames,
        ]);
    }
}

?>