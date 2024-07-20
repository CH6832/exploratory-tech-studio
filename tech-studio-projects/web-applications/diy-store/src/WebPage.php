<?php
/**
 * Datei: WebPage.php
 * Zweck: Klasse speichert die zur Anzeige der Webseite
 *        erforderlichen Daten.
 */
class WebPage
{
    private $filename;
    private $data;

    function __construct($filename,$data=NULL)
    {
        if(is_string($filename))
        {
            $this->filename = $filename;
        }
        else
        {
            throw new Exception("filename has to be string.");
        }

        if($data == NULL or is_array($data))
        {
            $this->data = $data;
        }
        else
        {
            throw new Exception("Data must be transferred to an array.");
        }
    }

    function displayPage()
    {
        include("fields_summary-oo.inc");
        @extract($this->data);
        include($this->filename);
    }
}
?>