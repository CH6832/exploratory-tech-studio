<?php

namespace App\Service;

use Exception;
use mysqli;

class Order
{
    private string $orderNumber;
    private string $table;
    private mysqli $cxn;

    public function __construct(mysqli $cxn, string $table)
    {
        $this->cxn = $cxn;
        if (is_string($table)) {
            $this->table = $table;
        } else {
            throw new Exception("$table is not a valid table name.");
        }
    }

    public function createOrder(): void
    {
        $today = date("Y-m-d");
        $sql = "INSERT INTO $this->table (bestell_date) VALUES ('$today')";
        if ($this->cxn->query($sql)) {
            $this->orderNumber = $this->cxn->insert_id;
            $_SESSION['bestell_nr'] = $this->orderNumber;
        } else {
            throw new Exception("Database not available. Try again later.");
        }
    }

    public function getOrderNumber(): string
    {
        return $this->orderNumber;
    }

    public function addCart(ShoppingCart $cart): void
    {
        foreach ($cart->getAllItems() as $n => $item) {
            $catalogNo = $item->getCatalogNumber();
            $quantity = $item->getQuantity();
            $price = $item->getPrice();
            $sql = "INSERT INTO bestell_auftrag (bestell_nr, katalog_nr, menge, pos_nr, preis) VALUES ('$this->orderNumber', '$catalogNo', '$quantity', '".($n + 1)."', '$price')";
            $this->cxn->query($sql);
        }
    }

    public function selectOrder(int $orderNumber): void
    {
        $this->orderNumber = $orderNumber;
    }

    public function getOrderInfo(): array
    {
        $sql = "SELECT * FROM $this->table WHERE bestell_nr = '$this->orderNumber'";
        if ($result = $this->cxn->query($sql)) {
            return $result->fetch_assoc();
        } else {
            throw new Exception("Database not available. Try again later.");
        }
    }

    public function getItemInfo(): array
    {
        $sql = "SELECT pos_nr, katalog_nr, menge, preis FROM bestell_auftrag WHERE bestell_nr = '$this->orderNumber'";
        if ($result = $this->cxn->query($sql)) {
            $items = [];
            $n = 1;
            while ($row = $result->fetch_assoc()) {
                $items[$n] = $row;
                $cat = new Catalog("vars.inc");
                $cat->selectCatalog("baumarkt");
                $items[$n]['name'] = $cat->getName($row['katalog_nr']);
                $n++;
            }
            return $items;
        } else {
            throw new Exception("Database not available. Try again later.");
        }
    }

    public function updateOrderInfo(array $data): bool
    {
        if (!is_array($data)) {
            throw new Exception("Data must be an array!");
        }
        $dataArray = [];
        foreach ($data as $field => $value) {
            if (preg_match("/ship/", $field) || $field == "telefon" || $field == "email") {
                $dataArray[] = "$field='$value'";
            }
        }
        $sql = "UPDATE $this->table SET " . implode(',', $dataArray) . " WHERE bestell_nr = '$this->orderNumber'";
        if (!$this->cxn->query($sql)) {
            throw new Exception("Database not available. Try again later.");
        }
        return true;
    }

    public function displayOrderInfo(string $fieldInfo, string $fieldPage): void
    {
        $data = $this->getOrderInfo();
        $items = $this->getItemInfo();
        extract($data);
        if (is_string($fieldInfo) && is_string($fieldPage)) {
            include($fieldInfo);
            include($fieldPage);
        } else {
            throw new Exception("Parameters are not valid filenames.");
        }
    }
}

?>