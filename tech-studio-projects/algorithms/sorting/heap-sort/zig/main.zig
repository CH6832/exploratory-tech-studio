const std = @import("std");
const Allocator = std.mem.Allocator;

pub fn main() void {
    const arr: [6]u32 = [12, 11, 13, 5, 6, 7];
    std.debug.print("Original array: {}\n", .{arr});
    heapSort(&arr);
    std.debug.print("Sorted array: {}\n", .{arr});
}

pub fn heapSort(arr: []const u32) void {
    const n: usize = arr.len;

    // Define heapify function to maintain the max-heap property
    fn heapify(arr: []u32, n: usize, i: usize) void {
        var largest: usize = i;
        const l: usize = 2 * i + 1;
        const r: usize = 2 * i + 2;

        // Check if left child exists and is greater than root
        if (l < n) and (arr[l] > arr[largest]) {
            largest = l;
        }

        // Check if right child exists and is greater than the largest so far
        if (r < n) and (arr[r] > arr[largest]) {
            largest = r;
        }

        // If the largest element is not the root
        if (largest != i) {
            const tmp: u32 = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    // Build max heap
    for (i := n / 2 - 1; i >= 0; i -= 1) {
        heapify(arr, n, i);
    }

    // Extract elements from heap one by one
    for (i := n - 1; i > 0; i -= 1) {
        const tmp: u32 = arr[0];
        arr[0] = arr[i];
        arr[i] = tmp;

        // Call max heapify on the reduced heap
        heapify(arr, i, 0);
    }
}
