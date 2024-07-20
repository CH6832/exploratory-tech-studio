from abc import ABC, abstractmethod
from typing import Any, Protocol


# Receiver
class Light:
    def turn_on(self) -> None:
        print("Light is on")

    def turn_off(self) -> None:
        print("Light is off")


# Command interface
class Command(Protocol):
    @abstractmethod
    def execute(self) -> None:
        pass


# Concrete command
class TurnOnCommand(Command):
    def __init__(self, light: Light) -> None:
        self.light = light

    def execute(self) -> None:
        self.light.turn_on()


# Concrete command
class TurnOffCommand(Command):
    def __init__(self, light: Light) -> None:
        self.light = light

    def execute(self) -> None:
        self.light.turn_off()


# Invoker
class RemoteControl:
    def __init__(self) -> None:
        self.command: Command

    def set_command(self, command: Command) -> None:
        self.command = command

    def press_button(self) -> None:
        self.command.execute()


# Client code
if __name__ == "__main__":
    light = Light()
    turn_on_command = TurnOnCommand(light)
    turn_off_command = TurnOffCommand(light)

    remote_control = RemoteControl()

    # Pressing turn on button
    remote_control.set_command(turn_on_command)
    remote_control.press_button()

    # Pressing turn off button
    remote_control.set_command(turn_off_command)
    remote_control.press_button()
