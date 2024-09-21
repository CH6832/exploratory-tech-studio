xquery version "3.0";

declare default element namespace "http://www.tei-c.org/ns/1.0";

declare option exist:serialize "method=html media-type=text/html";

let $werke :=
<texts>
    {
        for $resource in collection("/textanalyzation/data")
        return
    <text name="{ replace(base-uri($resource), '.+/(.+)$', '$1') }">
            { $resource//title[@n = 'digital'] }
    </text>
    }
</texts>
return
<html>
<head><title>Titel</title>

     <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/starter-template.css" rel="stylesheet" />

</head>
<body>

    <!-- Bootstrap Navigation -->
         <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" href="#">ANALYZATIONS OF TEXTS</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
    aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="listing.xquery">Text collection</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="analyze-all.xquery">Analyzation</a>
          </li>
        </ul>
      </div>
    </nav>


    <!-- MS: <main> und <div> aus bootstrap-template -->
    <main role="main" class="container">

      <div class="starter-template" style="text-align: left;">

    <h1>Project description</h1>
    <br/>
   <h4>Source</h4>
   <p>Sources of the texts are the following repositories: <a href="https://www.jeanpaul-edition.de/start.html">Jean Paul –
    Sämtliche Briefe digital</a></p>
   <p>Die Jean Paul Edition der Berlin-Brandenburgischen Akademie der Wissenschaften erarbeitete von 1992 bis 2016
    sämtliche Briefe an den Dichter als IV. Abteilung        der Historisch-kritischen Ausgabe. Es folgte von 2016 bis
    2018 die digitale Edition der Briefe Jean Pauls auf der Grundlage von Eduard Berends III. Abteilung          der
    Historisch-kritischen Ausgabe. Derzeit werden die Briefe aus dem Umfeld Jean Pauls erarbeitet. <a
    href="https://www.jeanpaul-edition.de/ueberblick.html">Zitat aus "Ein Editionsunternehmen und seine
    Geschichte"</a></p>
   <p>Daneben war die Jean Paul Edition der Berlin-Brandenburgischen Akademie der Wissenschaften beteiligt am Abschluss
    der Inventarisierung des Nachlasses von Jean        Paul in der Staatbibliothek zu Berlin – Preußischer Kulturbesitz
    und verantwortete mit dieser zusammen die große Ausstellung zum Jean Paul Jahr 2013 in der              Stiftung
    „Brandenburger Tor“ in der Berlin. Sie veranstaltet überdies Lesungen und Vorträge. <a
    href="https://www.jeanpaul-edition.de/ueberblick.html">Zitat aus "Ein Editionsunternehmen und seine
    Geschichte"</a></p>
    <br/>

    <h4>Description of functionalities</h4>
   <h5>Text collections</h5>
  <p>If you click on "Text collection" in the navigation bar, a list of all ten letters is displayed. Each individual letter can be clicked on. The title of the digital edition of the letter, the source of the letter itself and the entire text are displayed.
    </p>
    <br/>
    <h5>Analysis</h5>
     <p>In the area of analysis, there are analysis results to be discovered, which were carried out on the collected corpus of texts
         <ul>
             <li>Counting all the different layout instructions in the texts</li>
             <li>Search for the text string "pleasure" in the texts</li>
             <li>Evaluation of tokens, types and paragraphs</li>
         </ul>
     </p>
    </div>
    </main>

</body>
</html>



