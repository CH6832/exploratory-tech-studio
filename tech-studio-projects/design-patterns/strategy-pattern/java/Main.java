import java.util.List;

interface SortingStrategy {
    List<Integer> sort(List<Integer> data);
}

class BubbleSortStrategy implements SortingStrategy {
    public List<Integer> sort(List<Integer> data) {
        System.out.println("Bubble sorting...");
        data.sort(null);
        return data;
    }
}

class QuickSortStrategy implements SortingStrategy {
    public List<Integer> sort(List<Integer> data) {
        System.out.println("Quick sorting...");
        data.sort(null);
        return data;
    }
}

class Sorter {
    private SortingStrategy strategy;

    public Sorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Integer> executeSort(List<Integer> data) {
        return strategy.sort(data);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Integer> data = List.of(5, 2, 7, 1, 9);

        BubbleSortStrategy bubbleSortStrategy = new BubbleSortStrategy();
        Sorter sorter = new Sorter(bubbleSortStrategy);
        List<Integer> sortedData = sorter.executeSort(data);
        System.out.println("Sorted data (Bubble Sort): " + sortedData);

        QuickSortStrategy quickSortStrategy = new QuickSortStrategy();
        sorter.setStrategy(quickSortStrategy);
        sortedData = sorter.executeSort(data);
        System.out.println("Sorted data (Quick Sort): " + sortedData);
    }
}
