using System;

class Program
{
    static void Main(string[] args)
    {
        int[] sampleList = { 12, 11, 13, 5, 6 };
        Console.WriteLine("Original list: " + string.Join(", ", sampleList));
        InsertionSort(sampleList);
        Console.WriteLine("Sorted list: " + string.Join(", ", sampleList));
    }

    static void InsertionSort(int[] listToSort)
    {
        Console.WriteLine("Unsorted: " + string.Join(", ", listToSort));
        // Iterate over the array to be sorted
        for (int i = 1; i < listToSort.Length; i++)
        {
            int x = listToSort[i];  // Get each element
            int j = i - 1;  // Get one position before x
            // Shift elements until reaching index 0 or getting an element smaller than x
            while (j >= 0 && x < listToSort[j])
            {
                listToSort[j + 1] = listToSort[j];
                j--;
            }
            // Place x in its correct position
            listToSort[j + 1] = x;
        }
        Console.WriteLine("Sorted: " + string.Join(", ", listToSort));
    }
}
