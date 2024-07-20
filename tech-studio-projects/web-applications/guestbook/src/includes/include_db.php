<?php
$dbHost="localhost";
$dbName="gaestebuch";
$dbCharset="utf8";
$dbUser="root";
$dbPw="";

try
{	
	$db=new PDO(
		"mysql:host=$dbHost;dbname=$dbName;charset=$dbCharset",
		$dbUser,
		$dbPw
		);

}
catch(PDOException $e)
{
	exit("Keine Verbindung zur Datenbank");
}
?>