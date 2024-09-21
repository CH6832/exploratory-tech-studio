<?php

// src/Service/ShoppingCart.php

namespace App\Service;

use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Service\Item;

class ShoppingCart
{
    private $items = [];
    private $message;
    private $n_items = 0;
    private $session;

    public function __construct(SessionInterface $session)
    {
        $this->session = $session;

        if ($this->session->has('items')) {
            $this->items = $this->session->get('items');
            $this->n_items = count($this->items);
        }

        $this->message = "Shopping cart contains {$this->n_items} item(s).";
    }

    public function addItem(Item $item)
    {
        $this->items[] = $item;
        $this->session->set('items', $this->items);
        $this->n_items++;
        $this->message = "Shopping cart contains {$this->n_items} item(s).";
    }

    public function getAllItems()
    {
        return $this->items;
    }

    public function getMessage()
    {
        return $this->message;
    }

    public function displayCart(string $file_fields, string $file_page): void
    {
        if (is_string($file_fields) && is_string($file_page)) {
            // You can render templates instead of including files directly
            // In Symfony, you will use a different approach to render HTML
            throw new \RuntimeException("Use Twig templates to render the cart.");
        } else {
            throw new \InvalidArgumentException("Two filenames needed.");
        }
    }

    public function updateCart(array $new_array)
    {
        if (is_array($new_array)) {
            $items_new = [];
            foreach ($new_array as $field => $value) {
                if (preg_match("/item/", $field) && $value > 0) {
                    $cat_no = substr($field, 4);
                    $items_new[] = new Item($cat_no, $value);
                }
            }
            $this->items = $items_new;
            $this->session->set('items', $this->items);
            $this->n_items = count($this->items);
            $this->message = "Shopping cart contains {$this->n_items} item(s).";
        } else {
            throw new \InvalidArgumentException("Parameter is not an array.");
        }
    }
}

?>