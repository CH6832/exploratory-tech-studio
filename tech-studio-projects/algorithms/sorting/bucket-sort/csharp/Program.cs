using System;
using System.Collections.Generic;

class Program
{
    static void Main(string[] args)
    {
        double[] arr = { 0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68 };
        double[] sortedArr = BucketSort(arr);
        Console.WriteLine(string.Join(", ", sortedArr));
    }

    static double[] BucketSort(double[] arr)
    {
        int numBuckets = arr.Length;
        List<List<double>> buckets = new List<List<double>>();
        for (int i = 0; i < numBuckets; i++)
        {
            buckets.Add(new List<double>());
        }

        Console.WriteLine("Initial array: " + string.Join(", ", arr));

        // place each element in the appropriate bucket based on its value
        foreach (double num in arr)
        {
            int index = (int)(num * numBuckets);
            buckets[index].Add(num);
            Console.WriteLine($"Element {num} -> Bucket {index}");
        }

        Console.WriteLine("\nBuckets after distribution:");
        for (int i = 0; i < buckets.Count; i++)
        {
            Console.WriteLine($"Bucket {i}: [{string.Join(", ", buckets[i])}]");
        }

        // sort each bucket individually
        foreach (List<double> bucket in buckets)
        {
            bucket.Sort();
        }

        Console.WriteLine("\nBuckets after sorting:");
        for (int i = 0; i < buckets.Count; i++)
        {
            Console.WriteLine($"Bucket {i}: [{string.Join(", ", buckets[i])}]");
        }

        // concatenate all buckets into the original array
        List<double> sortedArray = new List<double>();
        foreach (List<double> bucket in buckets)
        {
            sortedArray.AddRange(bucket);
        }

        return sortedArray.ToArray();
    }
}
