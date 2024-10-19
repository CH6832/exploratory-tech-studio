# Bean Disease Classifier

This project is a Java implementation of a neural network classifier to distinguish between healthy bean leaves and two types of bean diseases: bean rust and angular leaf spots. The project demonstrates downloading datasets, unzipping them, and plotting model accuracy using JFreeChart.

## Prerequisites

Before running the project, ensure you have the following:

- **Java Development Kit (JDK)**: Version 17 or higher.
- **Maven**: For dependency management.

## Setup Instructions

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/yourusername/BeanDiseaseClassifier.git
cd BeanDiseaseClassifier
```

### 2. Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

### 3. Run the Application

Execute the application using:

```bash
mvn exec:java -Dexec.mainClass="com.example.BeanDiseaseClassifier"
```

## Notes

- The actual model training logic is not implemented in this version. This is a placeholder for demonstrating data downloading and plotting accuracy.
- Ensure you have internet access, as the datasets are downloaded during execution.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

### Step 5: Running the Project

1. Make sure you have **Maven** installed.
2. Open a terminal, navigate to the `BeanDiseaseClassifier` directory, and run the following commands:
   - `mvn clean install` to build the project.
   - `mvn exec:java -Dexec.mainClass="com.example.BeanDiseaseClassifier"` to run the application.

### Step 6: Dependencies and Limitations

- **TensorFlow for Java**: As of now, this example does not include model training directly in Java due to the complexity and limitations of the Java API for TensorFlow. For a complete training setup, it’s often easier to train the model in Python and then export it for inference in Java.
- **JFreeChart**: Make sure the JavaFX libraries are properly configured if you face any issues with plotting.
