<?php
/**
 * Die Klasse bietet Funktionen für die Datenbank an.
 */

class Database
{
    // private Variablen
    private $host; // Name des Hosts
    private $db; // Name der Datenbank
    private $user; // Name des Benutzers
    private $pwd; // Passwort des Nutzers. Kann über XAMPP gesetzt werden.
    private $con; // Verbindung
    private $port; // Standard port für MySQL (XAMPP Control Panel)

    /**
     * Initialisierung mit Parametern um eine Datenbankverbindung aufzubauen
     */
    function __construct($host, $db, $user, $pwd, $port = 3306, $autoconnect = true)
    {
        $this->host = $host;
        $this->db = $db;
        $this->user = $user;
        $this->pwd = $pwd;
        $this->port = $port;
        if ($autoconnect) {
            $this->connect_to_database();
        }
    }

    /**
     * Verbindung zur Datenbank herstellen
     */
    function connect_to_database()
    {
        $this->con = new mysqli($this->host, $this->user, $this->pwd, $this->db, $this->port);

        if ($this->con->connect_error) {
            die("Connection failed: " . $this->con->connect_error);
        }
    }

    /**
     * Abfrage ausführen
     */
    function sql_query($query)
    {
        if ($this->con->connect_errno) {
            die("Query failed: Connection is not open");
        }

        $result = $this->con->query($query);
        if (!$result) {
            die("Query failed: " . $this->con->error);
        }

        return $result;
    }

    /**
     * Verbindung schließen
     */
    function close_connection_to_database()
    {
        if ($this->con) {
            $this->con->close();
        }
    }
}
?>
