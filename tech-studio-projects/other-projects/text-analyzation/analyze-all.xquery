xquery version "3.0";

declare default element namespace "http://www.tei-c.org/ns/1.0";

declare option exist:serialize "method=html media-type=text/html";

<html>
<head><title>Analyse</title>
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

          <li class="nav-item">
            <a class="nav-link" href="index.xquery">Home</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="listing.xquery">Text collection</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#">Analyzation</a>
          </li>
        </ul>
      </div>
    </nav>

 <main role="main" class="container">

      <div class="starter-template" style="text-align: left;">

    <h1>Analyse</h1>

      <div>
        <h2>Liste aller Titel</h2>
        <ul>
{
    for $title in collection("/textanalyzation/data")//title[@n = 'digital']
    order by $title descending
    return <li>{ data($title) }</li>
}
        </ul>
    </div>



     <div>
         <h3>Zählen aller unterschiedlichen Layoutanweisungen in den Texten</h3>
        <ul>
            <!-- für jeden aller unterschiedlichen, im Text vorkommenden Layoutanweisungen ... -->
{
    for $rendition in distinct-values(collection("/textanalyzation/data")//@rendition)
    (: wird die Anzahl der Anweisungen gezählt und in der Variable $anzahlrendition gespeichert :) let $anzahlrendition
        :=
        for $rend in//div//* (: in dieser Variable wird für alle Elemente innerhalb von div geprüft ... :)
        where $rend/@rendition[. = $rendition]
        (: ob sie von der aktuellen Layoutanweisung (-> $rendition-Variable) stammen, falls ja, wird das Element
         : zurückgegeben :) return $rend
    (: Sortierung der Liste nach Häufigkeit der Liste, absteigend :) order by count($anzahlrendition) descending
    (: Rück-/Ausgabe eines Listenelements für jede Layoutanweisung mit Angabe der Häufigkeit :) return
                   <li>{ data($rendition) } ({ count($anzahlrendition) }) </li>
}
        </ul>
    </div>



     <div>
         <h3>Suche nach dem Textstring "Vergnügen" in den Texten</h3>
        <ul>

{
    for $absatz in collection("/textanalyzation/data")//div[@type = 'letter']/p
    let $briefnummer := $absatz/preceding::idno[@type = 'TELOTAID']
    where contains($absatz, "Vergnügen")
    (: alternativ dazu: for $absatz in collection("briefe/data")//div[@type='letter']/p[contains(., "Vergnügen")] :)
        return
                   <li>{ data($briefnummer) }: { data($absatz) }</li>
}



        </ul>
    </div>

     <div>
         <h3>Auswertung von Tokens, Types und Absätzen</h3>
        <table border="1">

            <th>Titel</th>
            <th>Anzahl Tokens</th>
            <th>Anzahl Types</th>
            <th>Anzahl Absätze</th>

{
    for $text in collection("/textanalyzation/data")
    let $title := $text//title[@n = 'digital']
    let $all-paragraphs := $text//text//p
    let $toks := $text//text()/tokenize(normalize-space(.), '\s')
    let $counttoks := count($toks)
    let $counttypes := count(distinct-values($toks))
    return
                   <tr><td>{ data($title) }</td> <td>{ data($counttoks) }</td> <td>{ data($counttypes) }</td> <td>{
            count($all-paragraphs)
        }</td></tr>
}



        </table>
    </div>


</div>
</main>


</body>
</html>



