const std = @import("std");
const PkgManager = @import("pkg_manager.zig").PkgManager;

pub fn main() !void {
    const allocator = std.heap.page_allocator;
    var pkgManager = try PkgManager.init(allocator);

    const args = std.process.args();
    if (args.len < 2) {
        std.debug.print("Usage: {} <command> [package_id]\n", .{args[0]});
        std.debug.print("Commands: install, uninstall, reset\n", .{});
        return;
    }

    const command = args[1];

    if (command == "install") {
        if (args.len < 3) {
            std.debug.print("Usage: {} install <package_id>\n", .{args[0]});
            return;
        }
        const pkgId = args[2];
        try pkgManager.install(pkgId);
    } else if (command == "uninstall") {
        if (args.len < 3) {
            std.debug.print("Usage: {} uninstall <package_id>\n", .{args[0]});
            return;
        }
        const pkgId = args[2];
        try pkgManager.uninstall(pkgId);
    } else if (command == "reset") {
        try pkgManager.reset();
    } else {
        std.debug.print("Unknown command: {}\n", .{command});
        std.debug.print("Commands: install, uninstall, reset\n", .{});
    }
}
