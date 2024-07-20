const std = @import("std");
const Allocator = std.mem.Allocator;

pub fn main() void {
    var sampleList: [7]u32 = [38, 27, 43, 3, 9, 82, 10];
    const originalList = sampleList;
    const originalListSlice = originalList[0..];

    // Print original list
    std.debug.print("Original list: {}\n", .{originalListSlice});

    mergeSort(&sampleList);

    // Print sorted list
    const sortedListSlice = sampleList[0..];
    std.debug.print("Sorted list: {}\n", .{sortedListSlice});
}

pub fn mergeSort(arr: []u32) void {
    // Check if array can be divided
    if (arr.len > 1) {
        // Finding the mid of the list
        const mid: usize = arr.len / 2;
        // Dividing the elements into 2 halves
        var leftPart: []u32 = arr[0..mid];
        var rightPart: []u32 = arr[mid..];

        // Sorting both halves
        mergeSort(leftPart);
        mergeSort(rightPart);

        // Merge the sorted halves
        var i: usize = 0;
        var j: usize = 0;
        var k: usize = 0;

        while (i < leftPart.len) && (j < rightPart.len) : (i += 1; j += 1) {
            if leftPart[i] <= rightPart[j] {
                arr[k] = leftPart[i];
                i += 1;
            } else {
                arr[k] = rightPart[j];
                j += 1;
            }
            k += 1;
        }

        // Copy remaining elements from leftPart
        while (i < leftPart.len) : (i += 1) {
            arr[k] = leftPart[i];
            k += 1;
        }

        // Copy remaining elements from rightPart
        while (j < rightPart.len) : (j += 1) {
            arr[k] = rightPart[j];
            k += 1;
        }
    }
}
