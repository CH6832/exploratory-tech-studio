<?php
/**
* Homepage: Entrypoint of the website.
*/

require_once "include/include_header.php";
require_once "classes/Database.php";

printf( "<!DOCTYPE html>

	<!-- header: Include CSS files and necessary bootstrap files form the web --> 
	<head>
		<title>Travel agency Starlines</title>
		<meta charset='UTF-8'>" );
		require_once "include/include_bootstrap.php";
		printf( "<link rel='stylesheet' href='css/blink.css'>
	</head>
	<!-- /header -->

	<!-- body: Contains the Greeting text, name of travel agency and additional offers -->
	<body >
		<br><br><br>
		<h1 style='text-align:center;'>Welcome to the website of the<br> <i>Travel agency Starlines</i></h1>
		<br>
		<p style='text-align:center;'>We offer travel packages for young and old, for the well-heeled and the middle class, but also for adventure enthusiasts.</p>
		<p style='text-align:center;'>Browse through our range of well put together travel offers and experience an unforgettable vacation.</p>
		<br><br>
		<!-- Pictures: Click on pictures to get to further offers from various travel agencies -->
		<div class='d-flex justify-content-center row'>
		<p style='text-align:left;font-size:40px'>Further<br> offers: </p>
		<div class='d-flex justify-content-center col-lg-3 col-md-4 col-xs-4 thumb'>
			<a class='thumbnail' href='https://www.ab-ins-blaue.at/urlaub/tuerkei/lastminute.html?gclid=EAIaIQobChMIt47KuuSg8gIVB-rtCh35yAljEAAYASAAEgK01PD_BwE'>
				<img class='img-responsive' src='images/abinsblaue.png' alt=''>
			</a>
		</div>
		<div class='d-flex justify-content-center col-lg-3 col-md-4 col-xs-4 thumb'>
			<a class='thumbnail' href='https://www.tui.at/last-minute/'>
				<img class='img-responsive' src='images/tuidiscoveryoursmile.png' alt=''>
			</a>
		</div>
		<div class='d-flex justify-content-center col-lg-3 col-md-4 col-xs-4 thumb'>
			<a class='thumbnail' href='https://globus-reisebuero.de/site/startseite/'>
				<img class='img-responsive' src='images/globusreisebuero.png' alt=''>
			</a>
		</div>
		<!-- /Pictures -->" );
		// require_once "include/include_footer.php";

	printf( "</body>
	<!-- /body --> 

</html>" );
?>