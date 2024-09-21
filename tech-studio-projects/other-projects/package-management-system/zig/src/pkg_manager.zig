const std = @import("std");
const fs = std.fs;

const PKG_JSON_PATH = "packages.json";
const STORAGE_FOLDER = "storage";
const INSTALLED_FOLDER = "installed";
const LOG_FILE = "log.txt";

pub const PackageInfo = struct {
    url: []const u8,
};

pub const PkgManager = struct {
    packages: std.AutoHashMap([]const u8, PackageInfo),
    allocator: *std.mem.Allocator,

    pub fn init(allocator: *std.mem.Allocator) !PkgManager {
        var pm = PkgManager{
            .packages = try std.AutoHashMap([]const u8, PackageInfo).init(allocator),
            .allocator = allocator,
        };
        try pm.loadPackages();
        return pm;
    }

    fn loadPackages(self: *PkgManager) !void {
        if (fs.exists(PKG_JSON_PATH)) {
            const file = try fs.File.openRead(PKG_JSON_PATH);
            defer file.close();
            // Load and parse JSON
            // (Implementation of JSON parsing is omitted for brevity)
        }
    }

    pub fn savePackages(self: *PkgManager) !void {
        const file = try fs.File.openWrite(PKG_JSON_PATH, .{ .create = true, .truncate = true });
        defer file.close();
        // Serialize and save packages to JSON
        // (Implementation of JSON serialization is omitted for brevity)
    }

    pub fn install(self: *PkgManager, pkgId: []const u8) !void {
        const pkgInfo = try self.packages.get(pkgId);
        const pkgUrl = pkgInfo.url;

        try self.createDirectory(STORAGE_FOLDER);
        try self.createDirectory(INSTALLED_FOLDER);

        const pkgPath = std.fs.path.join(STORAGE_FOLDER, pkgUrl);
        if (!fs.exists(pkgPath)) {
            self.log("Package file not found.");
            return error.PackageFileNotFound;
        }

        const destPath = std.fs.path.join(INSTALLED_FOLDER, pkgUrl);
        if (fs.exists(destPath)) {
            self.log("Package is already installed.");
            return error.PackageAlreadyInstalled;
        }

        try fs.copyFile(pkgPath, destPath);
        self.log("Package installed successfully.");
    }

    pub fn uninstall(self: *PkgManager, pkgId: []const u8) !void {
        const installedPath = std.fs.path.join(INSTALLED_FOLDER, pkgId);
        if (fs.exists(installedPath)) {
            try fs.remove(installedPath);
            self.log("Package uninstalled successfully.");
        } else {
            self.log("Package is not installed.");
        }
    }

    pub fn reset(self: *PkgManager) !void {
        if (fs.exists(INSTALLED_FOLDER)) {
            try fs.removeDirAll(INSTALLED_FOLDER);
            self.log("All packages uninstalled.");
        } else {
            self.log("No packages installed.");
        }
    }

    fn createDirectory(self: *PkgManager, path: []const u8) !void {
        if (!fs.exists(path)) {
            try fs.createDir(path);
            self.log("Folder created successfully.");
        } else {
            self.log("Folder already exists.");
        }
    }

    fn log(self: *PkgManager, message: []const u8) void {
        const logFile = try fs.File.openAppend(LOG_FILE);
        try logFile.writeAll(message);
        try logFile.writeAll("\n");
    }
};
