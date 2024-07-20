import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

abstract class SortingAlgorithm {
    abstract List<Integer> sort(List<Integer> data);

    List<Integer> templateMethod(List<Integer> data) {
        preprocess(data);
        List<Integer> sortedData = sort(data);
        postprocess(sortedData);
        return sortedData;
    }

    private void preprocess(List<Integer> data) {
        System.out.println("Preprocessing data...");
    }

    private void postprocess(List<Integer> sortedData) {
        System.out.println("Postprocessing sorted data...");
    }
}

class BubbleSort extends SortingAlgorithm {
    @Override
    List<Integer> sort(List<Integer> data) {
        System.out.println("Bubble sorting...");
        Collections.sort(data);
        return data;
    }
}

class QuickSort extends SortingAlgorithm {
    @Override
    List<Integer> sort(List<Integer> data) {
        System.out.println("Quick sorting...");
        Collections.sort(data);
        return data;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>(List.of(5, 2, 7, 1, 9));

        BubbleSort bubbleSort = new BubbleSort();
        List<Integer> sortedData = bubbleSort.templateMethod(data);
        System.out.print("Sorted data using Bubble Sort:");
        for (int num : sortedData) {
            System.out.print(" " + num);
        }
        System.out.println();

        QuickSort quickSort = new QuickSort();
        sortedData = quickSort.templateMethod(data);
        System.out.print("Sorted data using Quick Sort:");
        for (int num : sortedData) {
            System.out.print(" " + num);
        }
        System.out.println();
    }
}
