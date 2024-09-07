<?php
/**
 * file: connect_to_db.inc
 * reason: Establish a connection to a MySQL database. Name of a
 *        file with the database variables is passed to the function.
 */
function connect_to_db($filename)
{
    include($filename);
    $connection = mysqli_connect($host,$user,$passwd) or die ('No connection to server');
    $db = mysqli_select_db($connection,$database) or die ("Databse not available.");
    return $connection;
}
?>