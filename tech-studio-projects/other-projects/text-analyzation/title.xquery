xquery version"3.0";
declare default element namespace "http://www.tei-c.org/ns/1.0";

declare option exist:serialize "method=html media-type=text/html";

transform:transform(
    doc("/textanalyzation/data/I_1.xml"),
    doc("/textanalyzation/title.xsl"),
    ()
)
