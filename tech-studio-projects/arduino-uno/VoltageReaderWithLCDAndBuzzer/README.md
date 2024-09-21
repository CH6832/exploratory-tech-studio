# Voltage Reader and Indicator with LCD and Buzzer

This project involves reading an analog voltage from an input pin on an Arduino, converting it to a voltage value, and displaying it on an LCD screen and the serial monitor. Additionally, the project controls multiple LEDs based on the voltage level and triggers a buzzer if the voltage falls within a specific range.

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
This project demonstrates how to read analog values using an Arduino, convert these values to voltage, and display the results on an LCD. It also includes controlling LEDs based on the voltage level and activating a buzzer when the voltage is within a specific range.

## Features
- Reads analog voltage from pin A0.
- Converts the analog value to a voltage.
- Displays the voltage on a 16x2 LCD.
- Outputs the voltage to the serial monitor.
- Controls LEDs connected to pins 2 to 6 based on the voltage level.
- Triggers a buzzer connected to pin 9 if the voltage is between 3.25V and 3.35V.

## Hardware Requirements
- Arduino board (e.g., Arduino Uno)
- 16x2 LCD display
- Buzzer
- LEDs (5)
- Breadboard and jumper wires
- 10k potentiometer (for LCD contrast adjustment)
- Resistors (e.g., 220 ohms for LEDs)

## Software Requirements
- Arduino IDE
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
    - Connect LEDs to pins 2 through 6 with appropriate current-limiting resistors.
    - Connect the buzzer to pin 9.
    - Connect a voltage source to pin A0 for analog reading.

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
2. Open the Serial Monitor in the Arduino IDE.
3. Set the baud rate to 9600.
4. Observe the voltage readings displayed on the LCD and the Serial Monitor.
5. LEDs will light up progressively based on the voltage level.
6. The buzzer will sound if the voltage is between 3.25V and 3.35V.
