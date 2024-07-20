<?php
/**
* This class provides functions to deal with the database.
*/

class Database
{
    // private variables
    private $host; # hostname
    private $db; # name of database

    private $user; # name of user
    private $pwd; # password of user. Can be set via XAMMP
    
    private $con; # Connection
    private $port = 3306; # default port for MySQL (XAMPP control panel)
  
    /**
    * Initialize with parameters to connect to the database
    */
    function __construct($host, $db, $user, $pwd, $port = 3306, $autoconnect = true)
    {
        $this->host = $host;
        $this->db = $db;
        $this->user = $user;
        $this->pwd = $pwd;
        $this->port = $port;
        if($autoconnect)
        {
            $this->open();
        } 
    }

    /**
    * Connect to db
    */
    function open()
    {
        $this->con = new mysqli($this->host, $this->db, $this->user, $this->pwd, $this->port);
    }
    /**
    * Insert statement 
    */
    function insert()
    {
        if ( empty( $table ) || empty( $data ) )
        {
            return false;
        }
        // Connect to the database
	    $db = $this->open();

		// Cast $data and $format to arrays
		$data = (array) $data;
		$format = (array) $format;

        // Build format string
        $format = implode('', $format); 
        $format = str_replace('%', '', $format);
        
        list( $fields, $placeholders, $values ) = $this->prep_query($data);

        // Prepend $format onto $values
        array_unshift($values, $format); 

        // Prepary our query for binding
        $stmt = $db->prepare("INSERT INTO {$table} ({$fields}) VALUES ({$placeholders})");

        // Dynamically bind values
        call_user_func_array( array( $stmt, 'bind_param'), $this->ref_values($values));
        
        // Execute the query
        $stmt->execute();
        
        // Check for successful insertion
        if ( $stmt->affected_rows ) {
            return true;
        }
        
        return false;
    }

    /**
    * Execute your query
    */
    function query( $query )
    {
        return $this->con->query( $query ); # &query is the sql statement
    }

    /**
     * Close connection
    */
    function close()
    {
        $this->con->close();
    }

    private function prep_query($data, $type='insert')
    {
        // Instantiate $fields and $placeholders for looping
        $fields = '';
        $placeholders = '';
        $values = array();
        
        // Loop through $data and build $fields, $placeholders, and $values			
        foreach ( $data as $field => $value )
        {
            $fields .= "{$field},";
            $values[] = $value;
            
            if ( $type == 'update')
            {
                $placeholders .= $field . '=?,';
            }
            else
            {
                $placeholders .= '?,';
            }
        }
        // Normalize $fields and $placeholders for inserting
        $fields = substr($fields, 0, -1);
        $placeholders = substr($placeholders, 0, -1);
        
        return array( $fields, $placeholders, $values );
	}
}
?>