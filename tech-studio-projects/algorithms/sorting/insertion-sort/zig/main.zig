const std = @import("std");
const Allocator = std.mem.Allocator;

pub fn main() void {
    var sampleList: [5]u32 = [12, 11, 13, 5, 6];
    const originalList = sampleList;
    const originalListSlice = originalList[0..];

    // Print original list
    std.debug.print("Original list: {}\n", .{originalListSlice});

    insertionSort(&sampleList);

    // Print sorted list
    const sortedListSlice = sampleList[0..];
    std.debug.print("Sorted list: {}\n", .{sortedListSlice});
}

pub fn insertionSort(arr: []u32) void {
    // Iterate over the array to be sorted
    for (i := 1; i < arr.len; i += 1) : (i += 1) {
        var x: u32 = arr[i];  // Get each element
        var j: isize = @intCast(isize, i) - 1;  // Get one position before x
        // Shift elements until reaching index 0 or getting an element smaller than x
        while (j >= 0) && (x < arr[@intCast(usize, j)]) : (j -= 1) {
            arr[j + 1] = arr[j];
            j -= 1;
        }
        // Place x in its correct position
        arr[j + 1] = x;
    }
}
