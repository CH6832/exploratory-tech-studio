import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TPChecker {

    public TPChecker() {
        // Constructor
    }

    public void checkCaseSensitivity() {
        // Case sensitivity is handled by Java naturally, so no implementation needed
    }

    public boolean hasZipFormat(String archive) {
        return archive.endsWith(".zip");
    }

    public boolean hasTopLevelSingleDir(String archive) {
        try (ZipFile zipFile = new ZipFile(new File(archive))) {
            Set<String> topDir = new HashSet<>();
            zipFile.stream()
                .map(ZipEntry::getName)
                .forEach(name -> topDir.add(name.split("/")[0]));
            return topDir.size() == 1;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateXML(String schemaFile, File example) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(example);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(schemaFile));
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(xmlDocument));
            return true;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println("XML Validation Error: " + e.getMessage());
            return false;
        }
    }

    public boolean hasMetaInfFolder(String archive, String folderName) {
        try (ZipFile zipFile = new ZipFile(new File(archive))) {
            return zipFile.stream().anyMatch(entry -> entry.getName().contains(folderName + "/"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasTaxonomyPackageXml(String archive, String tpFile) {
        try (ZipFile zipFile = new ZipFile(new File(archive))) {
            return zipFile.stream().anyMatch(entry -> entry.getName().equals(tpFile));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasCatalogXml(String archive, String catalogFile) {
        try (ZipFile zipFile = new ZipFile(new File(archive))) {
            return zipFile.stream().anyMatch(entry -> entry.getName().equals(catalogFile));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkRelUrlBaseResolution(String fileContent, String baseUrl) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(fileContent.getBytes()));

            NodeList elementsToResolve = document.getElementsByTagName("*");
            for (int i = 0; i < elementsToResolve.getLength(); i++) {
                String href = elementsToResolve.item(i).getAttributes().getNamedItem("xlink:href") != null
                        ? elementsToResolve.item(i).getAttributes().getNamedItem("xlink:href").getNodeValue()
                        : null;
                String xmlBase = elementsToResolve.item(i).getAttributes().getNamedItem("xml:base") != null
                        ? elementsToResolve.item(i).getAttributes().getNamedItem("xml:base").getNodeValue()
                        : null;

                if (href != null) {
                    URI resolvedHref = URI.create(new URI(baseUrl).resolve(href).toString());
                    elementsToResolve.item(i).getAttributes().getNamedItem("xlink:href").setNodeValue(resolvedHref.toString());
                }

                if (xmlBase != null) {
                    URI resolvedBase = URI.create(new URI(baseUrl).resolve(xmlBase).toString());
                    elementsToResolve.item(i).getAttributes().getNamedItem("xml:base").setNodeValue(resolvedBase.toString());
                }
            }
            return true;
        } catch (IOException | ParserConfigurationException | SAXException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEntryPointLocation(String inputDocument) {
        return isTaxonomySchema(inputDocument) || isLinkbaseDocument(inputDocument);
    }

    private boolean isTaxonomySchema(String inputDocument) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(inputDocument.getBytes()));
            NodeList appInfoElems = document.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "appinfo");
            return appInfoElems.getLength() > 0;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.err.println("Error parsing XML for taxonomy schema: " + e.getMessage());
            return false;
        }
    }

    private boolean isLinkbaseDocument(String inputDocument) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(inputDocument.getBytes()));
            NodeList linkbaseElems = document.getElementsByTagNameNS("http://www.xbrl.org/2003/linkbase", "linkbase");
            return linkbaseElems.getLength() > 0;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.err.println("Error parsing XML for linkbase document: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Sample usage
        TPChecker checker = new TPChecker();
        
        // Sample file paths for testing
        String archivePath = "example.zip";
        String schemaFile = "taxonomy-package.xsd";
        String exampleFilePath = "taxonomyPackage.xml";

        System.out.println("Has ZIP format: " + checker.hasZipFormat(archivePath));
        System.out.println("Has single top-level directory: " + checker.hasTopLevelSingleDir(archivePath));
        System.out.println("Validating XML: " + checker.validateXML(schemaFile, new File(exampleFilePath)));
        System.out.println("Has META-INF folder: " + checker.hasMetaInfFolder(archivePath, "META-INF"));
        System.out.println("Has taxonomyPackage.xml: " + checker.hasTaxonomyPackageXml(archivePath, "taxonomyPackage.xml"));
        System.out.println("Has catalog.xml: " + checker.hasCatalogXml(archivePath, "catalog.xml"));
        
        // Example of URL resolution check
        String fileContent = "<?xml version=\"1.0\"?><doc xml:base=\"http://example.org/today/\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"><head><title>Virtual Library</title></head><body><paragraph>See <link xlink:type=\"simple\" xlink:href=\"new.xml\">what's new</link>!</paragraph></body></doc>";
        System.out.println("Check relative URL base resolution: " + checker.checkRelUrlBaseResolution(fileContent, "http://example.org/"));
        
        // Example entry point location check
        System.out.println("Check entry point location: " + checker.checkEntryPointLocation(fileContent));
    }
}
