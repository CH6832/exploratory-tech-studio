#include <iostream>
#include "Light.h"
#include "TurnOnCommand.h"
#include "TurnOffCommand.h"
#include "RemoteControl.h"

int main() {
    Light light;
    TurnOnCommand turnOnCommand(light);
    TurnOffCommand turnOffCommand(light);

    RemoteControl remoteControl;

    // Pressing turn on button
    remoteControl.setCommand(&turnOnCommand);
    remoteControl.pressButton();

    // Pressing turn off button
    remoteControl.setCommand(&turnOffCommand);
    remoteControl.pressButton();

    return 0;
}
