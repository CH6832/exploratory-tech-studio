#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""main.py

DCGAN implementation for generating images using CIFAR-10 dataset.

This script defines and trains a Deep Convolutional Generative Adversarial Network (DCGAN) 
to generate images that mimic the CIFAR-10 dataset. The architecture includes a Generator 
that creates images from random noise and a Discriminator that distinguishes between real 
and fake images. The two models are trained together in a competitive process, where the 
Generator tries to produce increasingly realistic images and the Discriminator aims to get 
better at detecting fake images.

"""

from __future__ import print_function
import torch
import torch.nn as nn
import torch.optim as optim
import torch.utils.data
import torchvision.datasets as dset
import torchvision.transforms as transforms
import torchvision.utils as vutils
from torch.autograd import Variable


def main():
    """Main function to set up data, models, and training loop."""

    # Setting some hyperparameters
    batchSize = 64  # Batch size for training
    imageSize = 64  # Image size (64x64)

    # Creating the transformations
    transform = transforms.Compose([
        transforms.Resize(imageSize),
        transforms.ToTensor(),
        transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5)),
    ])

    # Loading the dataset
    dataset = dset.CIFAR10(root='./data', download=True, transform=transform)
    dataloader = torch.utils.data.DataLoader(dataset, batch_size=batchSize, shuffle=True, num_workers=2)

    # Creating the generator and discriminator models
    netG = G()
    netG.apply(weights_init)
    netD = D()
    netD.apply(weights_init)

    # Setting up loss function and optimizers
    criterion = nn.BCELoss()
    optimizerD = optim.Adam(netD.parameters(), lr=0.0002, betas=(0.5, 0.999))
    optimizerG = optim.Adam(netG.parameters(), lr=0.0002, betas=(0.5, 0.999))

    # Training loop
    for epoch in range(25):
        for i, data in enumerate(dataloader, 0):
            # 1st Step: Update Discriminator
            netD.zero_grad()
            real, _ = data
            input_data = Variable(real)
            target = Variable(torch.ones(input_data.size()[0]))
            output = netD(input_data)
            errD_real = criterion(output, target)

            noise = Variable(torch.randn(input_data.size()[0], 100, 1, 1))
            fake = netG(noise)
            target = Variable(torch.zeros(input_data.size()[0]))
            output = netD(fake.detach())
            errD_fake = criterion(output, target)

            errD = errD_real + errD_fake
            errD.backward()
            optimizerD.step()

            # 2nd Step: Update Generator
            netG.zero_grad()
            target = Variable(torch.ones(input_data.size()[0]))
            output = netD(fake)
            errG = criterion(output, target)
            errG.backward()
            optimizerG.step()

            # 3rd Step: Print losses and save images
            print('[%d/%d][%d/%d] Loss_D: %.4f Loss_G: %.4f' % (epoch, 25, i, len(dataloader), errD.item(), errG.item()))
            if i % 100 == 0:
                vutils.save_image(real, '%s/real_samples.png' % "./results", normalize=True)
                with torch.no_grad():
                    fake = netG(noise)
                vutils.save_image(fake.detach(), '%s/fake_samples_epoch_%03d.png' % ("./results", epoch), normalize=True)


class G(nn.Module):
    """Generator network that takes random noise as input and generates images."""

    def __init__(self):
        super(G, self).__init__()
        self.main = nn.Sequential(
            nn.ConvTranspose2d(100, 512, 4, 1, 0, bias=False),
            nn.BatchNorm2d(512),
            nn.ReLU(True),
            nn.ConvTranspose2d(512, 256, 4, 2, 1, bias=False),
            nn.BatchNorm2d(256),
            nn.ReLU(True),
            nn.ConvTranspose2d(256, 128, 4, 2, 1, bias=False),
            nn.BatchNorm2d(128),
            nn.ReLU(True),
            nn.ConvTranspose2d(128, 64, 4, 2, 1, bias=False),
            nn.BatchNorm2d(64),
            nn.ReLU(True),
            nn.ConvTranspose2d(64, 3, 4, 2, 1, bias=False),
            nn.Tanh()
        )

    def forward(self, input_d):
        return self.main(input_d)


class D(nn.Module):
    """Discriminator network that takes images as input and predicts whether they are real or fake."""

    def __init__(self):
        super(D, self).__init__()
        self.main = nn.Sequential(
            nn.Conv2d(3, 64, 4, 2, 1, bias=False),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(64, 128, 4, 2, 1, bias=False),
            nn.BatchNorm2d(128),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(128, 256, 4, 2, 1, bias=False),
            nn.BatchNorm2d(256),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(256, 512, 4, 2, 1, bias=False),
            nn.BatchNorm2d(512),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(512, 1, 4, 1, 0, bias=False),
            nn.Sigmoid()
        )

    def forward(self, input_d):
        return self.main(input_d).view(-1)


def weights_init(m):
    """Initialize model weights with a normal distribution."""
    classname = m.__class__.__name__
    if classname.find('Conv') != -1:
        m.weight.data.normal_(0.0, 0.02)
    elif classname.find('BatchNorm') != -1:
        m.weight.data.normal_(1.0, 0.02)
        m.bias.data.fill_(0)


if __name__ == "__main__":
    main()
