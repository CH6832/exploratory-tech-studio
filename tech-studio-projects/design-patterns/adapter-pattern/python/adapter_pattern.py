from abc import ABC, abstractmethod
from typing import Protocol


# Target interface
class Target(Protocol):
    @abstractmethod
    def request(self) -> str:
        pass


# Adaptee
class Adaptee:
    def specific_request(self) -> str:
        return "Adaptee's specific request"


# Adapter
class Adapter(Target):
    def __init__(self, adaptee: Adaptee) -> None:
        self.adaptee = adaptee

    def request(self) -> str:
        return f"Adapter: {self.adaptee.specific_request()}"


# Client code
def client_code(target: Target) -> None:
    print(target.request())


# Example usage
if __name__ == "__main__":
    adaptee = Adaptee()
    adapter = Adapter(adaptee)
    client_code(adapter)
