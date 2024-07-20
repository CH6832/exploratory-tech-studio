from abc import ABC, abstractmethod


# Product interface
class Pizza(ABC):
    @abstractmethod
    def prepare(self) -> None:
        pass

    @abstractmethod
    def bake(self) -> None:
        pass

    @abstractmethod
    def cut(self) -> None:
        pass

    @abstractmethod
    def box(self) -> None:
        pass


# Concrete products
class CheesePizza(Pizza):
    def prepare(self) -> None:
        print("Preparing Cheese Pizza...")

    def bake(self) -> None:
        print("Baking Cheese Pizza...")

    def cut(self) -> None:
        print("Cutting Cheese Pizza...")

    def box(self) -> None:
        print("Boxing Cheese Pizza...")


class PepperoniPizza(Pizza):
    def prepare(self) -> None:
        print("Preparing Pepperoni Pizza...")

    def bake(self) -> None:
        print("Baking Pepperoni Pizza...")

    def cut(self) -> None:
        print("Cutting Pepperoni Pizza...")

    def box(self) -> None:
        print("Boxing Pepperoni Pizza...")


# Creator interface
class PizzaStore(ABC):
    @abstractmethod
    def create_pizza(self) -> Pizza:
        pass

    def order_pizza(self) -> None:
        pizza = self.create_pizza()
        pizza.prepare()
        pizza.bake()
        pizza.cut()
        pizza.box()


# Concrete creators
class NYStylePizzaStore(PizzaStore):
    def create_pizza(self) -> Pizza:
        return CheesePizza()


class ChicagoStylePizzaStore(PizzaStore):
    def create_pizza(self) -> Pizza:
        return PepperoniPizza()


# Client code
if __name__ == "__main__":
    ny_store = NYStylePizzaStore()
    ny_store.order_pizza()

    chicago_store = ChicagoStylePizzaStore()
    chicago_store.order_pizza()
