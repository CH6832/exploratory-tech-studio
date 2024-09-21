const std = @import("std");

pub fn main() void {
    var sampleList: [7]u32 = [38, 27, 43, 3, 9, 82, 10];
    const originalList = sampleList;
    const originalListSlice = originalList[0..];

    // Print original list
    std.debug.print("Original list: {}\n", .{originalListSlice});

    quickSort(&sampleList, 0, sampleList.len);

    // Print sorted list
    const sortedListSlice = sampleList[0..];
    std.debug.print("Sorted list: {}\n", .{sortedListSlice});
}

pub fn quickSort(arr: []u32, left: usize, right: usize) void {
    if left < right {
        const pivot: usize = partition(arr, left, right);
        quickSort(arr, left, pivot);
        quickSort(arr, pivot + 1, right);
    }
}

fn partition(arr: []u32, left: usize, right: usize) usize {
    const pivotIndex: usize = left;
    var leftIndex: usize = left + 1;
    var rightIndex: usize = right;

    while (leftIndex <= rightIndex) : (leftIndex += 1; rightIndex -= 1) {
        while (leftIndex <= right) : (leftIndex += 1) {
            if arr[leftIndex] > arr[pivotIndex] {
                break;
            }
        }
        while (rightIndex > left) : (rightIndex -= 1) {
            if arr[rightIndex] <= arr[pivotIndex] {
                break;
            }
        }
        if leftIndex < rightIndex {
            arr[leftIndex], arr[rightIndex] = arr[rightIndex], arr[leftIndex];
        }
    }

    arr[pivotIndex], arr[rightIndex] = arr[rightIndex], arr[pivotIndex];
    return rightIndex;
}
