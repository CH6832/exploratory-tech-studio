#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Image Classification API using FastAPI and PyTorch.

This module provides a FastAPI application that loads a pre-trained ResNet model
and serves an endpoint for image classification. The model is used to predict the
label of the provided image file.
"""

from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse
from typing import List
import torch
import torchvision.transforms as transforms
import torchvision.models as models
import uvicorn
from PIL import Image
import io

app = FastAPI()

# Define global variables
model = None
labels = ['cat', 'dog', 'bird', 'fish']  # Example labels for illustration purposes

# Define image transformation pipeline
transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
])

def load_model() -> None:
    """
    Load the pre-trained ResNet model and its state_dict.
    This function sets the model to evaluation mode.
    """
    global model
    model = models.resnet18(pretrained=True)
    model.load_state_dict(torch.load('image_classification_model.pth', map_location=torch.device('cpu')))
    model.eval()

@app.on_event("startup")
async def startup_event() -> None:
    """
    FastAPI startup event handler to load the model.
    """
    load_model()

@app.post("/predict")
async def predict(file: UploadFile = File(...)) -> JSONResponse:
    """
    Endpoint to classify the uploaded image.

    Args:
        file (UploadFile): The uploaded image file.

    Returns:
        JSONResponse: The predicted label of the image.
    """
    # Read image file and preprocess
    contents = await file.read()
    image = Image.open(io.BytesIO(contents))
    image = transform(image).unsqueeze(0)

    # Perform model inference
    with torch.no_grad():
        output = model(image)

    # Post-processing: Get predicted label
    _, predicted = torch.max(output, 1)
    predicted_label = labels[predicted.item()]

    return JSONResponse(content={"prediction": predicted_label})

if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000)
