from abc import ABC, abstractmethod
from typing import Type, TypeVar

# Define a generic type for products
T = TypeVar('T', bound='Product')


class AbstractProduct(ABC):
    """Abstract Product interface"""
    @abstractmethod
    def operation(self) -> str:
        pass


class ConcreteProductA(AbstractProduct):
    """Concrete Product A"""
    def operation(self) -> str:
        return "ConcreteProductA operation"


class ConcreteProductB(AbstractProduct):
    """Concrete Product B"""
    def operation(self) -> str:
        return "ConcreteProductB operation"


class AbstractFactory(ABC):
    """Abstract Factory interface"""
    @abstractmethod
    def create_product(self) -> AbstractProduct:
        pass


class ConcreteFactory1(AbstractFactory):
    """Concrete Factory 1"""
    def create_product(self) -> ConcreteProductA:
        return ConcreteProductA()


class ConcreteFactory2(AbstractFactory):
    """Concrete Factory 2"""
    def create_product(self) -> ConcreteProductB:
        return ConcreteProductB()


def client_code(factory: AbstractFactory) -> None:
    """Client code that interacts with products through factories"""
    product = factory.create_product()
    print(product.operation())


# Example usage
if __name__ == "__main__":
    factory1 = ConcreteFactory1()
    client_code(factory1)

    factory2 = ConcreteFactory2()
    client_code(factory2)
