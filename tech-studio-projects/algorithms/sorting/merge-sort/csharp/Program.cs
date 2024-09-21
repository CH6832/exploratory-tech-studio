using System;

class Program
{
    static void Main(string[] args)
    {
        int[] sampleList = { 38, 27, 43, 3, 9, 82, 10 };
        Console.WriteLine("Original list: " + string.Join(", ", sampleList));
        MergeSort(sampleList, 0, sampleList.Length - 1);
        Console.WriteLine("Sorted list: " + string.Join(", ", sampleList));
    }

    static void MergeSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int mid = (low + high) / 2;
            MergeSort(arr, low, mid);
            MergeSort(arr, mid + 1, high);
            Merge(arr, low, mid, high);
        }
    }

    static void Merge(int[] arr, int low, int mid, int high)
    {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
        {
            L[i] = arr[low + i];
        }
        for (int j = 0; j < n2; ++j)
        {
            R[j] = arr[mid + 1 + j];
        }

        int k = low;
        int lIndex = 0, rIndex = 0;
        while (lIndex < n1 && rIndex < n2)
        {
            if (L[lIndex] <= R[rIndex])
            {
                arr[k] = L[lIndex];
                lIndex++;
            }
            else
            {
                arr[k] = R[rIndex];
                rIndex++;
            }
            k++;
        }

        while (lIndex < n1)
        {
            arr[k] = L[lIndex];
            lIndex++;
            k++;
        }

        while (rIndex < n2)
        {
            arr[k] = R[rIndex];
            rIndex++;
            k++;
        }
    }
}
