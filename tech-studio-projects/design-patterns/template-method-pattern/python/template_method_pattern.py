from abc import ABC, abstractmethod
from typing import List


class SortingAlgorithm(ABC):
    @abstractmethod
    def sort(self, data: List[int]) -> List[int]:
        pass

    def template_method(self, data: List[int]) -> List[int]:
        self._preprocess(data)
        sorted_data = self.sort(data)
        self._postprocess(sorted_data)
        return sorted_data

    def _preprocess(self, data: List[int]) -> None:
        print("Preprocessing data...")

    def _postprocess(self, sorted_data: List[int]) -> None:
        print("Postprocessing sorted data...")


class BubbleSort(SortingAlgorithm):
    def sort(self, data: List[int]) -> List[int]:
        print("Bubble sorting...")
        return sorted(data)


class QuickSort(SortingAlgorithm):
    def sort(self, data: List[int]) -> List[int]:
        print("Quick sorting...")
        return sorted(data)


# Client code
if __name__ == "__main__":
    data = [5, 2, 7, 1, 9]

    bubble_sort = BubbleSort()
    sorted_data = bubble_sort.template_method(data)
    print("Sorted data using Bubble Sort:", sorted_data)

    quick_sort = QuickSort()
    sorted_data = quick_sort.template_method(data)
    print("Sorted data using Quick Sort:", sorted_data)
