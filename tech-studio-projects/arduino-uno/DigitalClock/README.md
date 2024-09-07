# Digital Clock with Time Setting Functionality

This project implements a digital clock using an Arduino, an LCD display, and buttons to set the current time. The clock displays hours and minutes in the `hh:mm` format and allows users to set the time using the buttons.

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
This project demonstrates how to create a digital clock that can display and set the current time using an LCD display and buttons. The user can adjust the hours and minutes using the buttons connected to the Arduino.

## Features
- Displays the current time in `hh:mm` format on an LCD.
- Allows setting the current time using buttons.
- Updates the display every two seconds.

## Hardware Requirements
- Arduino board (e.g., Arduino Uno)
- 16x2 LCD display
- Buttons (3) for setting the time
- Breadboard and jumper wires
- 10k potentiometer (for LCD contrast adjustment)
- Resistors (e.g., 10k ohms for pull-down resistors on buttons)

## Software Requirements
- Arduino IDE
- TimeLib library
- LiquidCrystal library (usually included with the Arduino IDE)

## Installation and Setup

**Wiring:**
    - Connect the LCD to the Arduino as follows:
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
    - Connect buttons to digital pins 3, 4, and 5 with pull-down resistors.
    - Connect a voltage source to pin A0 for analog reading (if required).

**Software:**
    - Download and install the Arduino IDE from the [official website](https://www.arduino.cc/en/software).
    - Install the TimeLib library via the Library Manager in the Arduino IDE.
    - Open the Arduino IDE and create a new sketch.
    - Copy and paste the provided code into the sketch.

**Upload:**
    - Connect your Arduino board to your computer via USB.
    - Select the correct board and port from the Tools menu in the Arduino IDE.
    - Click the Upload button to compile and upload the code to the Arduino board.

## Usage
1. Ensure all components are connected correctly.
2. Use the buttons to set the current time:
   - Press the `SET` button to switch between setting hours and minutes.
   - Use the `UP` and `DOWN` buttons to adjust the hours and minutes.
3. Observe the current time displayed on the LCD in `hh:mm` format.
