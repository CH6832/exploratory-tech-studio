<?php

// src/Controller/ShoppingCartController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;

class ShoppingCartController extends AbstractController
{
    #[Route('/cart', name: 'shopping_cart')]
    public function showCart(ParameterBagInterface $params): Response
    {
        $shoppingCartConfig = $params->get('shopping_cart');

        // Example data - you would replace this with actual cart data
        $cartItems = [
            ['Pos.' => 1, 'Kat.Nr.' => '123', 'Bezeichnung' => 'Item 1', 'Menge' => 2, 'Preis' => 10.00, 'Gesamt' => 20.00],
            ['Pos.' => 2, 'Kat.Nr.' => '456', 'Bezeichnung' => 'Item 2', 'Menge' => 1, 'Preis' => 15.00, 'Gesamt' => 15.00],
        ];

        return $this->render('shopping_cart.html.twig', [
            'page' => $shoppingCartConfig,
            'cartItems' => $cartItems,
        ]);
    }
}

?>