from abc import ABC, abstractmethod
from typing import List


# Component interface
class Component(ABC):
    @abstractmethod
    def operation(self) -> None:
        pass


# Leaf class
class Leaf(Component):
    def __init__(self, name: str) -> None:
        self.name = name

    def operation(self) -> None:
        print(f"Leaf {self.name} operation")


# Composite class
class Composite(Component):
    def __init__(self, name: str) -> None:
        self.name = name
        self.children: List[Component] = []

    def add(self, component: Component) -> None:
        self.children.append(component)

    def remove(self, component: Component) -> None:
        self.children.remove(component)

    def operation(self) -> None:
        print(f"Composite {self.name} operation:")
        for child in self.children:
            child.operation()


# Client code
if __name__ == "__main__":
    # Create leaf components
    leaf1 = Leaf("1")
    leaf2 = Leaf("2")

    # Create composite components and add leaf components to them
    composite1 = Composite("A")
    composite1.add(leaf1)
    composite1.add(leaf2)

    leaf3 = Leaf("3")
    leaf4 = Leaf("4")

    composite2 = Composite("B")
    composite2.add(leaf3)
    composite2.add(leaf4)

    # Create another composite and add the first two composites to it
    composite = Composite("Root")
    composite.add(composite1)
    composite.add(composite2)

    # Operation on the whole composite structure
    composite.operation()
