from abc import ABC, abstractmethod
from typing import Optional


# Product
class Product:
    def __init__(self, part1: str, part2: str, part3: str) -> None:
        self.part1 = part1
        self.part2 = part2
        self.part3 = part3

    def __str__(self) -> str:
        return f"Part 1: {self.part1}, Part 2: {self.part2}, Part 3: {self.part3}"


# Builder interface
class Builder(ABC):
    @abstractmethod
    def build_part1(self, part1: str) -> None:
        pass

    @abstractmethod
    def build_part2(self, part2: str) -> None:
        pass

    @abstractmethod
    def build_part3(self, part3: str) -> None:
        pass

    @abstractmethod
    def get_result(self) -> Product:
        pass


# Concrete builder
class ConcreteBuilder(Builder):
    def __init__(self) -> None:
        self.product: Optional[Product] = None
        self.reset()

    def reset(self) -> None:
        self.product = Product(part1="", part2="", part3="")

    def build_part1(self, part1: str) -> None:
        self.product.part1 = part1

    def build_part2(self, part2: str) -> None:
        self.product.part2 = part2

    def build_part3(self, part3: str) -> None:
        self.product.part3 = part3

    def get_result(self) -> Product:
        product = self.product
        self.reset()
        return product


# Director
class Director:
    def __init__(self, builder: Builder) -> None:
        self.builder = builder

    def construct(self) -> None:
        self.builder.build_part1("Part 1 built")
        self.builder.build_part2("Part 2 built")
        self.builder.build_part3("Part 3 built")


# Client code
if __name__ == "__main__":
    builder = ConcreteBuilder()
    director = Director(builder)
    director.construct()
    product = builder.get_result()
    print(product)
