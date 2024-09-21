#[cfg(test)]
mod tests {
    use super::*;
    use std::fs::{self, File};
    use std::io::Write;
    use tempfile::tempdir;

    #[test]
    fn load_packages_empty_file_no_packages_loaded() {
        let _dir = tempdir().unwrap();
        let pkg_manager = PkgManager::new();
        assert!(pkg_manager.packages.is_empty());
    }

    #[test]
    fn save_packages_add_packages_packages_saved_correctly() {
        let _dir = tempdir().unwrap();
        let mut pkg_manager = PkgManager::new();
        pkg_manager.packages.insert(
            "example".to_string(),
            PackageInfo(HashMap::from([("_pkgurl".to_string(), "example.zip".to_string()), ("version".to_string(), "1.0".to_string())])),
        );
        pkg_manager.save_packages();

        let file = File::open(PKG_JSON_PATH).unwrap();
        let packages: HashMap<String, PackageInfo> = serde_json::from_reader(file).unwrap();
        assert!(packages.contains_key("example"));
        assert_eq!(packages["example"].0["_pkgurl"], "example.zip");
    }

    #[test]
    fn install_package_not_found_error_logged() {
        let _dir = tempdir().unwrap();
        let mut pkg_manager = PkgManager::new();
        pkg_manager.install("nonexistent");
        let log_content = fs::read_to_string(LOG_FILE).unwrap();
        assert!(log_content.contains("Package nonexistent not found."));
    }

    #[test]
    fn install_valid_package_package_installed() {
        let _dir = tempdir().unwrap();
        let mut pkg_manager = PkgManager::new();
        pkg_manager.packages.insert("example".to_string(), PackageInfo(HashMap::from([("_pkgurl".to_string(), "example.zip".to_string())])));
        fs::create_dir_all(STORAGE_FOLDER).unwrap();
        fs::write(Path::new(STORAGE_FOLDER).join("example.zip"), "dummy data").unwrap();

        pkg_manager.install("example");
        assert!(Path::new(INSTALLED_FOLDER).join("example.zip").exists());
    }

    #[test]
    fn uninstall_installed_package_package_removed() {
        let _dir = tempdir().unwrap();
        let mut pkg_manager = PkgManager::new();
        pkg_manager.packages.insert("example".to_string(), PackageInfo(HashMap::from([("_pkgurl".to_string(), "example.zip".to_string())])));
        fs::create_dir_all(STORAGE_FOLDER).unwrap();
        fs::write(Path::new(STORAGE_FOLDER).join("example.zip"), "dummy data").unwrap();
        pkg_manager.install("example");

        pkg_manager.uninstall("example");
        assert!(!Path::new(INSTALLED_FOLDER).join("example.zip").exists());
    }

    #[test]
    fn reset_installed_packages_all_packages_removed() {
        let _dir = tempdir().unwrap();
        let mut pkg_manager = PkgManager::new();
        pkg_manager.packages.insert("example".to_string(), PackageInfo(HashMap::from([("_pkgurl".to_string(), "example.zip".to_string())])));
        fs::create_dir_all(STORAGE_FOLDER).unwrap();
        fs::write(Path::new(STORAGE_FOLDER).join("example.zip"), "dummy data").unwrap();
        pkg_manager.install("example");

        pkg_manager.reset();
        assert!(!Path::new(INSTALLED_FOLDER).exists());
    }
}
