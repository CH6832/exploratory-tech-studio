<?php

// src/Controller/CatalogController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;

class CatalogController extends AbstractController
{
    #[Route('/catalog', name: 'catalog_index')]
    public function showCatalog(ParameterBagInterface $params): Response
    {
        $catalogIndexConfig = $params->get('catalog_index');

        // Example categories data - replace this with your actual data source
        $foodCategories = [
            'Fruits' => ['Apples', 'Bananas', 'Oranges'],
            'Vegetables' => ['Carrots', 'Broccoli', 'Spinach'],
        ];

        return $this->render('catalog_index.html.twig', [
            'page' => $catalogIndexConfig,
            'food_categories' => $foodCategories,
        ]);
    }
}

?>