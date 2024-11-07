<?php

// src/Controller/OrderSummaryController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;

class OrderSummaryController extends AbstractController
{
    #[Route('/order-summary', name: 'order_summary')]
    public function orderSummary(ParameterBagInterface $params): Response
    {
        $summaryConfig = $params->get('order_summary');

        // Fetch order summary data from the database
        $orderNumber = $_SESSION['bestell_nr'];
        $shippingRate = $summaryConfig['shipping_rate'];
        $connect = $this->getDoctrine()->getConnection();
        $sql = "SELECT * FROM bestellung WHERE bestell_nr = :orderNumber";
        $result = $connect->executeQuery($sql, ['orderNumber' => $orderNumber]);
        $orderData = $result->fetchAssociative();

        return $this->render('order_summary.html.twig', [
            'page' => $summaryConfig,
            'order' => $orderData,
            'order_number' => $orderNumber,
            'shipping_rate' => $shippingRate
        ]);
    }
}

?>