from abc import ABC, abstractmethod
from typing import List


class SortingStrategy(ABC):
    @abstractmethod
    def sort(self, data: List[int]) -> List[int]:
        pass


class BubbleSortStrategy(SortingStrategy):
    def sort(self, data: List[int]) -> List[int]:
        print("Bubble sorting...")
        return sorted(data)


class QuickSortStrategy(SortingStrategy):
    def sort(self, data: List[int]) -> List[int]:
        print("Quick sorting...")
        return sorted(data)


class Sorter:
    def __init__(self, strategy: SortingStrategy) -> None:
        self._strategy = strategy

    def set_strategy(self, strategy: SortingStrategy) -> None:
        self._strategy = strategy

    def execute_sort(self, data: List[int]) -> List[int]:
        return self._strategy.sort(data)


# Client code
if __name__ == "__main__":
    data = [5, 2, 7, 1, 9]

    bubble_sort_strategy = BubbleSortStrategy()
    sorter = Sorter(bubble_sort_strategy)
    sorted_data = sorter.execute_sort(data)
    print("Sorted data (Bubble Sort):", sorted_data)

    quick_sort_strategy = QuickSortStrategy()
    sorter.set_strategy(quick_sort_strategy)
    sorted_data = sorter.execute_sort(data)
    print("Sorted data (Quick Sort):", sorted_data)
