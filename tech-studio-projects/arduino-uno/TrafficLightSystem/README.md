# Traffic Light Control with Pedestrian Signal

This Arduino project simulates a traffic light system with separate controls for vehicle and pedestrian signals. The system includes an LCD display for status updates and uses LEDs and a buzzer to indicate signal changes.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Hardware Requirements](#hardware-requirements)
- [Software Requirements](#software-requirements)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction
This project demonstrates a traffic light system with both vehicle and pedestrian signals. The vehicle traffic light has green, yellow, and red phases, while the pedestrian light has a green and red phase. The system also includes a button for pedestrians to request crossing, which triggers a visual and auditory alert before changing the lights.

## Features
- Controls vehicle traffic light with green, yellow, and red phases.
- Controls pedestrian signal with red and green lights.
- Includes a button for pedestrian crossing requests.
- Displays current phase on an LCD.
- Visual and auditory alerts when the pedestrian crossing request is activated.

## Hardware Requirements
- Arduino board (e.g., Arduino Uno)
- 16x2 LCD display
- LEDs for vehicle traffic light (3 colors: red, yellow, green)
- LEDs for pedestrian signal (2 colors: red, green)
- Button for pedestrian request
- Buzzer for auditory alert
- Breadboard and jumper wires
- Resistors (e.g., 220-ohms for LEDs)

## Software Requirements
- Arduino IDE
- LiquidCrystal library (usually included with the Arduino IDE)

## Installation and Setup

**Wiring:**
    - Connect the LCD to the Arduino:
      - RS to digital pin 7
      - E to digital pin 8
      - D4 to digital pin 9
      - D5 to digital pin 10
      - D6 to digital pin 11
      - D7 to digital pin 12
      - VSS to GND
      - VDD to 5V
      - V0 to the middle pin of a 10k potentiometer (other two pins to 5V and GND)
      - A (anode) to a 220-ohm resistor to 5V
      - K (cathode) to GND
    - Connect LEDs for vehicle traffic light:
      - Red LED to pin 2
      - Yellow LED to pin 3
      - Green LED to pin 4
    - Connect LEDs for pedestrian signal:
      - Red LED to pin 13
      - Green LED to pin 6
    - Connect the pedestrian request button to analog pin A5 with an internal pull-up resistor.
    - Connect the buzzer to analog pin A4.
    - Connect each LED with a 220-ohm resistor to limit current.

**Software:**
    - Download and install the Arduino IDE from the [official website](https://www.arduino.cc/en/software).
    - Open the Arduino IDE and create a new sketch.
    - Copy and paste the provided code into the sketch.

**Upload:**
    - Connect your Arduino board to your computer via USB.
    - Select the correct board and port from the Tools menu in the Arduino IDE.
    - Click the Upload button to compile and upload the code to the Arduino board.

## Usage
1. Ensure all components are connected correctly.
2. The traffic light will start with the vehicle light green and the pedestrian light red.
3. Press the pedestrian request button to activate the pedestrian signal. The system will:
   - Blink the pedestrian LED five times.
   - Sound the buzzer.
   - Change the vehicle light to red and the pedestrian light to green for 10 seconds.
   - Return to the vehicle phase with the yellow light for 2 seconds.
