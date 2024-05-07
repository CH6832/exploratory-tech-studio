# Real-Time Operating System (RTOS) for Embedded Systems

This project implements a simple Real-Time Operating System (RTOS) for embedded systems using C++. The RTOS provides task scheduling and execution, task suspension and resumption, and inter-task communication capabilities.

## Overview

The RTOS consists of the following components:

- `main.cpp`: Main program file containing the entry point and initialization logic.
- `rtos.cpp`: Implementation of the RTOS functionalities including task management and scheduling.
- `task.h`: Header file defining the Task structure and related functions.
- `communication.h`: Header file defining functions for inter-task communication.

## Build Steps

To build and run the RTOS on a Raspberry Pi, follow these steps:

1. **Clone the Repository**: 
   Clone this repository to your local machine using Git:

   ```bash
   git clone https://github.com/yourusername/rtos-project.git

2. Navigate to the Project Directory:

Change into the project directory:

```
cd rtos-project
```

3. Compile the Program:

Compile the program using g++ compiler:

```
g++ -std=c++11 -pthread main.cpp rtos.cpp -o rtos
```

4. Run the Program:

Execute the compiled program:

```
./rtos
```

Usage Example

Here's an example of how to create and run tasks using the RTOS:

```cpp
#include "rtos.h"

int main() {
    // Initialize the RTOS
    RTOS rtos;

    // Add tasks to the RTOS
    rtos.addTask(1, 2, 100, false); // Non-preemptable task
    rtos.addTask(2, 1, 200, true);  // Preemptable task
    rtos.addTask(3, 3, 150, false); // Non-preemptable task

    // Run the RTOS scheduler
    rtos.scheduler();

    return 0;
}
```

## CI/CD

Install Jenkins on Raspberry Pi:

You can install Jenkins directly on your Raspberry Pi by following the official documentation or using package managers like apt. Here's a general outline of the steps:

```sh
sudo apt update
sudo apt install openjdk-11-jdk
wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt update
sudo apt install jenkins
```

Once installed, start Jenkins:

```sh
sudo systemctl start jenkins
```

And enable it to start on boot:

```sh
sudo systemctl enable jenkins
```

2. Install Docker on Raspberry Pi:

Install Docker on your Raspberry Pi using the official installation script:

```sh
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
```

After Docker is installed, add your user to the docker group to run Docker commands without sudo:

```sh
sudo usermod -aG docker $USER
```

Log out and log back in to apply the changes.

3. Set up Jenkins Pipeline:

Create a Jenkins pipeline job to build your program. You can define the build steps in a Jenkinsfile stored in your project repository. Here's a basic example of a Jenkinsfile:

```goovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'docker run --rm -v $(pwd):/app -w /app arm32v7/debian:latest /bin/bash -c "apt-get update && apt-get install -y g++ make && g++ -std=c++11 -pthread main.cpp rtos.cpp -o rtos"'
            }
        }
    }
}
```

This pipeline configuration defines a single stage (Build) that runs on any available agent. It uses a Docker container (arm32v7/debian:latest) to compile the C++ source files (main.cpp and rtos.cpp) into an executable named rtos.

4. Run Jenkins Job:

Access Jenkins in your web browser (typically at http://localhost:8080) and create a new pipeline job. Point the job to your Git repository containing the Jenkinsfile. Trigger the job to run, and Jenkins will execute the pipeline steps, compiling your program on the Raspberry Pi.
