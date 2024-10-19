package src.fixer; // Update the package name as necessary

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class EDINETTaxonomyPackage extends TaxonomyPackageFixerInterface {

    /**
     * Use this class to fix an EDINET XBRL Taxonomy Package.
     * The package in input/* folder as well as newer and older versions
     * can be found here: https://disclosure2.edinet-fsa.go.jp/weee0020.aspx
     */

    public void convertToZipArchive() {
        try {
            Path zipPath = Paths.get(fullPathToZip);
            try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipPath))) {
                File sourceDir = new File(destinationFolder);
                for (File file : sourceDir.listFiles()) {
                    if (file.isFile()) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zos.putNextEntry(zipEntry);
                        Files.copy(file.toPath(), zos);
                        zos.closeEntry();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fixTopLevelSingleDir() {
        File topLevelDir = new File(destinationFolder, new File(fullPathToZip).getName().replace(".zip", ""));
        if (topLevelDir.mkdir()) {
            printColorMsg("    Top level directory generated", Fore.YELLOW);
        }
    }

    public void fixMetaInfFolder() {
        File metaInfDir = new File(destinationFolder, "META-INF");
        if (metaInfDir.mkdir()) {
            printColorMsg("    META-INF directory generated", Fore.YELLOW);
        }
    }

    public void restructureFolder() {
        File[] files = new File(destinationFolder).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".zip")) {
                    file.delete(); // Delete the original zip if needed
                    break;
                }
            }
            for (File file : files) {
                if (!file.getName().equals(new File(fullPathToZip).getName())) {
                    File destinationDir = new File(destinationFolder, new File(fullPathToZip).getName().replace(".zip", ""));
                    try {
                        Files.move(file.toPath(), destinationDir.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            printColorMsg("    Package content restructured", Fore.YELLOW);
        }
    }

    public void fixTaxonomyPackageXml(String sourceFolder) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create taxonomyPackage element
            Element taxonomyPackage = doc.createElement("taxonomyPackage");
            taxonomyPackage.setAttribute("xml:lang", "en");
            taxonomyPackage.setAttribute("xmlns", "http://xbrl.org/2016/taxonomy-package");
            taxonomyPackage.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            taxonomyPackage.setAttribute("xsi:schemaLocation", "http://xbrl.org/2016/taxonomy-package http://xbrl.org/2016/taxonomy-package.xsd");
            taxonomyPackage.appendChild(doc.createComment("This file and its content has been generated and is not part of the original ZIP."));
            doc.appendChild(taxonomyPackage);

            // Add metadata elements
            addElementWithText(doc, taxonomyPackage, "identifier", "full/official/path/to/the/package.zip");
            addElementWithText(doc, taxonomyPackage, "name", "ALL_2022-11-01.zip");
            addElementWithText(doc, taxonomyPackage, "description", "The ALL-2022-11-01 Taxonomy Package provided by the JFSA.");
            addElementWithText(doc, taxonomyPackage, "version", "2023");
            addElementWithText(doc, taxonomyPackage, "publisher", "Japanese Financial Service Agency");
            addElementWithText(doc, taxonomyPackage, "publisherURL", "https://www.fsa.go.jp/en/");
            addElementWithText(doc, taxonomyPackage, "publicationDate", "2022-11-01");

            // Create entryPoints element
            Element entryPoints = doc.createElement("entryPoints");
            taxonomyPackage.appendChild(entryPoints);

            // Add entry points from files
            File samplesDir = new File(sourceFolder, "samples/2022-11-01");
            for (File file : samplesDir.listFiles()) {
                if (file.getName().endsWith(".xsd")) {
                    Element entryPoint = doc.createElement("entryPoint");
                    entryPoints.appendChild(entryPoint);
                    
                    String entryPointName = getEntryPointName(file);
                    addElementWithText(doc, entryPoint, "name", entryPointName);
                    addElementWithText(doc, entryPoint, "version", "2023");
                    Element entryPointDocument = doc.createElement("entryPointDocument");
                    entryPointDocument.setAttribute("href", "http://disclosure.edinet-fsa.go.jp/samples/" + getTpPublicationDate() + "/" + file.getName());
                    entryPoint.appendChild(entryPointDocument);
                }
            }

            // Write the XML content to file
            writeXmlToFile(doc, new File(sourceFolder, "META-INF/taxonomyPackage.xml"));

            // Validate taxonomyPackage.xml
            TPChecker checker = new TPChecker();
            checker.validateXml("http://www.xbrl.org/2016/taxonomy-package.xsd", new File(sourceFolder, "META-INF/taxonomyPackage.xml").getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fixCatalogXml(String sourceFolder) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create catalog element
            Element catalog = doc.createElement("catalog");
            catalog.setAttribute("xmlns", "urn:oasis:names:tc:entity:xmlns:xml:catalog");
            catalog.setAttribute("xmlns:spy", "http://www.altova.com/catalog_ext");
            catalog.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            catalog.setAttribute("xsi:schemaLocation", "urn:oasis:names:tc:entity:xmlns:xml:catalog Catalog.xsd");
            doc.appendChild(catalog);

            // Write sample directory entries
            File samplesDir = new File(sourceFolder, "samples");
            for (File sampleFolder : samplesDir.listFiles()) {
                if (sampleFolder.isDirectory()) {
                    Element rewriteURI = doc.createElement("rewriteURI");
                    rewriteURI.setAttribute("uriStartString", "http://disclosure.edinet-fsa.go.jp/samples/" + sampleFolder.getName() + "/");
                    rewriteURI.setAttribute("rewritePrefix", "../samples/" + sampleFolder.getName() + "/");
                    catalog.appendChild(rewriteURI);
                }
            }

            // Write taxonomy directory entries
            File taxonomyDir = new File(sourceFolder, "taxonomy");
            for (File taxonomyFolder : taxonomyDir.listFiles()) {
                if (taxonomyFolder.isDirectory()) {
                    for (File epFolder : taxonomyFolder.listFiles()) {
                        if (epFolder.isDirectory()) {
                            String newPath = taxonomyFolder.getName() + "/" + epFolder.getName();
                            Element rewriteURI = doc.createElement("rewriteURI");
                            rewriteURI.setAttribute("uriStartString", "http://disclosure.edinet-fsa.go.jp/taxonomy/" + newPath + "/");
                            rewriteURI.setAttribute("rewritePrefix", "../taxonomy/" + newPath + "/");
                            catalog.appendChild(rewriteURI);
                        }
                    }
                }
            }

            // Write the XML content to file
            writeXmlToFile(doc, new File(sourceFolder, "META-INF/catalog.xml"));

            // Validate catalog.xml
            TPChecker checker = new TPChecker();
            checker.validateXml("http://www.xbrl.org/2016/taxonomy-package-catalog.xsd", new File(sourceFolder, "META-INF/catalog.xml").getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addElementWithText(Document doc, Element parent, String name, String textContent) {
        Element element = doc.createElement(name);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    private void writeXmlToFile(Document doc, File file) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
        printColorMsg("    " + file.getName() + " file generated", Fore.YELLOW);
    }

    private String getEntryPointName(File file) {
        String filePath = file.getAbsolutePath();
        if (filePath.contains("all")) {
            return "ALL : All Entry Points";
        } else if (filePath.contains("ifrs")) {
            return "IFRS : International Financial Reporting Standards";
        } else if (filePath.contains("jpcrp")) {
            return "JPCRP : Disclosure of Corporate Information";
        } else if (filePath.contains("jpctl")) {
            return "JPCTL : Internal Control Form No.1 Internal Control Report";
        } else if (filePath.contains("jpdei")) {
            return "JPDEI : Document and Entity information";
        } else if (filePath.contains("jpigp")) {
            return "JPIGP : Designed International Accounting Standards";
        } else if (filePath.contains("jplvh")) {
            return "JPLVH : Large Volume Holding";
        } else if (filePath.contains("jppfs")) {
            return "JPPFS : Primary Financial Statments";
        } else if (filePath.contains("jpsps")) {
            return "JPSPS : Disclosure of Information, etc. on Specified Securities";
        } else if (filePath.contains("jptoi")) {
            return "JPTOI : Tender Offer by Issuer";
        } else if (filePath.contains("jptoo")) {
            return "JPTOO : Tender Offer by Those Other than Issuer Form";
        } else {
            System.out.println("Please integrate new entry point group in script and in template!");
            return "<missingEntry>";
        }
    }

    private String getTpPublicationDate() {
        // TODO: implement method to retrieve the publication date
        return "yyyy-mm-dd"; // Placeholder for actual implementation
    }

    private void printColorMsg(String msg, Fore color) {
        // Implement color printing logic if necessary
        System.out.println(msg);
    }
}
