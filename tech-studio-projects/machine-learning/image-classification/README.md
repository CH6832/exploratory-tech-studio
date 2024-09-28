# FastAPI Image Classification API

This project provides a RESTful API using FastAPI and PyTorch to perform image classification. The API uses a pre-trained ResNet model to classify images into one of several predefined categories. 

## Features

- **Image Classification**: Predicts labels for uploaded images using a ResNet model.
- **Pre-trained Model**: Uses a ResNet-18 model with pre-loaded weights.
- **FastAPI Integration**: Serves the classification model via a RESTful API.
- **Asynchronous Handling**: Utilizes asynchronous operations for file handling and model inference.

## Prerequisites

Ensure you have the following installed:

- Python 3.7 or later
- `pip` (Python package installer)

You will need to install the following Python packages:

- `fastapi`: The web framework for building APIs.
- `uvicorn`: ASGI server for serving FastAPI applications.
- `torch`: PyTorch library for model handling and inference.
- `torchvision`: Provides pre-trained models and image transformations.
- `Pillow`: Python Imaging Library for image processing.

Install the required packages with:

```bash
pip install fastapi uvicorn torch torchvision pillow
