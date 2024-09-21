const std = @import("std");
const PkgManager = @import("../src/pkg_manager.zig").PkgManager;

const test_dir = "test_dir";
const test_log_file = "test_log.txt";
const test_pkg_json = "test_packages.json";
const test_storage_folder = "test_storage";
const test_installed_folder = "test_installed";

fn cleanup() void {
    const fs = std.fs;
    const allocator = std.heap.page_allocator;

    _ = fs.removeDirAll(test_dir);
    _ = fs.createDir(test_dir);
}

fn setup() !PkgManager {
    cleanup();
    const allocator = std.heap.page_allocator;
    return try PkgManager.init(allocator);
}

test "load packages from an empty file" {
    const pm = try setup();
    try pm.loadPackages();
    // Verify that no packages are loaded
    std.debug.assert(pm.packages.len == 0);
}

test "save packages to a JSON file" {
    const pm = try setup();
    const pkgInfo = PkgManager.PackageInfo{ .url = "sample_package.zip" };
    try pm.packages.put("sample_package", pkgInfo);
    try pm.savePackages();

    // Verify the JSON file exists and contains the expected data
    const file = try std.fs.File.openRead(test_pkg_json);
    defer file.close();

    const content = try file.readToEndAlloc(std.heap.page_allocator, 4096);
    std.debug.assert(content.len > 0); // Ensure the file is not empty
    // Further validation of content can be implemented here
}

test "install a package" {
    const pm = try setup();
    const pkgInfo = PkgManager.PackageInfo{ .url = "sample_package.zip" };
    try pm.packages.put("sample_package", pkgInfo);
    // Create a sample package file in the storage folder
    try std.fs.createDir(test_storage_folder);
    const packagePath = std.fs.path.join(test_storage_folder, "sample_package.zip");
    const file = try std.fs.File.openWrite(packagePath, .{ .create = true });
    defer file.close();

    try pm.install("sample_package");

    // Verify that the package is installed
    const installedPath = std.fs.path.join(test_installed_folder, "sample_package.zip");
    std.debug.assert(std.fs.exists(installedPath));
}

test "uninstall a package" {
    const pm = try setup();
    const pkgInfo = PkgManager.PackageInfo{ .url = "sample_package.zip" };
    try pm.packages.put("sample_package", pkgInfo);
    try std.fs.createDir(test_storage_folder);
    const packagePath = std.fs.path.join(test_storage_folder, "sample_package.zip");
    const file = try std.fs.File.openWrite(packagePath, .{ .create = true });
    defer file.close();

    try pm.install("sample_package");
    try pm.uninstall("sample_package");

    // Verify that the package is uninstalled
    const installedPath = std.fs.path.join(test_installed_folder, "sample_package.zip");
    std.debug.assert(!std.fs.exists(installedPath));
}

test "reset all packages" {
    const pm = try setup();
    const pkgInfo = PkgManager.PackageInfo{ .url = "sample_package.zip" };
    try pm.packages.put("sample_package", pkgInfo);
    try std.fs.createDir(test_storage_folder);
    const packagePath = std.fs.path.join(test_storage_folder, "sample_package.zip");
    const file = try std.fs.File.openWrite(packagePath, .{ .create = true });
    defer file.close();

    try pm.install("sample_package");
    try pm.reset();

    // Verify that all packages are removed
    std.debug.assert(!std.fs.exists(test_installed_folder));
}
