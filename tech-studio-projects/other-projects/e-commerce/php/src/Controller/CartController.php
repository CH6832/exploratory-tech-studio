<?php

// src/Controller/CartController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CartController extends AbstractController
{
    #[Route('/cart-page', name: 'cart_page')]
    public function tablePage(Request $request): Response
    {
        // Placeholder data (you will replace this with actual data retrieval logic)
        $page = [
            'title' => 'The DIY Store - Cart',
            'top' => 'The DIY Store - Cart',
            'top2' => 'Your Shopping Cart',
        ];

        $tableHeaders = ["Pos.", "Kat.Nr.", "Name", "Quantity", "Price", "Total"];
        $items = [
            // Example data
            ['catalog_number' => '001', 'name' => 'Product 1', 'quantity' => 2, 'price' => 10.00],
            ['catalog_number' => '002', 'name' => 'Product 2', 'quantity' => 1, 'price' => 20.00]
        ];

        $orderTotal = 0;
        foreach ($items as $item) {
            $orderTotal += $item['quantity'] * $item['price'];
        }

        return $this->render('cart/table.html.twig', [
            'page' => $page,
            'table_headers' => $tableHeaders,
            'items' => $items,
            'order_total' => number_format($orderTotal, 2),
        ]);
    }
}

?>