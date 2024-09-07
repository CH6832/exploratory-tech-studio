<?php
/*
 * file: fields_summary.inc
 * reason: Generates the data required to display a summary
 *        of the arrays required for the order.
 */
$page = array(
    "title" => "The DIY Store - Order",
    "top" => "The DIY Store - Order",
    "top2" => "Order summary",
    "bottom" => "If you have any questions or problems, please contact admin@Baumarkt.de" 
);
$table_headers = array(
    "Pos.","Kat.Nr.","Bezeichnung","Menge","Preis","Gesamt"
);
$order_number = $_SESSION['bestell_nr'];
$shipping_rate = .25;
$table_name = $order_number;
?>