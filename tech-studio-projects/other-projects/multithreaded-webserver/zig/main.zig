const std = @import("std");

pub fn main() !void {
    const allocator = std.heap.page_allocator;

    // Create TCP listener on port 8088
    const listener = try std.net.StreamServer.listen(std.net.AddressFamily.ipv4, 8088);
    defer listener.close();

    // Create a thread pool with 4 workers
    var pool = try ThreadPool.init(allocator, 4);
    defer pool.deinit();

    std.debug.print("Server listening on http://127.0.0.1:8088\n", .{});

    while (true) {
        // Accept new TCP connections
        var conn = try listener.accept(allocator);
        pool.schedule(handle_connection, &conn) catch |err| {
            std.debug.print("Error handling connection: {}\n", .{err});
            conn.close();
        };
    }
}

fn handle_connection(conn: *std.net.StreamServer.AcceptConnection) void {
    defer conn.close();

    // Read the incoming request
    var buffer: [1024]u8 = undefined;
    var reader = conn.reader();
    const bytes_read = reader.readAll(&buffer) catch |err| {
        std.debug.print("Error reading request: {}\n", .{err});
        return;
    };

    // Very basic request parsing (only looking at the first line)
    const request_line = try std.mem.tokenize(buffer[0..bytes_read], " ").next();
    if (request_line == "GET") {
        // Send a simple HTTP response
        var writer = conn.writer();
        try writer.print(
            "HTTP/1.1 200 OK\r\n" ++
            "Content-Type: text/plain\r\n" ++
            "Content-Length: 13\r\n\r\n" ++
            "Hello, World!",
            .{}
        );
    } else {
        std.debug.print("Unsupported request: {}\n", .{request_line});
    }
}

const ThreadPool = struct {
    workers: []std.Thread,
    allocator: *std.mem.Allocator,

    pub fn init(allocator: *std.mem.Allocator, num_threads: usize) !ThreadPool {
        var workers = try allocator.alloc(std.Thread, num_threads);
        var pool = ThreadPool{
            .workers = workers,
            .allocator = allocator,
        };

        for (workers) |*worker, i| {
            worker.* = try std.Thread.spawn(pool.worker_fn, i);
        }

        return pool;
    }

    pub fn deinit(self: *ThreadPool) void {
        for (self.workers) |*worker| {
            worker.wait() catch {};
        }
        self.allocator.free(self.workers);
    }

    fn worker_fn(arg: usize) void {
        std.debug.print("Worker thread {} started\n", .{arg});
    }

    pub fn schedule(func: fn(*std.net.StreamServer.AcceptConnection) void, arg: *std.net.StreamServer.AcceptConnection) !void {
        try std.Thread.spawn(func, arg);
    }
};
