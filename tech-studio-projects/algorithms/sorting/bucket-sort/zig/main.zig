const std = @import("std");
const Allocator = std.mem.Allocator;

pub fn main() void {
    const arr = [0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68];
    const sortedArr = bucketSort(arr);
    std.debug.print("{}\n", .{sortedArr});
}

pub fn bucketSort(arr: [10]f64) [10]f64 {
    const numBuckets: usize = arr.len;
    var buckets: [numBuckets][]f64 = undefined;

    for (arr) |num| {
        const index = @intCast(usize, num * numBuckets);
        buckets[index] |= @intToSlice(f64, &[_]f64{num});
    }

    // Sort each bucket individually
    for (buckets) |bucket| {
        std.sort.quickSort(bucket, .{@cmpOrd(f64, @cmpFloat)});
    }

    var sortedArray: [10]f64 = undefined;
    var idx: usize = 0;
    for (buckets) |bucket| {
        for (bucket) |num| {
            sortedArray[idx] = num;
            idx += 1;
        }
    }

    return sortedArray;
}
