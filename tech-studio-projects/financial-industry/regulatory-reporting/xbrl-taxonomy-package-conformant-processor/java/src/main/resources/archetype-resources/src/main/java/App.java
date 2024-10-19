package src.app; // Update the package name as necessary

import src.helpers.LoggerHelper; // Assuming you have a LoggerHelper similar to the Python logger
import src.helpers.Utils;
import src.checker.TPChecker;
import src.fixers.EBATaxonomyPackage;
import src.fixers.EDINETTaxonomyPackage;
import src.fixers.CMFCLCITaxonomyPackage;
import src.fixers.CIPCTaxonomyPackage;
import src.enums.Provider;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static LoggerHelper logger;
    
    public static void main(String[] args) {
        logger = LoggerHelper.setupLogger("app", "logs/app.log"); // Adjust the log path as needed

        try {
            String[] parsedArguments = parseArguments(args);
            String provider = parsedArguments[0];
            String packagePath = parsedArguments[1];

            // Print out provider and package info
            printColorMsg("Input information:", "\u001B[34m"); // Blue
            printColorMsg("------------------", "\u001B[34m");
            printColorMsg("    Provider -> " + provider, "\u001B[34m");
            printColorMsg("    Package  -> " + packagePath + "\n", "\u001B[34m");

            TPChecker tpChecker = new TPChecker();
            Path sourceZipPath = Paths.get(packagePath).toAbsolutePath();
            Path destinationFolder = Paths.get(packagePath.replace("input", "output").replace(File.separator + new File(packagePath).getName(), ""));

            // Check the package
            boolean zipFormat = tpChecker.hasZipFormat(packagePath);
            boolean singleDir = zipFormat && tpChecker.hasTopLevelSingleDir(packagePath);
            boolean metaInfDir = tpChecker.hasMetaInfFolder(packagePath) &&
                                  tpChecker.hasCatalogXml(packagePath) &&
                                  tpChecker.hasTaxonomyPackageXml(packagePath);

            checkAndLogPackage(zipFormat, singleDir, metaInfDir);

            // Map provider to the corresponding class
            Map<Provider, Class<?>> providerMap = new HashMap<>();
            providerMap.put(Provider.EBA, EBATaxonomyPackage.class);
            providerMap.put(Provider.EDINET, EDINETTaxonomyPackage.class);
            providerMap.put(Provider.CMFCLCI, CMFCLCITaxonomyPackage.class);
            providerMap.put(Provider.CIPC, CIPCTaxonomyPackage.class);

            // Determine provider enum
            Provider providerEnum;
            try {
                providerEnum = Provider.valueOf(provider.toUpperCase());
            } catch (IllegalArgumentException e) {
                printColorMsg("ERROR: Wrong provider!", "\u001B[31m"); // Red
                logger.error("ERROR: Wrong provider!");
                return;
            }

            printColorMsg("\nFixing package...", "\u001B[33m"); // Yellow
            fixPackage(providerEnum, providerMap.get(providerEnum), sourceZipPath, destinationFolder, zipFormat, singleDir, metaInfDir);

        } catch (Exception e) {
            printColorMsg("Error: " + e.getMessage(), "\u001B[31m"); // Red
            logger.error("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static String[] parseArguments(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Please provide both: Abbreviation of provider and full path to taxonomy package (zip).");
        }
        return args;
    }

    private static void checkAndLogPackage(boolean zipFormat, boolean singleDir, boolean metaInfDir) {
        printColorMsg("    " + (zipFormat ? "DONE" : "ERROR") + ": Package is " + (zipFormat ? "ZIP" : "not ZIP"), zipFormat ? "\u001B[32m" : "\u001B[31m");
        printColorMsg("    " + (singleDir ? "DONE" : "ERROR") + ": Package has " + (singleDir ? "top level dir" : "no single top level dir"), singleDir ? "\u001B[32m" : "\u001B[31m");
        printColorMsg("    " + (metaInfDir ? "DONE" : "ERROR") + ": Package has " + (metaInfDir ? "META-INF folder" : "no META-INF folder or required files"), metaInfDir ? "\u001B[32m" : "\u001B[31m");
    }

    private static void fixPackage(Provider providerName, Class<?> packageClass, Path sourceZipPath, Path destinationFolder, boolean zipFormat, boolean singleDir, boolean metaInfDir) {
        try {
            Object fixer = packageClass.getDeclaredConstructor(Path.class, Path.class).newInstance(sourceZipPath, destinationFolder);

            // Apply fixes based on the results of the checks
            if (!zipFormat) {
                packageClass.getMethod("convertToZipArchive").invoke(fixer);
            }
            if (!metaInfDir) {
                packageClass.getMethod("fixMetaInfFolder").invoke(fixer);
            }
            if (!singleDir) {
                packageClass.getMethod("fixTopLevelSingleDir").invoke(fixer);
            }

            // Restructure the package folder and regenerate necessary XML files
            packageClass.getMethod("restructureFolder").invoke(fixer);
            packageClass.getMethod("fixCatalogXml").invoke(fixer);
            packageClass.getMethod("fixTaxonomyPackageXml").invoke(fixer);

            // Generate the final ZIP archive in the destination folder
            String fullPathToZip = sourceZipPath.toString().replace("input", "output");
            Utils.genZipArchive(destinationFolder.toString(), fullPathToZip);

            // Clean up temporary directories
            Utils.deleteNonZipFilesAndFoldersRecursive(destinationFolder.toString());

            // Output the result to the user
            printColorMsg("\nOutput result:", "\u001B[34m"); // Blue
            printColorMsg("    " + new File(fullPathToZip).getName() + " is fixed", "\u001B[34m");
            logger.info("Output result: " + new File(fullPathToZip).getName());

        } catch (Exception e) {
            printColorMsg("Error fixing package: " + e.getMessage(), "\u001B[31m"); // Red
            logger.error("Error fixing package: " + e.getMessage());
        }
    }

    private static void printColorMsg(String msg, String color) {
        System.out.println(color + msg + "\u001B[0m"); // Reset color
    }
}
