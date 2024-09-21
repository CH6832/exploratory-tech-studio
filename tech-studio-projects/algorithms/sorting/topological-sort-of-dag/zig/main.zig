const std = @import("std");

pub fn main() void {
    var g = Graph.init(6);
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);

    std.debug.print("Following is a Topological Sort of the given graph\n", .{});
    g.topologicalSort();
}

pub fn Graph.init(vertices: usize) Graph {
    return Graph{
        .graph = [_][]u32{0} ** vertices,
        .V = vertices,
    };
}

pub fn (g *Graph) addEdge(u: u32, v: u32) void {
    g.graph[u] |= v;
}

pub fn (g *Graph) topologicalSort() void {
    var visited = [_]bool{false} ** g.V;
    var stack = []u32{};

    for (g.V).times |i| {
        if (!visited[i]) {
            g.topologicalSortUtil(i, &visited, &stack);
        }
    }

    var buf: [][0]u8 = .{};
    std.mem.zero(buf);

    std.debug.print("{}", .{stack.toSlice(buf)});
}

pub fn (g *Graph) topologicalSortUtil(v: u32, visited: *[_]bool, stack: *[]u32) void {
    visited[v] = true;

    for (g.graph[v]) |i| {
        if (!visited[i]) {
            g.topologicalSortUtil(i, visited, stack);
        }
    }

    stack |= v;
}
