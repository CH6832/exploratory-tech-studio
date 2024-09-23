<?php

namespace App\Service;

use Exception;

class Item
{
    private string $catalogNumber;
    private int $quantity;
    private string $name;
    private float $price;
    private Catalog $catalog;

    public function __construct(Catalog $catalog, string $catalogNumber, int $quantity)
    {
        if (!is_numeric($quantity)) {
            throw new Exception("Quantity must be a valid number.");
        }

        $this->catalogNumber = $catalogNumber;
        $this->quantity = $quantity;
        $this->catalog = $catalog;

        // Assuming the "baumarkt" catalog needs to be selected
        $this->catalog->selectCatalog("baumarkt");

        // Get item details from the catalog
        $this->name = $this->catalog->getName($catalogNumber);
        $this->price = $this->catalog->getPrice($catalogNumber);
    }

    public function getCatalogNumber(): string
    {
        return $this->catalogNumber;
    }

    public function getQuantity(): int
    {
        return $this->quantity;
    }

    public function getPrice(): float
    {
        return $this->price;
    }

    public function getName(): string
    {
        return $this->name;
    }
}

?>