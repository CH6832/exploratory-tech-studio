package src.helpers; // Update the package name as necessary

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Utils {

    public static void genZipArchive(String folderPath, String zipFilename) throws IOException {
        """Generate a zip archive out of a root input folder."""
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilename)))) {
            Path sourcePath = Paths.get(folderPath);
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    ZipEntry zipEntry = new ZipEntry(sourcePath.relativize(file).toString());
                    zipOutputStream.putNextEntry(zipEntry);
                    Files.copy(file, zipOutputStream);
                    zipOutputStream.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        printColorMsg("Final zip generated", "\u001B[33m"); // Yellow text
    }

    public static void moveFolderRecursively(String sourceFolder, String destinationFolder) throws IOException {
        """Move folder recursively from one destination to another one."""
        Path sourcePath = Paths.get(sourceFolder);
        Path destinationPath = Paths.get(destinationFolder);

        Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetFile = destinationPath.resolve(sourcePath.relativize(file));
                Files.createDirectories(targetFile.getParent());
                Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void extractZipInSameFolder(String zipPath) throws IOException {
        """Extract zip file to destination path."""
        Path zipFilePath = Paths.get(zipPath);
        Path destinationDir = zipFilePath.getParent();

        try (java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(zipFilePath.toFile())) {
            zipFile.stream().forEach(zipEntry -> {
                try {
                    Path entryDestination = destinationDir.resolve(zipEntry.getName());
                    if (zipEntry.isDirectory()) {
                        Files.createDirectories(entryDestination);
                    } else {
                        Files.createDirectories(entryDestination.getParent());
                        Files.copy(zipFile.getInputStream(zipEntry), entryDestination, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    System.err.println("Error extracting file: " + e.getMessage());
                }
            });
        }
    }

    public static void printColorMsg(String msg, String color) {
        """Print a colorized message."""
        System.out.println(color + msg + "\u001B[0m"); // Reset color
    }

    public static void deleteNonZipFilesAndFoldersRecursive(String folderPath) throws IOException {
        """Deletes all non-ZIP files and folders in the specified folder and its subfolders."""
        Path path = Paths.get(folderPath);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!file.getFileName().toString().toLowerCase().endsWith(".zip")) {
                    Files.delete(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (Files.list(dir).count() == 0) {
                    Files.delete(dir);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    // Example usage
    public static void main(String[] args) {
        try {
            // Replace with actual paths for testing
            genZipArchive("path/to/folder", "output.zip");
            moveFolderRecursively("path/to/sourceFolder", "path/to/destinationFolder");
            extractZipInSameFolder("path/to/zipfile.zip");
            printColorMsg("This is a colored message", "\u001B[34m"); // Blue text
            deleteNonZipFilesAndFoldersRecursive("path/to/folder");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
