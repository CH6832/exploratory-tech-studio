from typing import Dict, Any, Type


class Prototype:
    def clone(self) -> "Prototype":
        raise NotImplementedError("clone method must be implemented by subclasses")


class ConcretePrototype1(Prototype):
    def __init__(self, attr1: int, attr2: str):
        self.attr1 = attr1
        self.attr2 = attr2

    def clone(self) -> "ConcretePrototype1":
        return ConcretePrototype1(self.attr1, self.attr2)


class ConcretePrototype2(Prototype):
    def __init__(self, attr3: float, attr4: list):
        self.attr3 = attr3
        self.attr4 = attr4

    def clone(self) -> "ConcretePrototype2":
        return ConcretePrototype2(self.attr3, self.attr4)


class PrototypeFactory:
    _prototypes: Dict[str, Type[Prototype]] = {}

    @classmethod
    def register_prototype(cls, name: str, prototype: Type[Prototype]) -> None:
        cls._prototypes[name] = prototype

    @classmethod
    def create_prototype(cls, name: str) -> Prototype:
        prototype = cls._prototypes.get(name)
        if not prototype:
            raise ValueError(f"No prototype registered with name '{name}'")
        return prototype.clone()


# Client code
if __name__ == "__main__":
    # Register prototypes
    PrototypeFactory.register_prototype("prototype1", ConcretePrototype1(10, "foo"))
    PrototypeFactory.register_prototype("prototype2", ConcretePrototype2(3.14, [1, 2, 3]))

    # Clone prototypes
    clone1 = PrototypeFactory.create_prototype("prototype1")
    clone2 = PrototypeFactory.create_prototype("prototype2")

    # Output cloned objects
    print(f"Clone 1: {clone1.__dict__}")
    print(f"Clone 2: {clone2.__dict__}")
