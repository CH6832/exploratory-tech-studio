from typing import List


class Observer:
    def update(self, message: str) -> None:
        pass


class Subject:
    _observers: List[Observer] = []

    def attach(self, observer: Observer) -> None:
        self._observers.append(observer)

    def detach(self, observer: Observer) -> None:
        self._observers.remove(observer)

    def notify(self, message: str) -> None:
        for observer in self._observers:
            observer.update(message)


class ConcreteObserver(Observer):
    def __init__(self, name: str):
        self.name = name

    def update(self, message: str) -> None:
        print(f"{self.name} received message: {message}")


class ConcreteSubject(Subject):
    def do_something(self) -> None:
        # Perform some actions
        print("Subject is doing something...")
        # Notify observers
        self.notify("Something has happened!")


# Client code
if __name__ == "__main__":
    subject = ConcreteSubject()
    observer1 = ConcreteObserver("Observer 1")
    observer2 = ConcreteObserver("Observer 2")

    subject.attach(observer1)
    subject.attach(observer2)

    subject.do_something()

    subject.detach(observer2)

    subject.do_something()
