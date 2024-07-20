<?php
/**
 * This site displays all voyages from the table in database.
 */

require_once "include/include_header.php";
require_once "classes/Database.php";


printf( "<!DOCTYPE html>

	<!-- header: Include CSS files and necessary bootstrap files form the web -->
	<head>
		<title>Reisebüro Starlines</title>
		<meta charset='UTF-8'>" );
		require_once "include/include_bootstrap.php";
		printf( "<link rel='stylesheet' href='css/blink.css'>
	</head>
	<!-- /header -->

	<!-- body: Show travel offers in a table -->
	<body>
		<h1 style='text-align:center;'>Welcome to our offers</h1>
		<p style='text-align:center;'>Here you can see our entire range listed.</p>
		<div id='wrapper'>" );
			/**
			 * WICHTIG: Die Parameter müssen in folgender Reihenfolge
			 * angegeben werden: $HOST_NAME, %USER_NAME, $PASSWORD and $DATABASE_NAME.
			 */
			$reisebuero_db = new Database( 'localhost','reisebuero','christoph','hpotsirhc' ); # Neues Datenbank-Objekt initialisieren
			$result_of_query = $reisebuero_db->sql_query( 'SELECT * FROM reisen' ); # Abfrage 
			echo "<table border='1' style='margin-left:auto;margin-right:auto;'>";
			while( $row = mysqli_fetch_assoc( $result_of_query ) ) # Alle Spalten aus der Datenbank werden ausgegeben
			{
				echo '<tr>';
				echo '<td>'.$row['id'].'</td>';
				echo '<td>'.$row['bezeichnung'].'</td>';
				echo '<td>'.$row['gesamtpreis'].' €'.'</td>';
				echo '<td>'.$row['anreisedatum'].'</td>';
				echo '<td>'.$row['abreisedatum'].'</td>';
				echo '<td>'.$row['startflughafen'].'</td>';
				echo '<td>'.$row['uebernachtungsort'].'</td>';
				echo '<td>'.$row['hotelname'].'</td>';
				echo '<td>'.$row['reiseart'].'</td>';
				echo '</tr>';
			}
			echo '</table>';
			$reisebuero_db->close_connection_to_database(); # Verbindung zur Datenbank schließen
			// require_once 'include/include_footer.php';
		printf( "</div>
	</body>
	<!-- /body -->

</html>" );