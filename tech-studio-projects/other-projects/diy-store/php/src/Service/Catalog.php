<?php

namespace App\Service;

use Exception;

class Catalog
{
    private Database $database;
    private string $catalogName;

    public function __construct(Database $database)
    {
        $this->database = $database;
    }

    public function selectCatalog(string $databaseName): void
    {
        if ($this->database->useDatabase($databaseName)) {
            $this->catalogName = $databaseName;
        }
    }

    public function getCategoriesAndTypes(): array
    {
        $sql = "SELECT DISTINCT kateins, katzwei FROM katalog ORDER BY kateins, katzwei";
        $result = $this->database->getConnection()->query($sql);

        if (!$result) {
            throw new Exception($this->database->getConnection()->error);
        }

        $arrayCatType = [];
        while ($row = $result->fetch_assoc()) {
            $arrayCatType[$row['kateins']][] = $row['katzwei'];
        }

        return $arrayCatType;
    }

    // Other methods will similarly use $this->database->getConnection()
}

?>