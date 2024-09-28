# Horse vs Human Image Classification

This project is designed to classify images of horses and humans using a Convolutional Neural Network (CNN) with TensorFlow and Keras. It includes scripts for data preprocessing, model definition, training, predictions, and visualization of model layers.

## Project Structure

```
project/
│
├── data_preprocessing.py        # Handles downloading and extracting dataset
├── model_definition.py          # Defines and compiles the model
├── data_generators.py           # Sets up data generators for training and validation
├── model_training.py            # Trains the model
├── predictions.py               # Makes predictions on uploaded images
├── visualize_layers.py          # Visualizes intermediate layers of the model
└── main.py                      # Main script to run the entire process
```

## Requirements

- Python 3.x
- TensorFlow >= 2.x
- Keras
- wget
- matplotlib
- Google Colab (for predictions)

You can install the required Python packages using `pip`:

```bash
pip install tensorflow matplotlib wget
```

## Setup and Usage

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### 2. Download and Extract Data

The `data_preprocessing.py` script will automatically download and extract the dataset. You don't need to perform this step manually unless you want to handle the data differently.

### 3. Define the Model

The `model_definition.py` script defines a CNN model and compiles it. The model architecture is tailored for image classification tasks with a binary output (horse vs. human).

### 4. Create Data Generators

The `data_generators.py` script sets up data generators for training and validation. It includes data augmentation for the training set.

### 5. Train the Model

Run the `model_training.py` script to train the model:

```bash
python model_training.py
```

### 6. Make Predictions

For making predictions on new images, you can use the `predictions.py` script in Google Colab:

```python
import predictions
model = ... # Load or define your trained model here
results = predictions.make_predictions(model)
print(results)
```

**Note:** In Google Colab, you'll need to upload images manually when prompted.

### 7. Visualize Intermediate Layers

To visualize the intermediate layers of the trained model, use the `visualize_layers.py` script:

```python
import visualize_layers
model = ... # Load or define your trained model here
visualize_layers.visualize_layers(model, train_horse_dir, train_human_dir)
```

### 8. Run the Entire Pipeline

You can run the entire pipeline by executing the `main.py` script. This script ties together data preprocessing, model training, predictions, and visualization:

```bash
python main.py
```

## Detailed Explanation of Each File

### `data_preprocessing.py`

- **Function:** Downloads and extracts datasets, sets directory paths for training and validation.
- **Key Functions:** `download_and_extract_data`, `print_sample_file_names`.

### `model_definition.py`

- **Function:** Defines the CNN architecture and compiles the model.
- **Key Function:** `create_model`.

### `data_generators.py`

- **Function:** Sets up training and validation data generators with augmentation for the training set.
- **Key Function:** `create_data_generators`.

### `model_training.py`

- **Function:** Trains the model using the provided data generators.
- **Key Function:** `train_model`.

### `predictions.py`

- **Function:** Allows for image upload and prediction using the trained model.
- **Key Function:** `make_predictions`.

### `visualize_layers.py`

- **Function:** Visualizes feature maps of intermediate layers of the model.
- **Key Function:** `visualize_layers`.

### `main.py`

- **Function:** Runs the entire workflow including data preprocessing, model training, predictions, and visualization.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- TensorFlow and Keras for providing the tools for building and training the model.
- Google Colab for providing a convenient environment for predictions.

## Contact

For any questions or suggestions, please contact [your-email@example.com](mailto:your-email@example.com).

---

Feel free to adapt or expand this README based on your specific needs or additional functionalities.
