package src.fixers; // Adjust the package as needed

import org.junit.jupiter.api.*;
import src.fixers.EBATaxonomyPackage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class TestEBATaxonomyPackage {

    private static String fullPathToZip;
    private static String destinationFolder;

    @BeforeAll
    static void setup() {
        // Set up a temporary ZIP file for testing
        fullPathToZip = "data/EBAFixerTest/test_convert_to_zip_archive/data/example_0/example_0.zip";
        destinationFolder = "data/EBAFixerTest/test_convert_to_zip_archive/data/example_1";
        
        try {
            createZipFile(fullPathToZip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void cleanUp() {
        // Cleanup the destination folder after each test
        try {
            deleteDirectory(Paths.get(destinationFolder));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConvertToZipArchive() {
        EBATaxonomyPackage ebaPackage = new EBATaxonomyPackage(Paths.get(fullPathToZip), Paths.get(destinationFolder));
        ebaPackage.convertToZipArchive();
        // Assertions can be added to verify the result if needed
    }

    @Test
    void testFixMetaInfFolder() {
        EBATaxonomyPackage ebaPackage = new EBATaxonomyPackage(Paths.get(fullPathToZip), Paths.get(destinationFolder));
        ebaPackage.fixMetaInfFolder();
    }

    @Test
    void testFixTopLevelSingleDir() {
        String singleDirZip = "data/EBAFixerTest/test_fix_top_level_single_dir/example_0.zip";
        EBATaxonomyPackage ebaPackage = new EBATaxonomyPackage(Paths.get(singleDirZip), Paths.get(destinationFolder));
        ebaPackage.fixTopLevelSingleDir();
    }

    @Test
    void testRestructureFolder() {
        String restructureZip = "data/EBAFixerTest/test_fix_top_level_single_dir/example_0.zip";
        EBATaxonomyPackage ebaPackage = new EBATaxonomyPackage(Paths.get(restructureZip), Paths.get(destinationFolder));
        ebaPackage.restructureFolder();
    }

    @Test
    void testFixTaxonomyPackageXml() {
        String xmlZip = "data/EBAFixerTest/test_fix_top_level_single_dir/example_0.zip";
        EBATaxonomyPackage ebaPackage = new EBATaxonomyPackage(Paths.get(xmlZip), Paths.get(destinationFolder));
        ebaPackage.fixTaxonomyPackageXml();
    }

    @Test
    void testFixCatalogXml() {
        String catalogZip = "data/example_0.zip";
        EBATaxonomyPackage ebaPackage = new EBATaxonomyPackage(Paths.get(catalogZip), Paths.get(destinationFolder));
        ebaPackage.fixCatalogXml();
    }

    private static void createZipFile(String zipFilePath) throws Exception {
        Path zipPath = Paths.get(zipFilePath);
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            // Create a dummy entry in the zip file for testing
            ZipEntry entry = new ZipEntry("dummy.txt");
            zipOut.putNextEntry(entry);
            zipOut.write("This is a dummy file for testing.".getBytes());
            zipOut.closeEntry();
        }
    }

    private static void deleteDirectory(Path path) throws Exception {
        if (Files.exists(path)) {
            Files.walk(path)
                 .sorted((a, b) -> b.compareTo(a)) // Sort the paths for deletion in reverse order
                 .map(Path::toFile)
                 .forEach(File::delete);
        }
    }
}
