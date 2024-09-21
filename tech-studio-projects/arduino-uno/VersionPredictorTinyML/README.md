# Version Prediction on Arduino using TensorFlow Lite Micro

## Project Description

This project aims to predict the major and minor version numbers of software components using a machine learning model deployed on an Arduino board. The model is developed and trained using TensorFlow, converted to TensorFlow Lite Micro format, and deployed on a resource-constrained device like an Arduino. The project follows the complete machine learning lifecycle, from data collection and preprocessing to model deployment and maintenance.

## Why This Project?

### Motivation

In the modern software development lifecycle, managing and predicting software versions is crucial for maintaining compatibility and planning updates. Traditionally, this is done using server-side applications where computational resources are ample. However, there are scenarios where edge devices, like Arduino boards, need to perform version predictions locally for real-time decision-making, reducing latency and reliance on external systems.

### Why Use an Arduino Board?

1. **Real-Time Processing**: Running the model on an Arduino allows for real-time predictions without the need for constant communication with a server. This is useful in scenarios where quick responses are critical, such as in embedded systems and IoT devices.

2. **Edge Computing**: By deploying the model directly on the Arduino, the system can function independently of external servers. This reduces the dependency on cloud computing and enables the device to operate in environments with limited or unreliable internet connectivity.

3. **Cost-Effectiveness**: Arduino boards are cost-effective and widely available, making them a practical choice for deploying machine learning models in various applications. They provide an affordable platform for edge AI applications, allowing for experimentation and deployment in budget-constrained projects.

4. **Power Efficiency**: Arduino boards typically consume very low power, which is advantageous for battery-operated or low-power devices. Performing predictions locally helps in conserving energy by reducing the need for continuous data transmission to a server.

5. **Prototyping and Testing**: Arduino boards are popular for prototyping and testing new ideas in embedded systems. Running a machine learning model on such a board can help in quickly validating concepts and refining models in real-world conditions.

## Repository Structure

|-- data/
|   |-- raw/
|   |   |-- versions.csv
|   |
|   |-- processed/
|       |-- processed_versions.csv
|       |-- train_versions.csv
|       |-- val_versions.csv
|       |-- test_versions.csv
|
|-- models/
|   |-- version_model.h5
|   |-- version_model_quantized.tflite
|
|-- src/
|   |-- preprocessing/
|   |   |-- data_preprocessing.py
|   |
|   |-- training/
|   |   |-- train_model.py
|   |
|   |-- deployment/
|       |-- arduino_inference_code.ino
|       |-- model_conversion.py
|
|-- tests/
|   |-- test_model_conversion.py
|   |-- test_arduino_inference.ino
|
|-- docs/
|   |-- architecture_diagram.txt
|   |-- data_collection_plan.md
|   |-- data_description.md
|   |-- data_preprocessing_pipeline.md
|   |-- data_labeling_guidelines.md
|   |-- data_validation_report.md
|   |-- feature_selection.md
|   |-- model_architecture.md
|   |-- system_validation_plan.md
|   |-- deployment_guide.md
|   |-- monitoring_plan.md
|   |-- maintenance_plan.md
|
|-- Jenkinsfile
|-- README.md

## Overview of Technologies

- **TensorFlow**: Used for building and training the machine learning model.
- **TensorFlow Lite Micro**: Used to convert and deploy the model on the Arduino.
- **Arduino IDE**: Used to program the Arduino and upload the model.
- **Python**: Used for data preprocessing and model training.
- **pytest**: Used for testing the Python code.
- **Arduino CLI**: Used for compiling and uploading Arduino sketches.

- **Jenkins**: Used for continuous integration and deployment.

## Getting Started

### Prerequisites

- Python 3.x
- TensorFlow 2.x
- Arduino IDE
- Arduino board (e.g., Arduino Nano 33 BLE Sense)
- Arduino CLI (if not already installed)

### Steps to Run the Project

1. **Data Preparation**: Preprocess the data using `data_preprocessing.py`.

    ```bash
    cd src/preprocessing/
    python data_preprocessing.py
    ```

2. **Model Training**: Train the model using `train_model.py`.

    ```bash
    cd ../training/
    python train_model.py
    ```

3. **Model Conversion**: Convert the trained model to TensorFlow Lite format.

    ```bash
    cd ../deployment/
    python model_conversion.py
    ```

4. **Deploy the Model**: Upload the model to the Arduino.

    - Open `arduino_inference_code.ino` in the Arduino IDE.
    - Upload it to your board.
    - Monitor the serial output to see predictions.

5. **Testing**:

    - **Python Tests**: Run the unit tests for model conversion using `pytest`.

        ```bash
        cd ../tests/
        pytest test_model_conversion.py
        ```

    - **Arduino Tests**: To test the Arduino sketch, upload the `test_arduino_inference.ino` to the board and observe the serial output for validation. Ensure that you have the necessary libraries and dependencies installed on the Arduino IDE.

### Continuous Integration with Jenkins

- **Jenkinsfile**: This file contains the pipeline configuration for building, testing, and deploying the project. It automates the entire process from code updates to deployment.

### Additional Resources

- [TensorFlow Lite Micro Documentation](https://www.tensorflow.org/lite/microcontrollers)
- [Arduino Documentation](https://www.arduino.cc/en/Guide)
- [TensorFlow Model Conversion Guide](https://www.tensorflow.org/lite/guide/conversion)
- [Python Documentation](https://docs.python.org/3/)
- [Jenkins Documentation](https://www.jenkins.io/doc/)
- [Arduino CLI Documentation](https://arduino.github.io/arduino-cli/)
