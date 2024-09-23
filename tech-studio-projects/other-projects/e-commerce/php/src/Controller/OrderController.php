<?php

namespace App\Controller;

use App\Service\Item;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class OrderController extends AbstractController
{

    #[Route('/order-summary', name: 'order_summary')]
    public function summary(Request $request): Response
    {
        // Placeholder data (you will replace this with actual data retrieval logic)
        $page = [
            'title' => 'The DIY Store - Order',
            'top' => 'The DIY Store - Order',
            'top2' => 'Order summary',
            'bottom' => 'If you have any questions or problems, please contact admin@Baumarkt.de'
        ];

        $tableHeaders = ["Pos.","Kat.Nr.","Bezeichnung","Menge","Preis","Gesamt"];
        $orderNumber = $_SESSION['bestell_nr'] ?? 'N/A';
        $shippingRate = 0.25;
        $items = [
            // Example data
            ['katalog_nr' => '001', 'name' => 'Product 1', 'menge' => 2, 'preis' => 10.00, 'quantity' => 2, 'price' => 10.00],
            ['katalog_nr' => '002', 'name' => 'Product 2', 'menge' => 1, 'preis' => 20.00, 'quantity' => 1, 'price' => 20.00]
        ];

        $orderSubtotal = 0;
        foreach ($items as $item) {
            $orderSubtotal += $item['quantity'] * $item['price'];
        }

        $taxRate = ($ship_state ?? 'DE') == 'DE' ? 0.0700 : 0.00;
        $salesTax = $orderSubtotal * $taxRate;
        $shipping = $shippingRate * count($items);
        $orderTotal = $orderSubtotal + $salesTax + $shipping;

        return $this->render('order/summary.html.twig', [
            'page' => $page,
            'table_headers' => $tableHeaders,
            'order_number' => $orderNumber,
            'items' => $items,
            'order_subtotal' => number_format($orderSubtotal, 2),
            'sales_tax' => number_format($salesTax, 2),
            'shipping' => number_format($shipping, 2),
            'order_total' => number_format($orderTotal, 2),
        ]);
    }

    #[Route('/order/item/{catalogNumber}/{quantity}', name: 'order_item')]
    public function showItem(Item $item): Response
    {
        // Example: Create a new item (replace the parameters with actual values)
        $catalogNumber = '12345'; // Replace with dynamic value
        $quantity = 2;            // Replace with dynamic value

        // Create an item
        $item = new Item($this->get(Catalog::class), $catalogNumber, $quantity);

        // Display item details
        return new Response(
            '<html><body>' .
            'Item Name: ' . $item->getName() . '<br>' .
            'Catalog Number: ' . $item->getCatalogNumber() . '<br>' .
            'Quantity: ' . $item->getQuantity() . '<br>' .
            'Price: ' . $item->getPrice() . '<br>' .
            '</body></html>'
        );
    }
}

?>