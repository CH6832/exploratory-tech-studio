import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.util.zip.*;
import org.xml.sax.InputSource;

public class CIPCTaxonomyPackage {

    private String destinationFolder;
    private String sourceFolder;
    private File fullPathToZip;

    public CIPCTaxonomyPackage(String destinationFolder, String sourceFolder, File fullPathToZip) {
        this.destinationFolder = destinationFolder;
        this.sourceFolder = sourceFolder;
        this.fullPathToZip = fullPathToZip;
    }

    public void restructureFolder() {
        removeIntegratedIFRSTaxonomy();
        List<String> allXmlFiles = getFullPathOfAllXmlFiles();
        
        // First round: change URLs from relative to absolute paths
        for (String xmlFile : allXmlFiles) {
            try {
                Document doc = parseXML(xmlFile);
                fixXmlFile(doc, xmlFile);
                writeXML(doc, xmlFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Second round: deleting strings from the rest of the URLs
        for (String xmlFile : allXmlFiles) {
            try {
                Document doc = parseXML(xmlFile);
                deleteUrlStrings(doc, xmlFile);
                writeXML(doc, xmlFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Third round: fixing entries in XSD schema files
        List<String> allXsdFiles = getFullPathOfAllXsdFiles();
        for (String xsdFile : allXsdFiles) {
            try {
                Document xsdDoc = parseXML(xsdFile);
                fixXsdFile(xsdDoc, xsdFile);
                writeXML(xsdDoc, xsdFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void removeIntegratedIFRSTaxonomy() {
        try {
            Path ifrsPath = Paths.get(destinationFolder, "def", "ifrs");
            if (Files.exists(ifrsPath)) {
                Files.walk(ifrsPath)
                     .sorted(Comparator.reverseOrder())
                     .forEach(path -> {
                         try {
                             Files.delete(path);
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getFullPathOfAllXmlFiles() {
        List<String> xmlFiles = new ArrayList<>();
        try {
            Files.walk(Paths.get(destinationFolder))
                .filter(path -> path.toString().endsWith(".xml") && !path.toString().contains("catalog") && !path.toString().contains("taxonomyPackage"))
                .forEach(path -> xmlFiles.add(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlFiles;
    }

    private List<String> getFullPathOfAllXsdFiles() {
        List<String> xsdFiles = new ArrayList<>();
        try {
            Files.walk(Paths.get(destinationFolder))
                .filter(path -> path.toString().endsWith(".xsd") && !path.toString().contains("catalog") && !path.toString().contains("taxonomyPackage"))
                .forEach(path -> xsdFiles.add(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xsdFiles;
    }

    private Document parseXML(String xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new FileInputStream(xmlFile)));
    }

    private void writeXML(Document doc, String xmlFile) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(xmlFile));
        transformer.transform(source, result);
    }

    private void fixXmlFile(Document doc, String xmlFile) {
        NodeList locs = doc.getElementsByTagNameNS("http://www.xbrl.org/2003/linkbase", "loc");
        for (int i = 0; i < locs.getLength(); i++) {
            Element loc = (Element) locs.item(i);
            String hrefValue = loc.getAttributeNS("http://www.w3.org/1999/xlink", "href");

            if (hrefValue.contains("/def/ifrs/full_ifrs") || hrefValue.contains("/def/ifrs/ifrs_for_smes") || hrefValue.contains("/def/ifrs/deprecated")) {
                String affectedXmlFileDate = extractDateFromHref(hrefValue);
                // Replace substrings with absolute paths
                hrefValue = updateHref(hrefValue, affectedXmlFileDate);
                loc.setAttributeNS("http://www.w3.org/1999/xlink", "href", hrefValue);
                System.out.println("Fixed href value in " + xmlFile + ": " + hrefValue);
            }
        }
    }

    private String extractDateFromHref(String hrefValue) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(hrefValue);
        return matcher.find() ? matcher.group() : "";
    }

    private String updateHref(String hrefValue, String affectedXmlFileDate) {
        // Here, we can implement the actual URL changes similar to the Python code
        // Example (not complete; adapt as necessary):
        if (hrefValue.startsWith("../../..")) {
            return hrefValue.replace("../../../def/ifrs", "https://xbrl.ifrs.org/taxonomy/" + affectedXmlFileDate);
        }
        // Other replacements should be added similarly
        return hrefValue; // Default return
    }

    private void deleteUrlStrings(Document doc, String xmlFile) {
        NodeList locs = doc.getElementsByTagNameNS("http://www.xbrl.org/2003/linkbase", "loc");
        for (int i = 0; i < locs.getLength(); i++) {
            Element loc = (Element) locs.item(i);
            String hrefValue = loc.getAttributeNS("http://www.w3.org/1999/xlink", "href");

            if (hrefValue.contains("/full_ifrs/") || hrefValue.contains("/ifrs_for_smes/") || hrefValue.contains("/deprecated/")) {
                // Example of how to delete parts of hrefValue (e.g., replace with an empty string)
                hrefValue = hrefValue.replace("../", "");
                loc.setAttributeNS("http://www.w3.org/1999/xlink", "href", hrefValue);
                System.out.println("Updated href value in " + xmlFile + ": " + hrefValue);
            }
        }
    }

    private void fixXsdFile(Document xsdDoc, String xsdFile) {
        NodeList imports = xsdDoc.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "import");
        for (int i = 0; i < imports.getLength(); i++) {
            Element imp = (Element) imports.item(i);
            String schemaLocation = imp.getAttribute("schemaLocation");
            String affectedXsdFileDate = extractDateFromHref(schemaLocation);
            
            // Perform necessary replacements similar to the Python code
            if (schemaLocation.contains("/full_ifrs/")) {
                schemaLocation = schemaLocation.replace("/full_ifrs/", "https://xbrl.ifrs.org/taxonomy/" + affectedXsdFileDate + "/full_ifrs");
            }
            imp.setAttribute("schemaLocation", schemaLocation);
            System.out.println("Fixed schema location in " + xsdFile + ": " + schemaLocation);
        }
    }
}
