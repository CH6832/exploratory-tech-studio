# Deep Convolutional GANs

# Importing the libraries
from __future__ import print_function

import torch.nn as nn
import torch.optim as optim
import torch.utils.data
import torchvision.datasets as dset
import torchvision.transforms as transforms
import torchvision.utils as vutils
from torch.autograd import Variable

def main():
    """Main program and entry point"""

    # Setting some hyperparameters
    batchSize = 64  # We set the size of the batch.
    imageSize = 64  # We set the size of the generated images (64x64).

    # Creating the transformations
    transform = transforms.Compose([transforms.Resize(imageSize),
                                    transforms.ToTensor(),
                                    transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5)), ])

    # Loading the dataset
    dataset = dset.CIFAR10(root='./data', download=True, transform=transform)
    dataloader = torch.utils.data.DataLoader(dataset, batch_size=batchSize, shuffle=True, num_workers=2)

    # Creating the generator
    netG = G()
    netG.apply(weights_init)

    # Creating the discriminator
    netD = D()
    netD.apply(weights_init)

    # Training the DCGANs
    criterion = nn.BCELoss()
    optimizerD = optim.Adam(netD.parameters(), lr=0.0002, betas=(0.5, 0.999))
    optimizerG = optim.Adam(netG.parameters(), lr=0.0002, betas=(0.5, 0.999))

    for epoch in range(25):
        for i, data in enumerate(dataloader, 0):
            # 1st Step: Updating the weights of the neural network of the discriminator
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

            # 2nd Step: Updating the weights of the neural network of the generator
            netG.zero_grad()
            target = Variable(torch.ones(input_data.size()[0]))
            output = netD(fake)
            errG = criterion(output, target)
            errG.backward()
            optimizerG.step()

            # 3rd Step: Printing the losses and saving the real images and the generated images
            print('[%d/%d][%d/%d] Loss_D: %.4f Loss_G: %.4f' % (epoch, 25, i, len(dataloader), errD.item(), errG.item()))
            if i % 100 == 0:
                vutils.save_image(real, '%s/real_samples.png' % "./results", normalize=True)
                with torch.no_grad():
                    fake = netG(noise)
                vutils.save_image(fake.detach(), '%s/fake_samples_epoch_%03d.png' % ("./results", epoch), normalize=True)


# Defining the generator
class G(nn.Module):  # We introduce a class to define the generator.

    def __init__(self):  # We introduce the __init__() function that will define the architecture of the generator.
        super(G, self).__init__()  # We inherit from the nn.Module tools.
        self.main = nn.Sequential(
            # We create a meta module of a neural network that will contain a sequence of modules (convolutions,
            # full connections, etc.).
            nn.ConvTranspose2d(100, 512, 4, 1, 0, bias=False),  # We start with an inversed convolution.
            nn.BatchNorm2d(512),  # We normalize all the features along the dimension of the batch.
            nn.ReLU(True),  # We apply a ReLU rectification to break the linearity.
            nn.ConvTranspose2d(512, 256, 4, 2, 1, bias=False),  # We add another inversed convolution.
            nn.BatchNorm2d(256),  # We normalize again.
            nn.ReLU(True),  # We apply another ReLU.
            nn.ConvTranspose2d(256, 128, 4, 2, 1, bias=False),  # We add another inversed convolution.
            nn.BatchNorm2d(128),  # We normalize again.
            nn.ReLU(True),  # We apply another ReLU.
            nn.ConvTranspose2d(128, 64, 4, 2, 1, bias=False),  # We add another inversed convolution.
            nn.BatchNorm2d(64),  # We normalize again.
            nn.ReLU(True),  # We apply another ReLU.
            nn.ConvTranspose2d(64, 3, 4, 2, 1, bias=False),  # We add another inversed convolution.
            nn.Tanh()  # We apply a Tanh rectification to break the linearity and stay between -1 and +1.
        )

    def forward(self,
                input_d):  # We define the forward function that takes as argument an input that will be fed to
        # the neural network, and that will return the output containing the generated images.
        output_d = self.main(
            input_d)  # We forward propagate the signal through the whole neural network of the generator defined
        # by self.main.
        return output_d  # We return the output containing the generated images.




# Defining the discriminator
class D(nn.Module):  # We introduce a class to define the discriminator.

    def __init__(self):  # We introduce the __init__() function that will define the architecture of the discriminator.
        super(D, self).__init__()  # We inherit from the nn.Module tools.
        self.main = nn.Sequential(
            # We create a meta module of a neural network that will contain a sequence of modules (convolutions,
            # full connections, etc.).
            nn.Conv2d(3, 64, 4, 2, 1, bias=False),  # We start with a convolution.
            nn.LeakyReLU(0.2, inplace=True),  # We apply a LeakyReLU.
            nn.Conv2d(64, 128, 4, 2, 1, bias=False),  # We add another convolution.
            nn.BatchNorm2d(128),  # We normalize all the features along the dimension of the batch.
            nn.LeakyReLU(0.2, inplace=True),  # We apply another LeakyReLU.
            nn.Conv2d(128, 256, 4, 2, 1, bias=False),  # We add another convolution.
            nn.BatchNorm2d(256),  # We normalize again.
            nn.LeakyReLU(0.2, inplace=True),  # We apply another LeakyReLU.
            nn.Conv2d(256, 512, 4, 2, 1, bias=False),  # We add another convolution.
            nn.BatchNorm2d(512),  # We normalize again.
            nn.LeakyReLU(0.2, inplace=True),  # We apply another LeakyReLU.
            nn.Conv2d(512, 1, 4, 1, 0, bias=False),  # We add another convolution.
            nn.Sigmoid()  # We apply a Sigmoid rectification to break the linearity and stay between 0 and 1.
        )

    def forward(self,
                input_d):  # We define the forward function that takes as argument an input that will be fed to the
        # neural network, and that will return the output which will be a value between 0 and 1.
        output_d = self.main(
            input_d)  # We forward propagate the signal through the whole neural network of the discriminator defined
        # by self.main.
        return output_d.view(-1)  # We return the output which will be a value between 0 and 1.

# Defining the weights_init function that takes as input a neural network m and that will initialize all its weights.
def weights_init(m):
    classname = m.__class__.__name__
    if classname.find('Conv') != -1:
        m.weight.data.normal_(0.0, 0.02)
    elif classname.find('BatchNorm') != -1:
        m.weight.data.normal_(1.0, 0.02)
        m.bias.data.fill_(0)


if __name__ == "__main__":
    main()