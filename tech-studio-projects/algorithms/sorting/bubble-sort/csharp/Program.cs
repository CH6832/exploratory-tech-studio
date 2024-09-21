using System;
using System.Collections.Generic;

public class BubbleSort
{
    public static void Main(string[] args)
    {
        List<int> unsortedList = new List<int> { 64, 34, 25, 12, 22, 11, 90 };
        List<int> sortedList = BubbleSortFunction(unsortedList);
        Console.WriteLine("Sorted list:");
        foreach (var item in sortedList)
        {
            Console.Write(item + " ");
        }
        Console.WriteLine();
        VisualizeBubbleSort(unsortedList);
    }

    public static List<int> BubbleSortFunction(List<int> inputList)
    {
        int n = inputList.Count;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (inputList[j] > inputList[j + 1])
                {
                    int temp = inputList[j];
                    inputList[j] = inputList[j + 1];
                    inputList[j + 1] = temp;
                }
            }
        }
        return inputList;
    }

    public static void VisualizeBubbleSort(List<int> inputList)
    {
        Console.WriteLine("Bubble Sort Visualization");
        foreach (var item in inputList)
        {
            Console.Write(item + " ");
        }
        Console.WriteLine();
    }
}
