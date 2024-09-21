package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
)

const (
	PKG_JSON_PATH    = "packages.json"
	STORAGE_FOLDER   = "storage"
	INSTALLED_FOLDER = "installed"
	LOG_FILE         = "log.txt"
)

type PackageInfo map[string]string

type PkgManager struct {
	packages map[string]PackageInfo
}

func NewPkgManager() *PkgManager {
	pm := &PkgManager{packages: make(map[string]PackageInfo)}
	pm.loadPackages()
	return pm
}

func (pm *PkgManager) loadPackages() {
	file, err := os.Open(PKG_JSON_PATH)
	if err == nil {
		defer file.Close()
		json.NewDecoder(file).Decode(&pm.packages)
	}
}

func (pm *PkgManager) savePackages() {
	data, err := json.Marshal(pm.packages)
	if err != nil {
		pm.log("Failed to save packages: " + err.Error())
		return
	}
	ioutil.WriteFile(PKG_JSON_PATH, data, 0644)
}

func (pm *PkgManager) Install(pkgID string) {
	pkgInfo, exists := pm.packages[pkgID]
	if !exists {
		pm.log(fmt.Sprintf("Package %s not found.", pkgID))
		fmt.Println("Package ID not found.")
		return
	}

	pkgUrl := pkgInfo["_pkgurl"]
	createDirectory(STORAGE_FOLDER)
	createDirectory(INSTALLED_FOLDER)

	pkgPath := filepath.Join(STORAGE_FOLDER, pkgUrl)
	if _, err := os.Stat(pkgPath); os.IsNotExist(err) {
		pm.log("Package file not found.")
		fmt.Println("Package file not found.")
		return
	}

	destPath := filepath.Join(INSTALLED_FOLDER, pkgUrl)
	if _, err := os.Stat(destPath); !os.IsNotExist(err) {
		pm.log(fmt.Sprintf("Package %s is already installed.", pkgID))
		fmt.Println("Package " + pkgID + " is already installed.")
		return
	}

	err := os.Link(pkgPath, destPath)
	if err != nil {
		pm.log(fmt.Sprintf("Failed to install package %s: %s", pkgID, err))
		fmt.Println("Failed to install package " + pkgID + ": " + err.Error())
		return
	}

	pm.log(fmt.Sprintf("Package %s installed successfully.", pkgID))
	fmt.Println("Package " + pkgID + " installed successfully.")
}

func (pm *PkgManager) Uninstall(pkgID string) {
	installedPath := filepath.Join(INSTALLED_FOLDER, pkgID+".zip")
	if _, err := os.Stat(installedPath); err == nil {
		err := os.Remove(installedPath)
		if err != nil {
			pm.log(fmt.Sprintf("Failed to uninstall package %s: %s", pkgID, err))
			fmt.Println("Failed to uninstall package " + pkgID + ": " + err.Error())
			return
		}
		pm.log(fmt.Sprintf("Package %s uninstalled successfully.", pkgID))
		fmt.Println("Package " + pkgID + " uninstalled successfully.")
	} else {
		pm.log("Package is not installed.")
		fmt.Println("Package is not installed.")
	}
}

func (pm *PkgManager) Reset() {
	if _, err := os.Stat(INSTALLED_FOLDER); err == nil {
		err := os.RemoveAll(INSTALLED_FOLDER)
		if err != nil {
			pm.log("Failed to reset package storage: " + err.Error())
			fmt.Println("Failed to reset package storage: " + err.Error())
			return
		}
		pm.log("All packages uninstalled.")
		fmt.Println("All packages uninstalled.")
	} else {
		pm.log("No packages installed.")
		fmt.Println("No packages installed.")
	}
}

func (pm *PkgManager) log(message string) {
	f, err := os.OpenFile(LOG_FILE, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		log.Println("Failed to log message:", err)
		return
	}
	defer f.Close()
	f.WriteString(message + "\n")
}

func createDirectory(path string) {
	if _, err := os.Stat(path); os.IsNotExist(err) {
		os.MkdirAll(path, os.ModePerm)
		log.Printf("Folder '%s' created successfully.", path)
	} else {
		log.Printf("Folder '%s' already exists.", path)
	}
}
