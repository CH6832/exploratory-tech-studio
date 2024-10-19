import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CMFCLCITaxonomyPackage {

    private String destinationFolder;
    private String fullPathToZip;
    private List<String> entryPoints;

    public CMFCLCITaxonomyPackage(String destinationFolder, String fullPathToZip) {
        this.destinationFolder = destinationFolder;
        this.fullPathToZip = fullPathToZip;
        this.entryPoints = new ArrayList<>();
    }

    public void restructureFolder() throws IOException {
        // Remove source file
        Path sourceFile = Paths.get(destinationFolder, Paths.get(fullPathToZip).getFileName().toString().replace("input", "output"));
        Files.deleteIfExists(sourceFile);

        // Create a folder called files in the source folder
        Files.createDirectories(Paths.get(destinationFolder, "files"));

        // Iterate over all files and folders and put all of them into the "files" folder
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(destinationFolder))) {
            for (Path itemPath : stream) {
                if (!itemPath.getFileName().toString().equals("META-INF") && !itemPath.getFileName().toString().equals("files")) {
                    Files.move(itemPath, Paths.get(destinationFolder, "files", itemPath.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    public void fixMetaInfFolder() throws IOException {
        // Create META-INF directory
        Files.createDirectories(Paths.get(destinationFolder, "META-INF"));
        System.out.println("    META-INF directory generated");
    }

    public void convertToZipArchive() {
        // Implementation would go here
    }

    public void fixTopLevelSingleDir() throws IOException {
        Files.createDirectories(Paths.get(destinationFolder, Paths.get(fullPathToZip).getFileName().toString().replace(".zip", "")));
        System.out.println("    Top level directory generated");
    }

    public void fixTaxonomyPackageXml(String sourceFolder) throws Exception {
        // Extract entry points
        extractEntryPoints(sourceFolder);

        // Version patterns
        String tpVersion = extractVersion(sourceFolder);

        // Create taxonomyPackage.xml
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document xmlPkg = dBuilder.newDocument();

        Element pkgElement = xmlPkg.createElementNS("http://xbrl.org/2016/taxonomy-package", "tp:taxonomyPackage");
        pkgElement.setAttribute("xml:lang", "en");
        pkgElement.setAttribute("xsi:schemaLocation", "http://xbrl.org/2016/taxonomy-package http://xbrl.org/2016/taxonomy-package.xsd");
        pkgElement.appendChild(xmlPkg.createComment("This file and its content has been generated and is not part of the original ZIP."));

        xmlPkg.appendChild(pkgElement);

        appendChildElement(pkgElement, "tp:identifier", sourceFolder);
        appendChildElement(pkgElement, "tp:name", sourceFolder.replace(".zip", "").replace("-" + tpVersion, "") + " XBRL Taxonomy");
        
        if (sourceFolder.contains("CMF")) {
            appendChildElement(pkgElement, "tp:description", "Expanded IFRS 2020 taxonomy with additional Chilean regulations added");
        }

        appendChildElement(pkgElement, "tp:version", tpVersion);
        
        if (sourceFolder.contains("CMF")) {
            appendChildElement(pkgElement, "tp:publisher", "Comision para el Mercado Financiero");
            appendChildElement(pkgElement, "tp:publisherURL", "https://www.cmfchile.cl/portal/principal/613/w3-channel.html");
        }

        appendChildElement(pkgElement, "tp:publicationDate", tpVersion);

        Element xmlEPs = xmlPkg.createElement("tp:entryPoints");
        pkgElement.appendChild(xmlEPs);

        for (String schemaName : entryPoints) {
            Element xmlEP = xmlPkg.createElement("tp:entryPoint");
            xmlEPs.appendChild(xmlEP);

            String entryPointName = Paths.get(schemaName).getFileName().toString().replace("_", "-").replace(".xsd", "");
            appendChildElement(xmlEP, "tp:name", entryPointName);
            
            String epVersion = extractDateFromFileName(schemaName);
            if (epVersion != null) {
                appendChildElement(xmlEP, "tp:version", epVersion);
            }

            String entryPointDocument = "http://www.cmfchile.cl/cl/fr/ci/" + tpVersion + schemaName.replace("CL_CI_2020", "").replace("CMF-CL-CI-" + tpVersion, "").replace("\\", "/");
            Element epDoc = xmlPkg.createElement("tp:entryPointDocument");
            epDoc.setAttribute("href", entryPointDocument);
            xmlEP.appendChild(epDoc);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlPkg);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);

        String strXmlPkg = writer.toString();
        
        // Write to META-INF/taxonomyPackage.xml
        Path metaInfPath = Paths.get(sourceFolder, "META-INF", "taxonomyPackage.xml");
        if (!Files.exists(metaInfPath)) {
            try (BufferedWriter writerFile = Files.newBufferedWriter(metaInfPath, StandardOpenOption.CREATE_NEW)) {
                writerFile.write(strXmlPkg);
            }
        }
    }

    private void appendChildElement(Element parent, String tagName, String textContent) {
        Document doc = parent.getOwnerDocument();
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    private String extractVersion(String sourceFolder) {
        Pattern yearMonthDayPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern yearMonthPattern = Pattern.compile("\\d{4}-\\d{2}");
        Pattern yearPattern = Pattern.compile("\\d{4}");

        Matcher matcher = yearMonthDayPattern.matcher(sourceFolder);
        if (matcher.find()) {
            return matcher.group();
        }
        matcher = yearMonthPattern.matcher(sourceFolder);
        if (matcher.find()) {
            return matcher.group();
        }
        matcher = yearPattern.matcher(sourceFolder);
        if (matcher.find()) {
            return matcher.group();
        }
        return ""; // Default return
    }

    private String extractDateFromFileName(String fileName) {
        Matcher matcher = Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(fileName);
        if (matcher.find()) {
            return matcher.group();
        }
        return null; // Default return
    }

    private void extractEntryPoints(String sourceFolder) throws IOException {
        // Extract entry points from the specified folder
        entryPoints.clear(); // Ensure entryPoints is defined
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(sourceFolder), "*.xsd")) {
            for (Path taxonomySchema : stream) {
                // Process XSD files
                try {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document root = dBuilder.parse(taxonomySchema.toFile());

                    if (root.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "appinfo").getLength() > 0) {
                        String relEntryPointPath = taxonomySchema.toString().replace(destinationFolder, "");
                        entryPoints.add(relEntryPointPath);
                    }
                } catch (SAXException | ParserConfigurationException e) {
                    System.out.println("Error processing " + taxonomySchema + ": " + e.getMessage());
                }
            }
        }
    }

    public void fixCatalogXml(String sourceFolder) throws Exception {
        // Design and generate the 'catalog.xml'
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document xmlCatalog = dBuilder.newDocument();

        Element catalogElement = xmlCatalog.createElement("catalog");
        catalogElement.setAttribute("xmlns", "urn:oasis:names:tc:entity:xmlns:xml:catalog");
        catalogElement.setAttribute("xmlns:spy", "http://www.altova.com/catalog_ext");
        catalogElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        catalogElement.setAttribute("xsi:schemaLocation", "urn:oasis:names:tc:entity:xmlns:xml:catalog Catalog.xsd");
        xmlCatalog.appendChild(catalogElement);

        String catEntryVersion = extractCatalogVersion(sourceFolder);

        Element rewriteURI = xmlCatalog.createElement("rewriteURI");
        rewriteURI.setAttribute("uriStartString", "http://www.cmfchile.cl/cl/fr/ci/" + "2020-01-02" + "/");
        rewriteURI.setAttribute("rewritePrefix", "../CL-CI-" + catEntryVersion + "/");
        catalogElement.appendChild(rewriteURI);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlCatalog);
        StreamResult result = new StreamResult(new StringWriter());
        transformer.transform(source, result);

        String strXmlCatalog = result.getWriter().toString();

        // Write to META-INF/catalog.xml
        Path catalogPath = Paths.get(sourceFolder, "META-INF", "catalog.xml");
        if (!Files.exists(catalogPath)) {
            try (BufferedWriter writerFile = Files.newBufferedWriter(catalogPath, StandardOpenOption.CREATE_NEW)) {
                writerFile.write(strXmlCatalog);
            }
        } else {
            System.out.println("WARNING: 'catalog.xml' file already exists!");
        }
    }

    private String extractCatalogVersion(String sourceFolder) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(sourceFolder), "*.xsd")) {
            for (Path file : stream) {
                if (file.getFileName().toString().contains("_cor_")) {
                    return file.getFileName().toString().replaceAll("[^\\d]", "").substring(0, 4); // Extract year
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown"; // Default return
    }
}
