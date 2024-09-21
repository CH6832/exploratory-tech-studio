import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PackageManager {
    private static final String PKG_JSON_PATH = "packages.json";
    private static final String STORAGE_FOLDER = "storage";
    private static final String INSTALLED_FOLDER = "installed";
    private static final String LOG_FILE = "log.txt";

    private Map<String, PackageInfo> packages = new HashMap<>();

    public PackageManager() {
        loadPackages();
    }

    private void loadPackages() {
        File file = new File(PKG_JSON_PATH);
        if (file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Map<String, String>> root = mapper.readValue(file, Map.class);
            root.forEach((pkg, info) -> packages.put(pkg, new PackageInfo(info)));
        }
    }

    public void savePackages() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(PKG_JSON_PATH), packages);
    }

    public void install(String pkgId) {
        if (!packages.containsKey(pkgId)) {
            log("Package " + pkgId + " not found.", "ERROR");
            System.out.println("Package ID not found.");
            return;
        }

        PackageInfo pkgInfo = packages.get(pkgId);
        String pkgUrl = pkgInfo.get("_pkgurl");

        createDirectory(STORAGE_FOLDER);
        createDirectory(INSTALLED_FOLDER);

        String pkgPath = Paths.get(STORAGE_FOLDER, pkgUrl).toString();
        if (!new File(pkgPath).exists()) {
            log("Package file not found.");
            System.out.println("Package file not found.");
            return;
        }

        String destPath = Paths.get(INSTALLED_FOLDER, pkgUrl).toString();
        if (new File(destPath).exists()) {
            log("Package " + pkgId + " is already installed.");
            System.out.println("Package " + pkgId + " is already installed.");
            return;
        }

        try {
            Files.copy(Paths.get(pkgPath), Paths.get(destPath));
            log("Package " + pkgId + " installed successfully.");
            System.out.println("Package " + pkgId + " installed successfully.");
        } catch (IOException e) {
            log("Failed to install package " + pkgId + ": " + e.getMessage(), "ERROR");
            System.out.println("Failed to install package " + pkgId + ": " + e.getMessage());
        }
    }

    private void log(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'log'");
    }

    public void uninstall(String pkgId) {
        String installedPath = Paths.get(INSTALLED_FOLDER, pkgId + ".zip").toString();
        if (new File(installedPath).exists()) {
            try {
                Files.delete(Paths.get(installedPath));
                log("Package " + pkgId + " uninstalled successfully.");
                System.out.println("Package " + pkgId + " uninstalled successfully.");
            } catch (IOException e) {
                log("Failed to uninstall package " + pkgId + ": " + e.getMessage(), "ERROR");
                System.out.println("Failed to uninstall package " + pkgId + ": " + e.getMessage());
            }
        } else {
            log("Package is not installed.", "ERROR");
            System.out.println("Package is not installed.");
        }
    }

    public void reset() {
        File installedDir = new File(INSTALLED_FOLDER);
        if (installedDir.exists()) {
            try {
                Files.walk(installedDir.toPath())
                        .sorted((path1, path2) -> path2.compareTo(path1))
                        .forEach(path -> path.toFile().delete());
                log("All packages uninstalled.");
                System.out.println("All packages uninstalled.");
            } catch (IOException e) {
                log("Failed to reset package storage: " + e.getMessage(), "ERROR");
                System.out.println("Failed to reset package storage: " + e.getMessage());
            }
        } else {
            log("No packages installed.", "ERROR");
            System.out.println("No packages installed.");
        }
    }

    private void log(String message, String level) {
        try {
            Files.write(Paths.get(LOG_FILE), (level + ": " + message + System.lineSeparator()).getBytes(), 
                        java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Failed to log message: " + e.getMessage());
        }
    }

    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
            log("Folder '" + path + "' created successfully.");
            System.out.println("Folder '" + path + "' created successfully.");
        } else {
            log("Folder '" + path + "' already exists.");
            System.out.println("Folder '" + path + "' already exists.");
        }
    }

    public Object getPackageCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPackageCount'");
    }

    public void addPackage(String string, PackageInfo packageInfo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPackage'");
    }
}
