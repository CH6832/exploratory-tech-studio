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
                    <h1 style='text-align:center;font-size:50px'>Datenschutzerklärung<br></h1>
                    <br>
                </div>
                <div class="col"></div>
            </div>
            <div class="row">
                <div class="col">
                </div>
                <div class="col-14">
                    <h5>Inhaltsverzeichnis</h5>
                    <a href="#einleitung">Einleitung und Überblick</a><br>
                    <a href="#anwendungsbereich">Anwendungsbereich</a><br>
                    <a href="#rechtsgrundlagen">Rechtsgrundlagen</a><br>
                    <a href="#verantwortliche">Kontaktdaten des Verantwortlichen</a><br>
                    <a href="#datenschutzbeauftragte">Kontaktdaten des Datenschutzbeauftragten</a><br>
                    <a href="#speicherdauer">Speicherdauer</a><br>
                    <a href="#datenschutz">Rechte laut Datenschutz-Grundverordnung</a><br>
                    <a href="#web">Webhosting</a><br><br>

                    <h5 id="einleitung">Einleitung und Überblick</h5>
                    <p style='text-align:justify;'>Wir haben diese Datenschutzerklärung (Fassung 11.08.2021-121795891) verfasst, um Ihnen gemäß der Vorgaben der Datenschutz-Grundverordnung (EU) 2016/679 und
                    anwendbaren nationalen Gesetzen zu erklären, welche personenbezogenen Daten (kurz Daten) wir als Verantwortliche – und die von uns beauftragten Auftragsverarbeiter
                    (z. B. Provider) – verarbeiten, zukünftig verarbeiten werden und welche rechtmäßigen Möglichkeiten Sie haben. Die verwendeten Begriffe sind geschlechtsneutral zu verstehen.
                    Kurz gesagt: Wir informieren Sie umfassend über Daten, die wir über Sie verarbeiten.</p>

                    <p style='text-align:justify;'>Datenschutzerklärungen klingen für gewöhnlich sehr technisch und verwenden juristische Fachbegriffe. Diese Datenschutzerklärung soll Ihnen hingegen die wichtigsten
                    Dinge so einfach und transparent wie möglich beschreiben. Soweit es der Transparenz förderlich ist, werden technische Begriffe leserfreundlich erklärt, Links zu
                    weiterführenden Informationen geboten und Grafiken zum Einsatz gebracht. Wir informieren damit in klarer und einfacher Sprache, dass wir im Rahmen unserer
                    Geschäftstätigkeiten nur dann personenbezogene Daten verarbeiten, wenn eine entsprechende gesetzliche Grundlage gegeben ist. Das ist sicher nicht möglich,
                    wenn man möglichst knappe, unklare und juristisch-technische Erklärungen abgibt, so wie sie im Internet oft Standard sind, wenn es um Datenschutz geht.
                    Ich hoffe, Sie finden die folgenden Erläuterungen interessant und informativ und vielleicht ist die eine oder andere Information dabei, die Sie noch nicht kannten.
                    Wenn trotzdem Fragen bleiben, möchten wir Sie bitten, sich an die unten bzw. im Impressum genannte verantwortliche Stelle zu wenden, den vorhandenen Links
                    zu folgen und sich weitere Informationen auf Drittseiten anzusehen. Unsere Kontaktdaten finden Sie selbstverständlich auch im Impressum.</p>

                    <h5 id="anwendungsbereich">Anwendungsbereich</h5>
                    <p style='text-align:justify;'>Diese Datenschutzerklärung gilt für alle von uns im Unternehmen verarbeiteten personenbezogenen Daten und für alle personenbezogenen Daten, die von uns beauftragte
                    Firmen (Auftragsverarbeiter) verarbeiten. Mit personenbezogenen Daten meinen wir Informationen im Sinne des Art. 4 Nr. 1 DSGVO wie zum Beispiel Name, E-Mail-Adresse
                    und postalische Anschrift einer Person. Die Verarbeitung personenbezogener Daten sorgt dafür, dass wir unsere Dienstleistungen und Produkte anbieten und abrechnen können,
                    sei es online oder offline. Der Anwendungsbereich dieser Datenschutzerklärung umfasst:</p>
                    <ul>
                        <li>alle Onlineauftritte (Websites, Onlineshops), die wir betreiben</li>
                        <li>Social Media Auftritte und E-Mail-Kommunikation</li>
                        <li>mobile Apps für Smartphones und andere Geräte</li>
                    </ul>
                    <p>Kurz gesagt: Die Datenschutzerklärung gilt für alle Bereiche, in denen personenbezogene Daten im Unternehmen über die genannten Kanäle strukturiert
                        verarbeitet werden. Sollten wir außerhalb dieser Kanäle mit Ihnen in Rechtsbeziehungen eintreten, werden wir Sie gegebenenfalls gesondert informieren.</p>

                    <h5 id="rechtsgrundlagen">Rechtsgrundlagen</h5>
                    <p style='text-align:justify;'>In der folgenden Datenschutzerklärung geben wir Ihnen transparente Informationen zu den rechtlichen Grundsätzen und Vorschriften, also den Rechtsgrundlagen
                    der Datenschutz-Grundverordnung, die uns ermöglichen, personenbezogene Daten zu verarbeiten.
                    Was das EU-Recht betrifft, beziehen wir uns auf die VERORDNUNG (EU) 2016/679 DES EUROPÄISCHEN PARLAMENTS UND DES RATES
                    vom 27. April 2016. Diese Datenschutz-Grundverordnung der EU können Sie selbstverständlich online auf EUR-Lex, dem Zugang zum EU-Recht,
                    unter <a href="https://eur-lex.europa.eu/legal-content/DE/TXT/?uri=celex%3A32016R0679">https://eur-lex.europa.eu/legal-content/DE/TXT/?uri=celex%3A32016R0679</a> nachlesen.<p>

                    <p>Wir verarbeiten Ihre Daten nur, wenn mindestens eine der folgenden Bedingungen zutrifft:</p>

                    <ul>
                        <li>Einwilligung (Artikel 6 Absatz 1 lit. a DSGVO): Sie haben uns Ihre Einwilligung gegeben, Daten zu einem bestimmten Zweck zu verarbeiten.
                        Ein Beispiel wäre die Speicherung Ihrer eingegebenen Daten eines Kontaktformulars.</li>
                        
                        <li>Vertrag (Artikel 6 Absatz 1 lit. b DSGVO): Um einen Vertrag oder vorvertragliche Verpflichtungen mit Ihnen zu erfüllen, verarbeiten wir Ihre Daten.
                        Wenn wir zum Beispiel einen Kaufvertrag mit Ihnen abschließen, benötigen wir vorab personenbezogene Informationen.</li>

                        <li>Rechtliche Verpflichtung (Artikel 6 Absatz 1 lit. c DSGVO): Wenn wir einer rechtlichen Verpflichtung unterliegen, verarbeiten wir Ihre Daten.
                        Zum Beispiel sind wir gesetzlich verpflichtet Rechnungen für die Buchhaltung aufzuheben. Diese enthalten in der Regel personenbezogene Daten.</li>
                        
                        <li>Berechtigte Interessen (Artikel 6 Absatz 1 lit. f DSGVO): Im Falle berechtigter Interessen, die Ihre Grundrechte nicht einschränken, behalten wir uns die Verarbeitung personenbezogener
                        Daten vor. Wir müssen zum Beispiel gewisse Daten verarbeiten, um unsere Website sicher und wirtschaftlich effizient betreiben zu können. Diese Verarbeitung ist somit ein berechtigtes Interesse.
                        Weitere Bedingungen wie die Wahrnehmung von Aufnahmen im öffentlichen Interesse und Ausübung öffentlicher Gewalt sowie dem Schutz lebenswichtiger Interessen treten bei uns in der Regel nicht auf.
                        Soweit eine solche Rechtsgrundlage doch einschlägig sein sollte, wird diese an der entsprechenden Stelle ausgewiesen.</li>
                    </ul>

                    <p>Zusätzlich zu der EU-Verordnung gelten auch noch nationale Gesetze:</p>

                    <p style='text-align:justify;'>In Österreich ist dies das Bundesgesetz zum Schutz natürlicher Personen bei der Verarbeitung personenbezogener Daten (Datenschutzgesetz), kurz DSG.
                    In Deutschland gilt das Bundesdatenschutzgesetz, kurz BDSG.
                    Sofern weitere regionale oder nationale Gesetze zur Anwendung kommen, informieren wir Sie in den folgenden Abschnitten darüber.</p>

                    <h5 id="verantwortliche">Kontaktdaten des Verantwortlichen</h5>
                    <p>Sollten Sie Fragen zum Datenschutz haben, finden Sie nachfolgend die Kontaktdaten der verantwortlichen Person bzw. Stelle:<br>
                    
                    Gaestebuch GmbH<br>
                    Christoph Hartleb<br>
                    Musterstrasse 1, Stiege 2 Tür 3<br>
                    9876 Musterberg<br>
                    Österreich<br>
                    <a href="impressum.php">Impressum</a></p>

                    <h5 id="datenschutzbeauftragte">Kontaktdaten des Datenschutzbeauftragten</h5>
                    <p>Nachfolgend finden Sie die Kontaktdaten unseres Datenschutzbeauftragten.</p>

                    <p>E-Mail: <a href="mailto:datenschutz.gaestebuch.web@site.at">datenschutz.gaestebuch.web@site.at</a></p>
                    <p>Telefon: +01 234 567 8910 222</p>

                    <h5 id="speicherdauer">Speicherdauer</h5>
                    <p style='text-align:justify;'>Dass wir personenbezogene Daten nur so lange speichern, wie es für die Bereitstellung unserer Dienstleistungen und Produkte unbedingt notwendig
                    ist, gilt als generelles Kriterium bei uns. Das bedeutet, dass wir personenbezogene Daten löschen, sobald der Grund für die Datenverarbeitung
                    nicht mehr vorhanden ist. In einigen Fällen sind wir gesetzlich dazu verpflichtet, bestimmte Daten auch nach Wegfall des ursprüngliches Zwecks
                    zu speichern, zum Beispiel zu Zwecken der Buchführung.</p>

                    <p>Sollten Sie die Löschung Ihrer Daten wünschen oder die Einwilligung zur Datenverarbeitung widerrufen, werden die Daten so rasch wie möglich und soweit keine Pflicht
                    zur Speicherung besteht, gelöscht.</p>

                    <p>Über die konkrete Dauer der jeweiligen Datenverarbeitung informieren wir Sie weiter unten, sofern wir weitere Informationen dazu haben.</p>

                    <h5 id="datenschutz">Rechte laut Datenschutz-Grundverordnung</h5>
                    <p>Laut Artikel 13 DSGVO stehen Ihnen die folgenden Rechte zu, damit es zu einer fairen und transparenten Verarbeitung von Daten kommt:</p>

                    <p>Sie haben laut Artikel 15 DSGVO ein Auskunftsrecht darüber, ob wir Daten von Ihnen verarbeiten. Sollte das zutreffen, haben Sie Recht darauf eine Kopie der Daten zu erhalten
                    und die folgenden Informationen zu erfahren:</p>
                    <ul>
                        <li>zu welchem Zweck wir die Verarbeitung durchführen;</li>
                        <li>die Kategorien, also die Arten von Daten, die verarbeitet werden;</li>
                        <li>wer diese Daten erhält und wenn die Daten an Drittländer übermittelt werden, wie die Sicherheit garantiert werden kann;</li>
                        <li>wie lange die Daten gespeichert werden;</li>
                        <li>das Bestehen des Rechts auf Berichtigung, Löschung oder Einschränkung der Verarbeitung und dem Widerspruchsrecht gegen die Verarbeitung;</li>
                        <li>dass Sie sich bei einer Aufsichtsbehörde beschweren können (Links zu diesen Behörden finden Sie weiter unten);</li>
                        <li>die Herkunft der Daten, wenn wir sie nicht bei Ihnen erhoben haben;</li>
                        <li>ob Profiling durchgeführt wird, ob also Daten automatisch ausgewertet werden, um zu einem persönlichen Profil von Ihnen zu gelangen.</li>
                    </ul>

                    <p style='text-align:justify;'>Sie haben laut Artikel 16 DSGVO ein Recht auf Berichtigung der Daten, was bedeutet, dass wir Daten richtig stellen müssen, falls Sie Fehler finden.
                    Sie haben laut Artikel 17 DSGVO das Recht auf Löschung („Recht auf Vergessenwerden“), was konkret bedeutet, dass Sie die Löschung Ihrer Daten verlangen dürfen.
                    Sie haben laut Artikel 18 DSGVO das Recht auf Einschränkung der Verarbeitung, was bedeutet, dass wir die Daten nur mehr speichern dürfen aber nicht weiter verwenden.
                    Sie haben laut Artikel 19 DSGVO das Recht auf Datenübertragbarkeit, was bedeutet, dass wir Ihnen auf Anfrage Ihre Daten in einem gängigen Format zur Verfügung stellen.
                    Sie haben laut Artikel 21 DSGVO ein Widerspruchsrecht, welches nach Durchsetzung eine Änderung der Verarbeitung mit sich bringt.
                    Wenn die Verarbeitung Ihrer Daten auf Artikel 6 Abs. 1 lit. e (öffentliches Interesse, Ausübung öffentlicher Gewalt) oder Artikel 6 Abs. 1 lit. f (berechtigtes Interesse) basiert, können Sie gegen die Verarbeitung Widerspruch einlegen. Wir prüfen danach so rasch wie möglich, ob wir diesem Widerspruch rechtlich nachkommen können.
                    Werden Daten verwendet, um Direktwerbung zu betreiben, können Sie jederzeit gegen diese Art der Datenverarbeitung widersprechen. Wir dürfen Ihre Daten danach nicht mehr für Direktmarketing verwenden.
                    Werden Daten verwendet, um Profiling zu betreiben, können Sie jederzeit gegen diese Art der Datenverarbeitung widersprechen. Wir dürfen Ihre Daten danach nicht mehr für Profiling verwenden.
                    Sie haben laut Artikel 22 DSGVO unter Umständen das Recht, nicht einer ausschließlich auf einer automatisierten Verarbeitung (zum Beispiel Profiling) beruhenden Entscheidung
                    unterworfen zu werden.</p>
                    
                    <p>Kurz gesagt: Sie haben Rechte – zögern Sie nicht, die oben gelistete verantwortliche Stelle bei uns zu kontaktieren!</p>

                    <p style='text-align:justify;'>Wenn Sie glauben, dass die Verarbeitung Ihrer Daten gegen das Datenschutzrecht verstößt oder Ihre datenschutzrechtlichen Ansprüche in sonst einer Weise verletzt
                    worden sind, können Sie sich bei der Aufsichtsbehörde beschweren. Diese ist für Österreich die Datenschutzbehörde, deren Website Sie
                    unter <a href="https://www.dsb.gv.at/">https://www.dsb.gv.at/</a> finden. In Deutschland gibt es für jedes Bundesland einen Datenschutzbeauftragten.
                    Für nähere Informationen können Sie sich an die Bundesbeauftragte für den Datenschutz und die Informationsfreiheit (BfDI) wenden.</p>
                    <p>Für unser Unternehmen ist die folgende lokale Datenschutzbehörde zuständig:</p>

                    <h5>Warum verarbeiten wir personenbezogene Daten?</h5>
                    
                    <p>Die Zwecke der Datenverarbeitung sind:</p>
                    
                    <ul>
                        <li>Professionelles Hosting der Website und Absicherung des Betriebs zur Aufrechterhaltung der Betriebs- und IT-Sicherheit</li>
                        <li>Anonyme Auswertung des Zugriffsverhaltens zur Verbesserung unseres Angebots und ggf. zur Strafverfolgung bzw. Verfolgung von Ansprüchen</li>
                    </ul>

                    <p>Welche Daten werden verarbeitet?</p>
                    <p style='text-align:justify;'>Auch während Sie unsere Website jetzt gerade besuchen, speichert unser Webserver, das ist der Computer auf dem diese Webseite gespeichert ist, in der Regel automatisch Daten wie
                    die komplette Internetadresse (URL) der aufgerufenen Webseite</p>
                    
                    (z. B. <a href="https://www.beispielwebsite.de/beispielunterseite.html?tid=121795891">https://www.beispielwebsite.de/beispielunterseite.html?tid=121795891</a>),
                    
                    Browser und Browserversion (z. B. Chrome 87) das verwendete Betriebssystem (z. B. Windows 10), die Adresse (URL) der zuvor besuchten Seite (Referrer URL)
                    
                    (z. B. <a href="https://www.beispielquellsite.de/vondabinichgekommen.html/">https://www.beispielquellsite.de/vondabinichgekommen.html/</a>)
                    
                    den Hostnamen und die IP-Adresse des Geräts von welchem aus zugegriffen wird (z. B. COMPUTERNAME und 194.23.43.121), Datum und Uhrzeit
                    in Dateien, den sogenannten Webserver-Logfiles

                    <p>Wie lange werden Daten gespeichert?</p>
                    
                    <p style='text-align:justify;'>In der Regel werden die oben genannten Daten zwei Wochen gespeichert und danach automatisch gelöscht. Wir geben diese Daten nicht weiter, können jedoch
                    nicht ausschließen, dass diese Daten beim Vorliegen von rechtswidrigem Verhalten von Behörden eingesehen werden.</p>

                    <p>Kurz gesagt: Ihr Besuch wird durch unseren Provider (Firma, die unsere Website auf speziellen Computern (Servern) laufen lässt), protokolliert, aber wir
                    geben Ihre Daten nicht ohne Zustimmung weiter!</p>

                    <h5 id="web">Webhosting</h5>
                    <p style='text-align:justify;'>Die Rechtmäßigkeit der Verarbeitung personenbezogener Daten im Rahmen des Webhosting ergibt sich aus Art. 6 Abs. 1 lit. f DSGVO
                    (Wahrung der berechtigten Interessen), denn die Nutzung von professionellem Hosting bei einem Provider ist notwendig, um das
                    Unternehmen im Internet sicher und nutzerfreundlich präsentieren und Angriffe und Forderungen hieraus gegebenenfalls verfolgen zu können.</p>

                    <p>Alle Texte sind urheberrechtlich geschützt.</p>
                </div>
                <div class="col"></div>
            </div>
        </div>

        <?php
        require_once "includes/bootstrap_footer.php";
        ?>

	</body>

</html>
