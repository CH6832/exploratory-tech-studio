<?php
/* 
 * file: fields_ship_info.inc
 * reason: Contains arrays with the field names and form elements for the shipping data
 */
$page = array(
    "title" => "The DIY store: Shipping data",
    "top" => "Place an order: Shipping data",
    "top2" => "If you have any questions or problems, please contact admin@baumarkt.de",
    "bottom" => "If you have any questions or problems, please contact admin@baumarkt.de"
);
$ship_info = array(
    "email" => "E-Mail Address",
    "vers_name" => "First-/Lastname",
    "vers_strasse" => "Street",
    "vers_ort" => "City",
    "sta" => "State",
    "vers_postl" => "Zip code",
    "telefon" => "Phone",
    "cc_type" => "credit card type",
    "cc_number" => "credit card number",
    "cc_exp" => "Valid until"
);
$cc_types = array(
    "visa" => "Visa",
    "mc" => "Mastercard",
    "amex" => "American Express",
);
$length = array(
    "email" => "55",
    "vers_name" => "40",
    "vers_strasse" => "55",
    "vers_ort" => "40",
    "vers_postl" => "10",
    "telefon" => "15",
    "cc_number" => "20"
);
$months = array(1 => "January", "February", "March", "April", "May",
 "June", "July", "August", "September", "October",
 "November", "December"
);
$today = time();
$elements = array("submit" => "Weiter");
$connect = connect_to_db("vars.inc");
$sql = "SELECT vers_name,vers_strasse,vers_ort,sta,vers_postl,telefon,email FROM bestellung WHERE bestell_nr='{$_SESSION['bestell_nr']}'";
$result = mysqli_query($connect,$sql) or die("Error: ".mysqli_error($connect));
$n = mysqli_num_rows($result);
if($n > 0)
{
    $row = mysqli_fetch_assoc($result);
    extract($row);
}
?>