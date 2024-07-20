xquery version "3.0";


declare default element namespace "http://www.tei-c.org/ns/1.0";


declare option exist:serialize "method=html media-type=text/html";


declare variable $briefe := collection("/textanalyzation/data");


<html>
    <head><title>Titel</title></head>
    <body>

    <ul>
{
    for $title in $briefe//title[@n = 'digital']
    return
    <li>{ data($title) }</li>
}

    </ul>

    </body>
</html>



