package main

import (
	"encoding/json"
	"io/ioutil"
	"os"
	"path/filepath"
	"strings"
	"testing"
)

const (
	PKG_JSON_PATH = "packages.json"
	LOG_FILE      = "log.txt"
)

func TestLoadPackages_EmptyFile_NoPackagesLoaded(t *testing.T) {
	os.Remove(PKG_JSON_PATH)
	pm := NewPkgManager()
	if len(pm.packages) != 0 {
		t.Errorf("Expected no packages loaded, got %d", len(pm.packages))
	}
}

func TestSavePackages_AddPackages_PackagesSavedCorrectly(t *testing.T) {
	pm := NewPkgManager()
	pm.packages["example"] = PackageInfo{"_pkgurl": "example.zip", "version": "1.0"}
	pm.savePackages()

	data, err := ioutil.ReadFile(PKG_JSON_PATH)
	if err != nil {
		t.Fatal(err)
	}

	var savedPackages map[string]PackageInfo
	json.Unmarshal(data, &savedPackages)

	if _, exists := savedPackages["example"]; !exists {
		t.Error("Expected package 'example' to be saved")
	}
	if savedPackages["example"]["_pkgurl"] != "example.zip" {
		t.Error("Expected package URL to be 'example.zip'")
	}
}

func TestInstall_PackageNotFound_ErrorLogged(t *testing.T) {
	pm := NewPkgManager()
	pm.Install("nonexistent")
	logContent, _ := ioutil.ReadFile(LOG_FILE)
	if !contains(string(logContent), "Package nonexistent not found.") {
		t.Error("Expected error log for nonexistent package")
	}
}

func TestInstall_ValidPackage_PackageInstalled(t *testing.T) {
	pm := NewPkgManager()
	pm.packages["example"] = PackageInfo{"_pkgurl": "example.zip"}
	os.MkdirAll(STORAGE_FOLDER, os.ModePerm)
	ioutil.WriteFile(filepath.Join(STORAGE_FOLDER, "example.zip"), []byte("dummy data"), 0644)

	pm.Install("example")
	if _, err := os.Stat(filepath.Join(INSTALLED_FOLDER, "example.zip")); os.IsNotExist(err) {
		t.Error("Expected package to be installed")
	}
}

func TestUninstall_InstalledPackage_PackageRemoved(t *testing.T) {
	pm := NewPkgManager()
	pm.packages["example"] = PackageInfo{"_pkgurl": "example.zip"}
	os.MkdirAll(STORAGE_FOLDER, os.ModePerm)
	ioutil.WriteFile(filepath.Join(STORAGE_FOLDER, "example.zip"), []byte("dummy data"), 0644)
	pm.Install("example")

	pm.Uninstall("example")
	if _, err := os.Stat(filepath.Join(INSTALLED_FOLDER, "example.zip")); !os.IsNotExist(err) {
		t.Error("Expected package to be uninstalled")
	}
}

func TestReset_InstalledPackages_AllPackagesRemoved(t *testing.T) {
	pm := NewPkgManager()
	pm.packages["example"] = PackageInfo{"_pkgurl": "example.zip"}
	os.MkdirAll(STORAGE_FOLDER, os.ModePerm)
	ioutil.WriteFile(filepath.Join(STORAGE_FOLDER, "example.zip"), []byte("dummy data"), 0644)
	pm.Install("example")

	pm.Reset()
	if _, err := os.Stat(INSTALLED_FOLDER); !os.IsNotExist(err) {
		t.Error("Expected all packages to be removed")
	}
}

func contains(s, substr string) bool {
	return strings.Contains(s, substr)
}
