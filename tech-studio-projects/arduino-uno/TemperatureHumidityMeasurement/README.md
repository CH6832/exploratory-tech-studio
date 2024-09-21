# Temperature and Humidity Measurement with Arduino

This project measures the temperature and controls LEDs based on the temperature range using an Arduino. It utilizes a DHT11 sensor for temperature readings and provides visual feedback through LEDs.

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
This project demonstrates how to measure temperature and control LEDs based on temperature readings using an Arduino and a DHT11 sensor. The temperature readings are used to activate one of three LEDs representing different temperature ranges.

## Features
- Measures temperature using a DHT11 sensor.
- Controls three LEDs based on temperature ranges:
  - Blue LED for temperatures below 18°C.
  - Green LED for temperatures between 19°C and 20°C.
  - Red LED for temperatures 21°C and above.
- Updates every 2 seconds.

## Hardware Requirements
- Arduino board (e.g., Arduino Uno)
- DHT11 Temperature and Humidity Sensor
- LEDs (3) in different colors (blue, green, red)
- 220-ohm resistors (for LED current limiting)
- Breadboard and jumper wires

## Software Requirements
- Arduino IDE
- Adafruit Sensor library
- DHT library

## Installation and Setup

**Wiring:**
    - Connect the DHT11 sensor to the Arduino:
      - VCC to 5V
      - GND to GND
      - Data to digital pin 2
    - Connect LEDs to the Arduino:
      - Blue LED to pin 4
      - Green LED to pin 5
      - Red LED to pin 6
    - Each LED should have a 220-ohm resistor connected in series to limit current.

**Software:**
    - Download and install the Arduino IDE from the [official website](https://www.arduino.cc/en/software).
    - Install the Adafruit Sensor and DHT libraries via the Library Manager in the Arduino IDE.
      - Go to `Sketch` > `Include Library` > `Manage Libraries...`
      - Search for "Adafruit Sensor" and "DHT" and install them.

**Upload:**
    - Connect your Arduino board to your computer via USB.
    - Select the correct board and port from the Tools menu in the Arduino IDE.
    - Click the Upload button to compile and upload the code to the Arduino board.

## Usage
1. Ensure all components are connected correctly.
2. Power the Arduino and observe the LEDs:
   - The blue LED will light up if the temperature is below 18°C.
   - The green LED will light up if the temperature is between 19°C and 20°C.
   - The red LED will light up if the temperature is 21°C or above.
3. The temperature is measured and LEDs are controlled every 2 seconds.
