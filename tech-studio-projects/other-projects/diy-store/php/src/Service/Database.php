<?php

namespace App\Service;

use Exception;
use mysqli;

class Database
{
    private mysqli $cxn;
    private string $databaseName;
    private string $host;
    private string $user;
    private string $password;

    public function __construct(string $host, string $user, string $password)
    {
        // Attempt to create a MySQL connection
        $this->cxn = new mysqli($host, $user, $password);

        if ($this->cxn->connect_errno) {
            throw new Exception("Database is not available. Please try again later.");
            // Optionally integrate Symfony Mailer here
            // $mailer->sendEmail('dbadmin@unseresite.de', 'DB-Problem', 'MySQL-Server does not respond: ' . $this->cxn->connect_error);
        }

        $this->host = $host;
        $this->user = $user;
        $this->password = $password;
    }

    public function useDatabase(string $dbname): bool
    {
        // Show all available databases
        $result = $this->cxn->query("SHOW DATABASES");

        if (!$result) {
            throw new Exception("Database is not available. Please try again later.");
            // Optionally send email here if desired
            // $mailer->sendEmail('dbadmin@unseresite.de', 'DB-Problem', 'MySQL-Server does not respond: ' . $this->cxn->error);
        }

        // Check if the specified database exists
        $databases = [];
        while ($row = $result->fetch_row()) {
            $databases[] = $row[0];
        }

        if (in_array($dbname, $databases) || in_array(strtolower($dbname), $databases)) {
            $this->databaseName = $dbname;
            $this->cxn->select_db($dbname);
            return true;
        } else {
            throw new Exception("Database $dbname not found.");
            return false;
        }
    }

    public function getConnection(): mysqli
    {
        return $this->cxn;
    }

    public function getDatabaseName(): string
    {
        return $this->databaseName;
    }
}

?>
