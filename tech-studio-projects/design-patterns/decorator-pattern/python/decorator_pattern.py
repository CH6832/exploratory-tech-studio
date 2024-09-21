from abc import ABC, abstractmethod


# Component interface
class Coffee(ABC):
    @abstractmethod
    def cost(self) -> float:
        pass


# Concrete component
class SimpleCoffee(Coffee):
    def cost(self) -> float:
        return 1.0


# Decorator class
class CoffeeDecorator(Coffee):
    def __init__(self, coffee: Coffee) -> None:
        self._coffee = coffee

    def cost(self) -> float:
        return self._coffee.cost()


# Concrete decorators
class Milk(CoffeeDecorator):
    def cost(self) -> float:
        return self._coffee.cost() + 0.5


class Sugar(CoffeeDecorator):
    def cost(self) -> float:
        return self._coffee.cost() + 0.2


# Client code
if __name__ == "__main__":
    coffee = SimpleCoffee()
    print("Cost of simple coffee:", coffee.cost())

    coffee_with_milk = Milk(coffee)
    print("Cost of coffee with milk:", coffee_with_milk.cost())

    coffee_with_sugar = Sugar(coffee)
    print("Cost of coffee with sugar:", coffee_with_sugar.cost())

    coffee_with_milk_and_sugar = Sugar(Milk(coffee))
    print("Cost of coffee with milk and sugar:", coffee_with_milk_and_sugar.cost())
