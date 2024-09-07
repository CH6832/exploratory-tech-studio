# Frequency Measurement with Arduino

This project measures the frequency of a TTL (Transistor-Transistor Logic) signal at pin 6 of an Arduino. It displays the frequency in both Hertz (Hz) and kilohertz (kHz) on an LCD screen and prints the values to the serial monitor.

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
This project demonstrates how to measure the frequency of a TTL signal using an Arduino. The frequency is calculated by measuring the time period of the signal and then converting it into Hertz. The result is displayed on a 16x2 LCD screen and printed to the serial monitor.

## Features
- Measures frequency of a TTL signal on pin 6.
- Displays frequency in Hz and kHz on an LCD.
- Prints frequency to the serial monitor.
- Accurate for frequencies up to approximately 1 kHz.

## Hardware Requirements
- Arduino board (e.g., Arduino Uno)
- 16x2 LCD display
- TTL signal generator
- Breadboard and jumper wires
- 10k potentiometer (for LCD contrast adjustment)
- Resistors (e.g., 10k ohms for pull-down resistors on buttons)

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
    - Connect the TTL signal generator to digital pin 6.

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
2. Connect a TTL signal generator to pin 6 of the Arduino.
3. Observe the frequency displayed on the LCD in Hz and kHz.
4. The frequency values are also printed to the serial monitor.
