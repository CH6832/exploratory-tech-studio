const std = @import("std");
const Allocator = std.mem.Allocator;

pub fn main() void {
    const inputArr: [13]char = ['g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's'];
    const sortedArr = countingSort(inputArr);
    std.debug.print("Sorted Array: {any}\n", .{sortedArr});
}

pub fn countingSort(arr: []const u8, allocator: *Allocator) []const u8 {
    const countSize: usize = 256;
    var count: [countSize]u8 = undefined;
    var output: []const u8 = allocator.alloc(u8, arr.len) catch unreachable;

    // Initialize count array with zeros
    for (count) |_, idx| {
        count[idx] = 0;
    }

    // Store count of each character
    for (arr) |char| {
        count[char] += 1;
    }

    // Change count[i] so that count[i] now contains actual position of this character in output array
    for (1 .. countSize) |i| {
        count[i] += count[i - 1];
    }

    // Build the output character array
    for (arr) |char| {
        const index = count[char] - 1;
        output[index] = char;
        count[char] -= 1;
    }

    return output;
}
