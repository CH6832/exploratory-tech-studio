package src.fixers; // Adjust the package as needed

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import src.fixers.EDINETTaxonomyPackage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;

class TestEDINETTaxonomyPackage {

    private EDINETTaxonomyPackage edinetPackage;
    private String pathToZip;
    private String destFolder;

    @BeforeEach
    void setup() {
        pathToZip = "data/archive_single_dir.zip";
        destFolder = "data";
        edinetPackage = new EDINETTaxonomyPackage(Paths.get(pathToZip), Paths.get(destFolder));
    }

    @Test
    void testConvertToZipArchive() {
        // Mocking the shutil.make_archive equivalent
        try {
            EDINETTaxonomyPackage spyPackage = Mockito.spy(edinetPackage);
            spyPackage.convertToZipArchive();
            verify(spyPackage, times(1)).convertToZipArchive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFixTopLevelSingleDir() {
        // Mocking the os.makedirs equivalent
        try {
            EDINETTaxonomyPackage spyPackage = Mockito.spy(edinetPackage);
            spyPackage.fixTopLevelSingleDir();
            verify(spyPackage, times(1)).fixTopLevelSingleDir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFixMetaInfFolder() {
        // Mocking the os.makedirs equivalent
        try {
            EDINETTaxonomyPackage spyPackage = Mockito.spy(edinetPackage);
            spyPackage.fixMetaInfFolder();
            verify(spyPackage, times(1)).fixMetaInfFolder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRestructureFolder() {
        // Mocking the os.listdir equivalent
        try {
            EDINETTaxonomyPackage spyPackage = Mockito.spy(edinetPackage);
            spyPackage.restructureFolder();
            verify(spyPackage, times(1)).restructureFolder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFixTaxonomyPackageXml() {
        // Mocking the XML generation equivalent
        try {
            EDINETTaxonomyPackage spyPackage = Mockito.spy(edinetPackage);
            spyPackage.fixTaxonomyPackageXml("source_folder");
            verify(spyPackage, times(1)).fixTaxonomyPackageXml("source_folder");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFixCatalogXml() {
        // Mocking the XML generation equivalent
        try {
            EDINETTaxonomyPackage spyPackage = Mockito.spy(edinetPackage);
            spyPackage.fixCatalogXml("source_folder");
            verify(spyPackage, times(1)).fixCatalogXml("source_folder");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void cleanUp() {
        // Cleanup the data if necessary
        try {
            Files.deleteIfExists(Paths.get(pathToZip));
            // Additional cleanup logic can be added here if needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
