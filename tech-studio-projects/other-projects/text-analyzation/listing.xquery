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

          <li class="nav-item">
            <a class="nav-link" href="index.xquery">Home</a>
          </li>

          <li class="nav-item active">
            <a class="nav-link" href="#">Text collection <span class="sr-only">(current)</span></a>
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


    <ul>
    {
        for $html-title in $werke/text
        return
    <li>
        <a href="data/{ $html-title/@name }?_xsl=../title.xsl">{ data($html-title) }</a>


    </li>
    }
    </ul>
    </div>
    </main>

</body>
</html>



