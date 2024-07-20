const std = @import("std");
const io = std.io;

pub fn main() void {
    // Initialize an unsorted list of integers
    var unsortedList: [7]u32 = [64, 34, 25, 12, 22, 11, 90];
    io.print("Unsorted list: ");
    printList(unsortedList);

    // Sort the list using Bubble Sort algorithm
    bubbleSort(&unsortedList);

    // Print the sorted list
    io.print("Sorted list: ");
    printList(unsortedList);
}

pub fn bubbleSort(list: []u32) void {
    const len = list.len;
    var swapped: bool = false;

    // Perform multiple passes over the list
    for (len - 1) |i| {
        swapped = false;
        // Compare adjacent elements and swap if necessary
        for (len - 1) |j| {
            if (list[j] > list[j + 1]) {
                const temp = list[j];
                list[j] = list[j + 1];
                list[j + 1] = temp;
                swapped = true;
            }
        }
        // If no two elements were swapped, the list is already sorted
        if (!swapped) {
            break;
        }
    }
}

fn printList(list: []u32) void {
    var first: bool = true;
    for (list) |element| {
        if (!first) {
            io.print(", ");
        }
        first = false;
        io.print("{d}", .{element});
    }
    io.print("\n");
}
