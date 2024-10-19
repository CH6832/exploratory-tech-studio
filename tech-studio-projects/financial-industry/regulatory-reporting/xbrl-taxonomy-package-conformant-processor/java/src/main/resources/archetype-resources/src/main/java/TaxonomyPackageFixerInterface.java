package src.fixer; // Update the package name as necessary

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public abstract class TaxonomyPackageFixerInterface {
    /**
     * The Interface provides methods to fix an XBRL Taxonomy Package by a certain provider.
     */

    protected String fullPathToZip;
    protected String destinationFolder;

    public TaxonomyPackageFixerInterface(String fullPathToZip, String destinationFolder) throws IOException {
        this.fullPathToZip = fullPathToZip;
        this.destinationFolder = destinationFolder;

        // Create destination folder
        Files.createDirectories(Paths.get(this.destinationFolder));

        // Move taxonomy package to destination folder
        Path sourcePath = Paths.get(fullPathToZip);
        Path destinationPath = Paths.get(destinationFolder, sourcePath.getFileName().toString());
        Files.move(sourcePath, destinationPath);

        // Extract the ZIP file at the destination
        try {
            ZipFile zipFile = new ZipFile(destinationPath.toFile());
            zipFile.stream().forEach(entry -> {
                try {
                    Files.copy(zipFile.getInputStream(entry), Paths.get(destinationFolder, entry.getName()));
                } catch (IOException e) {
                    System.err.println("An error occurred while extracting the file: " + e.getMessage());
                }
            });
            System.out.println("Extracted " + fullPathToZip + " to " + destinationFolder);
        } catch (ZipException e) {
            System.err.println("Error: The file " + fullPathToZip + " is not a valid ZIP archive.");
        } catch (IOException e) {
            System.err.println("Error: Permission denied or I/O error during extraction to " + destinationFolder + ".");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public abstract void convertToZipArchive();

    public abstract void fixTopLevelSingleDir();

    public abstract void fixMetaInfFolder();

    public abstract void restructureFolder();

    public abstract void fixTaxonomyPackageXml(String sourceFolder);

    public abstract void fixCatalogXml(String sourceFolder);
}
