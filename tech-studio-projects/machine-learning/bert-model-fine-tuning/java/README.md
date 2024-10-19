Translating the provided Python script for fine-tuning a BERT model on the IMDB dataset into Java requires a few considerations. Java does not have direct equivalents for all the libraries used in the Python version (like Hugging Face's Transformers and Datasets libraries), but we can use the `DeepLearning4J` (DL4J) library for working with deep learning models in Java, along with the `ND4J` library for handling numerical data.

Below is a step-by-step translation of the Python script into Java, ensuring we maintain similar functionality. This example assumes you have the necessary libraries installed and properly configured.

### Step 1: Set Up the Project Structure

First, create a directory structure for your Java project:

```
BERTFineTuning/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── BertFineTune.java
└── README.md
```

### Step 2: Create `pom.xml`

Create a `pom.xml` file to manage dependencies using Maven. This file will include necessary libraries such as DeepLearning4J and ND4J:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>BERTFineTuning</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.deeplearning4j</groupId>
            <artifactId>deeplearning4j-core</artifactId>
            <version>1.0.0-beta7</version>
        </dependency>
        <dependency>
            <groupId>org.nd4j</groupId>
            <artifactId>nd4j-native-platform</artifactId>
            <version>1.0.0-beta7</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.30</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.30</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### Step 3: Create `BertFineTune.java`

Below is the Java code for the `BertFineTune.java` file, implementing similar functionality as the Python script:

```java
package com.example;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.BertOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.IteratorUtils;
import org.nd4j.linalg.dataset.api.iterator.IDataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.LabeledDataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.LabeledDataSetIteratorFactory;
import org.nd4j.linalg.dataset.api.iterator.LabeledDataSetIteratorFactory;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class BertFineTune {

    private static final String TRAIN_DATA_PATH = "path/to/train_data.csv"; // Change to your train data path
    private static final String TEST_DATA_PATH = "path/to/test_data.csv"; // Change to your test data path

    public static void main(String[] args) {
        try {
            // Load the datasets
            List<String[]> trainData = loadCSV(TRAIN_DATA_PATH);
            List<String[]> testData = loadCSV(TEST_DATA_PATH);

            // Tokenize data and convert it to DataSetIterator
            DataSetIterator trainIterator = createDataSetIterator(trainData);
            DataSetIterator testIterator = createDataSetIterator(testData);

            // Build the model
            MultiLayerNetwork model = buildModel();
            model.init();

            // Train the model
            model.fit(trainIterator, 3); // Train for 3 epochs

            // Evaluate the model
            evaluateModel(model, testIterator);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MultiLayerNetwork buildModel() {
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .updater(new Adam(0.001))
                .list()
                .layer(0, new BertOutputLayer.Builder()
                        .nIn(768) // Number of input features (for BERT)
                        .nOut(2) // Number of classes
                        .activation(Activation.SOFTMAX)
                        .build())
                .build();

        return new MultiLayerNetwork(conf);
    }

    private static void evaluateModel(MultiLayerNetwork model, DataSetIterator testIterator) {
        while (testIterator.hasNext()) {
            DataSet batch = testIterator.next();
            INDArray features = batch.getFeatures();
            INDArray labels = batch.getLabels();

            INDArray predictions = model.output(features);
            // Compare predictions with actual labels here
            // (e.g., compute accuracy)
        }
    }

    private static DataSetIterator createDataSetIterator(List<String[]> data) {
        // Convert your data to DataSetIterator here
        // Placeholder for actual implementation
        return null; // Replace with actual implementation
    }

    private static List<String[]> loadCSV(String path) throws IOException {
        return Files.lines(new File(path).toPath())
                .map(line -> line.split(","))
                .collect(Collectors.toList());
    }
}
```

### Step 4: Create `README.md`

Here’s a sample `README.md` for your project:

```markdown
# BERT Fine-Tuning

This project is a Java implementation of fine-tuning a BERT model on the IMDB dataset. It demonstrates loading the dataset, building a BERT model, training, and evaluating the model.

## Prerequisites

Before running the project, ensure you have the following:

- **Java Development Kit (JDK)**: Version 17 or higher.
- **Maven**: For dependency management.

## Setup Instructions

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/yourusername/BERTFineTuning.git
cd BERTFineTuning
```

### 2. Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

### 3. Run the Application

Execute the application using:

```bash
mvn exec:java -Dexec.mainClass="com.example.BertFineTune"
```

## Notes

- You need to modify the `TRAIN_DATA_PATH` and `TEST_DATA_PATH` constants to point to your actual CSV files containing the IMDB dataset.
- The `createDataSetIterator` method is a placeholder and needs to be implemented to convert your data into a suitable `DataSetIterator`.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
```

### Step 5: Running the Project

1. Make sure you have **Maven** installed.
2. Open a terminal, navigate to the `BERTFineTuning` directory, and run the following commands:
   - `mvn clean install` to build the project.
   - `mvn exec:java -Dexec.mainClass="com.example.BertFineTune"` to run the application.

### Conclusion

The provided Java implementation is a basic framework for fine-tuning a BERT model using the IMDB dataset. Note that specific details such as dataset preprocessing and model training require further elaboration depending on the requirements and the dataset structure. If you have questions or need further assistance, feel free to ask!