<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tei="http://www.tei-c.org/ns/1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:math="http://www.w3.org/2005/xpath-functions/math" exclude-result-prefixes="xs math" version="3.0">
    
    <xsl:template match="/">
    <html>
        <head>
            <title>Titel</title>
            <link href="/exist/rest/textanalyzation/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="/exist/rest/textanalyzation/css/starter-template.css" rel="stylesheet"/>
        </head>
        <body>
             
    <!-- Bootstrap Navigation -->
         <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"/>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="/exist/rest/textanalyzation/index.xquery">Home
                                </a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/exist/rest/textanalyzation/listing.xquery">Textsammlung
                                </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/exist/rest/textanalyzation/analyze-all.xquery">Analyse</a>
          </li>
        </ul>
      </div>
    </nav>


    <!-- MS: <main> und <div> aus bootstrap-template -->
    <main role="main" class="container">

      <div class="starter-template" style="text-align: left;">

        <h1>
            <xsl:value-of select="//tei:title[@n='digital']"/>
        </h1>
        <h5>
            <xsl:value-of select="//tei:edition"/>
        </h5>
        <p>
            <xsl:value-of select="//tei:body"/>
        </p>
        </div>
        </main>
            
        </body>
        
    </html>
    </xsl:template>
    
</xsl:stylesheet>