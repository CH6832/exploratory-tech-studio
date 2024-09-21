use serde::{Deserialize, Serialize};
use std::collections::HashMap;
use std::fs::{self, File};
use std::io::{self, Write};
use std::path::{Path, PathBuf};

const PKG_JSON_PATH: &str = "packages.json";
const STORAGE_FOLDER: &str = "storage";
const INSTALLED_FOLDER: &str = "installed";
const LOG_FILE: &str = "log.txt";

#[derive(Serialize, Deserialize, Debug)]
struct PackageInfo(HashMap<String, String>);

struct PkgManager {
    packages: HashMap<String, PackageInfo>,
}

impl PkgManager {
    fn new() -> Self {
        let mut pm = PkgManager {
            packages: HashMap::new(),
        };
        pm.load_packages();
        pm
    }

    fn load_packages(&mut self) {
        if Path::new(PKG_JSON_PATH).exists() {
            let file = File::open(PKG_JSON_PATH).unwrap();
            self.packages = serde_json::from_reader(file).unwrap_or_default();
        }
    }

    fn save_packages(&self) {
        let file = File::create(PKG_JSON_PATH).unwrap();
        serde_json::to_writer(file, &self.packages).unwrap();
    }

    fn install(&mut self, pkg_id: &str) {
        let pkg_info = match self.packages.get(pkg_id) {
            Some(info) => info,
            None => {
                self.log(&format!("Package {} not found.", pkg_id));
                println!("Package ID not found.");
                return;
            }
        };

        let pkg_url = &pkg_info.0["_pkgurl"];
        self.create_directory(STORAGE_FOLDER);
        self.create_directory(INSTALLED_FOLDER);

        let pkg_path = PathBuf::from(STORAGE_FOLDER).join(pkg_url);
        if !pkg_path.exists() {
            self.log("Package file not found.");
            println!("Package file not found.");
            return;
        }

        let dest_path = PathBuf::from(INSTALLED_FOLDER).join(pkg_url);
        if dest_path.exists() {
            self.log(&format!("Package {} is already installed.", pkg_id));
            println!("Package {} is already installed.", pkg_id);
            return;
        }

        fs::copy(&pkg_path, &dest_path).unwrap_or_else(|err| {
            self.log(&format!("Failed to install package {}: {}", pkg_id, err));
            println!("Failed to install package {}: {}", pkg_id, err);
        });

        self.log(&format!("Package {} installed successfully.", pkg_id));
        println!("Package {} installed successfully.", pkg_id);
    }

    fn uninstall(&mut self, pkg_id: &str) {
        let installed_path = PathBuf::from(INSTALLED_FOLDER).join(format!("{}.zip", pkg_id));
        if installed_path.exists() {
            fs::remove_file(installed_path).unwrap_or_else(|err| {
                self.log(&format!("Failed to uninstall package {}: {}", pkg_id, err));
                println!("Failed to uninstall package {}: {}", pkg_id, err);
            });
            self.log(&format!("Package {} uninstalled successfully.", pkg_id));
            println!("Package {} uninstalled successfully.", pkg_id);
        } else {
            self.log("Package is not installed.");
            println!("Package is not installed.");
        }
    }

    fn reset(&mut self) {
        if Path::new(INSTALLED_FOLDER).exists() {
            fs::remove_dir_all(INSTALLED_FOLDER).unwrap_or_else(|err| {
                self.log(&format!("Failed to reset package storage: {}", err));
                println!("Failed to reset package storage: {}", err);
            });
            self.log("All packages uninstalled.");
            println!("All packages uninstalled.");
        } else {
            self.log("No packages installed.");
            println!("No packages installed.");
        }
    }

    fn log(&self, message: &str) {
        let mut log_file = OpenOptions::new()
            .append(true)
            .create(true)
            .open(LOG_FILE)
            .unwrap();
        writeln!(log_file, "{}", message).unwrap();
    }

    fn create_directory(&self, path: &str) {
        if !Path::new(path).exists() {
            fs::create_dir_all(path).unwrap();
            println!("Folder '{}' created successfully.", path);
        } else {
            println!("Folder '{}' already exists.", path);
        }
    }
}