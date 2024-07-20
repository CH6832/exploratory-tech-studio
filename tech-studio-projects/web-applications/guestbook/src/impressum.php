<?php
/**
* Homepage: Entry point of the website.
*/
require_once "includes/bootstrap_header.php";
require_once "classes/Database.php"
?>

<!DOCTYPE html>

	<!-- header: Include CSS files and necessary bootstrap files form the web --> 
	<head>
		<title>Guestbook</title>
		<meta charset="UTF-8">
		<?php require_once "includes/include_bootstrap.php"; ?>
		<link rel="stylesheet" href="../resources/css/blink.css">
	</head>
	<!-- /header -->
    
	<!-- body: Contains the Greeting text, name of travel agency and additional offers -->
	<body style="width:auto">
        <div class="container">
            <div class="row">
                <div class="col"></div>
                <div class="col-14">
                    <br><br>
                    <h1 style='text-align:center;font-size:50px'>Impressum<br></h1>
                    <br>
                </div>
                <div class="col"></div>
            </div>
            <div class="row">
                <div class="col">
                </div>
                <div class="col-14">
                    Informationspflicht laut §5 E-Commerce Gesetz, §14 Unternehmensgesetzbuch, §63 Gewerbeordnung und Offenlegungspflicht laut §25 Mediengesetz.<br>
                    <br>
                    Gaestebuch GmbH<br>
                    Christoph Hartleb<br>
                    Musterstrasse 1, Stiege 2 Tür 3<br>
                    9876 Musterberg<br>
                    Österreich<br>
                    <br>
                    Unternehmensgegenstand: IT Dienstleistungen<br>
                    UID-Nummer: ATU12345678<br>
                    GLN: 1234567891234<br>
                    GISA: 12345678<br>
                    <br>
                    Tel.: 01234/56789<br>
                    Fax: 01234/56789-0<br>
                    E-Mail: <a href="mailto:office.gaestebuch.web@site.at">office.gaestebuch.web@site.at</a><br>
                    <br>
                    Mitglied bei: WKO, Landesinnung, etc.<br>
                    Berufsrecht: Gewerbeordnung <a href="https://www.ris.bka.gv.at/GeltendeFassung.wxe?Abfrage=Bundesnormen&Gesetzesnummer=10007517">www.ris.bka.gv.at</a><br>
                    <br>
                    Aufsichtsbehörde/Gewerbebehörde: Bezirkshauptmannschaft Musterhausen<br>
                    Berufsbezeichnung: Webdesigner, Grafiker<br>
                    Verleihungsstaat: Österreich<br>
                    <br>
                    <h5>Kontaktdaten des Verantwortlichen für Datenschutz</h5>
                    Sollten Sie Fragen zum Datenschutz haben, finden Sie nachfolgend die Kontaktdaten der verantwortlichen Person bzw. Stelle:<br>
                    Musterfirma GmbH<br>
                    Musterstraße 47, 12312 Musterstadt, Musterland<br>
                    Vertretungsberechtigt: Markus Mustermann<br>
                    E-Mail-Adresse: <a href="mailto:office@musterfirma.com">office@musterfirma.com</a><br>
                    Telefon: 01234/56789<br>
                    Impressum: https://www.testfirma.at/impressum/<br>
                    <br>
                    Quelle: Erstellt mit dem Impressum Generator von <a href="https://www.adsimple.at/datenschutz-generator/">AdSimple</a><br>
                    <br>
                    <h5>EU-Streitschlichtung</h5>
                    Gemäß Verordnung über Online-Streitbeilegung in Verbraucherangelegenheiten (<a href="https://eur-lex.europa.eu/legal-content/DE/TXT/?uri=celex:32013R0524">ODR-Verordnung</a>) möchten
                    wir Sie über die Online-Streitbeilegungsplattform (OS-Plattform) informieren.<br>
                    Verbraucher haben die Möglichkeit, Beschwerden an die Online Streitbeilegungsplattform der Europäischen Kommission unter <a href="http://ec.europa.eu/odr?tid=121795891">http://ec.europa.eu/odr?tid=121795891</a>
                    zu richten. Die dafür notwendigen Kontaktdaten finden Sie oberhalb in unserem Impressum.<br>
                    <br>
                    Wir möchten Sie jedoch darauf hinweisen, dass wir nicht bereit oder verpflichtet sind, an Streitbeilegungsverfahren vor einer Verbraucherschlichtungsstelle teilzunehmen.<br>
                    <br>
                    <h5>Haftung für Inhalte dieser Website</h5>
                    Wir entwickeln die Inhalte dieser Webseite ständig weiter und bemühen uns korrekte und aktuelle Informationen bereitzustellen.
                    Leider können wir keine Haftung für die Korrektheit aller Inhalte auf dieser Website übernehmen, speziell für jene, die seitens
                    Dritter bereitgestellt wurden. Als Diensteanbieter sind wir nicht verpflichtet, die von ihnen übermittelten oder gespeicherten
                    Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit hinweisen.<br>
                    <br>
                    Unsere Verpflichtungen zur Entfernung von Informationen oder zur Sperrung der Nutzung von Informationen nach den allgemeinen Gesetzen
                    aufgrund von gerichtlichen oder behördlichen Anordnungen bleiben auch im Falle unserer Nichtverantwortlichkeit davon unberührt.
                    <br>
                    Sollten Ihnen problematische oder rechtswidrige Inhalte auffallen, bitte wir Sie uns umgehend zu kontaktieren, damit wir
                    die rechtswidrigen Inhalte entfernen können. Sie finden die Kontaktdaten im Impressum.<br>
                    <br>
                    <h5>Haftung für Links auf dieser Webseite</h5>
                    Unsere Webseite enthält Links zu anderen Webseiten für deren Inhalt wir nicht verantwortlich sind. Haftung für
                    verlinkte Websites besteht für uns nicht, da wir keine Kenntnis rechtswidriger Tätigkeiten hatten und haben, uns
                    solche Rechtswidrigkeiten auch bisher nicht aufgefallen sind und wir Links sofort entfernen würden, wenn uns
                    Rechtswidrigkeiten bekannt werden.<br>
                    <br>
                    Wenn Ihnen rechtswidrige Links auf unserer Website auffallen, bitte wir Sie uns zu kontaktieren. Sie finden die Kontaktdaten im <a href="impressum.php">Impressum</a>.<br>
                    <br>
                    <h5>Urheberrechtshinweis</h5>
                    Alle Inhalte dieser Webseite (Bilder, Fotos, Texte, Videos) unterliegen dem Urheberrecht. Bitte fragen Sie uns
                    bevor Sie die Inhalte dieser Website verbreiten, vervielfältigen oder verwerten wie zum Beispiel auf anderen Websites
                    erneut veröffentlichen. Falls notwendig, werden wir die unerlaubte Nutzung von Teilen der Inhalte unserer Seite rechtlich
                    verfolgen.<br>
                    <br>
                    Sollten Sie auf dieser Webseite Inhalte finden, die das Urheberrecht verletzen, bitten wir Sie <a href="mailto:office@musterfirma.com">uns zu kontaktieren</a>.<br>
                    <br>
                    <h5>Bildernachweis</h5>
                    Die Bilder, Fotos und Grafiken auf dieser Webseite sind urheberrechtlich geschützt.
                    <br>
                    Die Bilderrechte liegen bei den folgenden Fotografen und Unternehmen: Fotograf Mustermann
                </div>
                <div class="col"></div>
            </div>
        </div>
        <br><br>
        <?php
        require_once "includes/bootstrap_footer.php";
        ?>

	</body>

</html>