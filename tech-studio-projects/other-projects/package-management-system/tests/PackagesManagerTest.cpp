#include "PackagesManager.h"
#include <gtest/gtest.h>
#include <fstream>

// Fixture for setting up and cleaning up test environment
class PkgManagerTest : public ::testing::Test {
protected:
    // Set up the test fixture
    void SetUp() override {
        // Create a test package JSON file
        std::ofstream testJsonFile(PKG_JSON_PATH);
        testJsonFile << "{ \"test_pkg\": { \"_pkgurl\": \"test_pkg.zip\" } }";
        testJsonFile.close();
    }

    // Tear down the test fixture
    void TearDown() override {
        // Remove the test package JSON file
        std::remove(PKG_JSON_PATH);
        // Remove any installed test packages
        std::remove((INSTALLED_FOLDER + "/test_pkg.zip").c_str());
    }
};

// Test fixture to test PkgManager class
TEST_F(PkgManagerTest, InstallPackage) {
    // Arrange
    PkgManager pkgManager;
    std::string pkgId = "test_pkg";

    // Act
    pkgManager.install(pkgId);

    // Assert
    ASSERT_TRUE(std::filesystem::exists(INSTALLED_FOLDER + "/test_pkg.zip"));
}

// Test fixture to test PkgManager class
TEST_F(PkgManagerTest, UninstallPackage) {
    // Arrange
    PkgManager pkgManager;
    std::string pkgId = "test_pkg";
    pkgManager.install(pkgId);

    // Act
    pkgManager.uninstall(pkgId);

    // Assert
    ASSERT_FALSE(std::filesystem::exists(INSTALLED_FOLDER + "/test_pkg.zip"));
}

// Test fixture to test PkgManager class
TEST_F(PkgManagerTest, ResetPackages) {
    // Arrange
    PkgManager pkgManager;
    std::string pkgId = "test_pkg";
    pkgManager.install(pkgId);

    // Act
    pkgManager.reset();

    // Assert
    ASSERT_FALSE(std::filesystem::exists(INSTALLED_FOLDER + "/test_pkg.zip"));
}

int main(int argc, char** argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
