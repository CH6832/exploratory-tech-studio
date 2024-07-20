public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Command turnOnCommand = new TurnOnCommand(light);
        Command turnOffCommand = new TurnOffCommand(light);

        RemoteControl remoteControl = new RemoteControl();

        // Pressing turn on button
        remoteControl.setCommand(turnOnCommand);
        remoteControl.pressButton();

        // Pressing turn off button
        remoteControl.setCommand(turnOffCommand);
        remoteControl.pressButton();
    }
}
