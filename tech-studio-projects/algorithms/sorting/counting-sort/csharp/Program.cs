using System;
using System.Collections.Generic;

class Program
{
    static void Main(string[] args)
    {
        List<char> inputArr = new List<char>() { 'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's' };
        List<char> sortedArr = CountingSort(inputArr);
        Console.WriteLine(string.Join(", ", sortedArr));
    }

    static List<char> CountingSort(List<char> arr)
    {
        // The output character list that will have sorted arr
        List<char> output = new List<char>();
        output.AddRange(new char[arr.Count]);

        // Create a count array to store the count of individual characters
        int[] count = new int[256];

        // Store count of each character
        foreach (char c in arr)
        {
            count[c]++;
        }

        // Change count[i] so that count[i] now contains the actual position of this character in the output list
        for (int i = 1; i < 256; i++)
        {
            count[i] += count[i - 1];
        }

        // Build the output character list
        for (int i = arr.Count - 1; i >= 0; i--) // Iterate in reverse to maintain stability
        {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        return output;
    }
}
