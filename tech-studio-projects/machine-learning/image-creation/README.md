# DCGAN for CIFAR-10 Image Generation

This repository contains an implementation of a Deep Convolutional Generative Adversarial Network (DCGAN) in PyTorch, designed to generate images that resemble those found in the CIFAR-10 dataset.

## Overview

Generative Adversarial Networks (GANs) consist of two neural networks, a Generator and a Discriminator, that are trained together in a competitive manner. The Generator creates images from random noise, attempting to produce realistic-looking images. The Discriminator evaluates these images and tries to distinguish between real images (from the dataset) and fake images (from the Generator). As training progresses, the Generator becomes better at creating realistic images, while the Discriminator improves its ability to distinguish real from fake.

### Key Components

- **Generator (G)**: A neural network that takes random noise as input and outputs images.
- **Discriminator (D)**: A neural network that takes images as input and predicts whether they are real or fake.
- **Loss Function**: Binary Cross-Entropy (BCE) loss is used for both the Generator and Discriminator.
- **Optimizers**: Adam optimizers are used with a learning rate of 0.0002.

## Usage

### Prerequisites

- Python 3.x
- PyTorch
- torchvision

### Running the Script

To start training the DCGAN, simply run:

```bash
python dcgan_cifar10.py
```

The script will download the CIFAR-10 dataset if it is not already present, set up the Generator and Discriminator, and start the training process. The training progress will be printed to the console, and generated images will be saved to the `results` directory.

### Output

- **Losses**: The script prints the Discriminator and Generator losses after each training batch.
- **Images**: Real and generated images are saved periodically in the `./results` directory.
