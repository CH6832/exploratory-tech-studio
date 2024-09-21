<?php

// src/Controller/ProductController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;

class ProductController extends AbstractController
{
    #[Route('/product/{catalogNumber}', name: 'product_page')]
    public function showProduct($catalogNumber, ParameterBagInterface $params): Response
    {
        $productPageConfig = $params->get('product_page');

        // Example product data - replace this with your actual data source
        $product = [
            'katalog_nr' => $catalogNumber,
            'name' => 'Sample Product',
            'pr_beschr' => 'This is a sample product description.',
            'preis' => '19.99',
            'menge' => '50',
        ];

        return $this->render('product_page.html.twig', [
            'page' => $productPageConfig,
            'product' => $product,
        ]);
    }
}

?>