from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse
from typing import List
import torch
import torchvision.transforms as transforms
import torchvision.models as models
import uvicorn
from PIL import Image

app = FastAPI()

# Load your model during startup
@app.on_event("startup")
async def startup_event():
    global model
    # Load pre-trained ResNet model
    model1 = models.resnet18(pretrained=True)
    model1.load_state_dict(torch.load('image_classification_model.pth', map_location=torch.device('cpu')))
    model1.eval()

# Load pre-trained ResNet model
model1 = models.resnet18(pretrained=True)

# Save the model's parameters
torch.save(model1.state_dict(), 'image_classification_model.pth')

# Load the state_dict into the model
state_dict = torch.load('image_classification_model.pth', map_location=torch.device('cpu'))
model1.load_state_dict(state_dict)

# Set the model to evaluation mode
model1.eval()
labels = ['cat', 'dog', 'bird', 'fish']  # Example labels for illustration purposes

# Define image transformation pipeline
transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
])

# Define your prediction endpoint
@app.post("/predict")
async def predict(file: UploadFile = File(...)) -> JSONResponse:
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