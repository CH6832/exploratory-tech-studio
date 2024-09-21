using System;

class Program
{
    static void Main(string[] args)
    {
        int[] sampleList = { 38, 27, 43, 3, 9, 82, 10 };
        Console.WriteLine("Original list: " + string.Join(", ", sampleList));
        QuickSort(sampleList, 0, sampleList.Length - 1);
        Console.WriteLine("Sorted list: " + string.Join(", ", sampleList));
    }

    static void QuickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = Partition(arr, low, high);
            QuickSort(arr, low, pi - 1);
            QuickSort(arr, pi + 1, high);
        }
    }

    static int Partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                Swap(arr, i, j);
            }
        }

        Swap(arr, i + 1, high);
        return i + 1;
    }

    static void Swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
