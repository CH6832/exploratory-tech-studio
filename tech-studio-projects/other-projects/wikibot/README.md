# WikiBot

## :newspaper: About the project

A minimalistic ChatBot implemented in Python that can retrieve information from Wikipedia based on keywords provided by a user.

### How it works

The chatbot operates by first receiving user input through a text input field in a web interface. Upon receiving the input, it normalizes the text by converting it to lowercase and removing punctuation.

The normalized input is then checked against a conversation history to see if there's a pre-existing response. If not found, the chatbot queries the Wikipedia API to fetch relevant information based on the input.

This information is then cached for future use. The bot responds to the user with the fetched data or a default message if no relevant information is found. The conversation history, consisting of user inputs and bot responses, is stored in a file for reference. Additionally, the chat interface displays the conversation history, with user and bot messages displayed in separate chat bubbles.

The chatbot continuously interacts with users in this manner, dynamically fetching information and updating the conversation history as needed.

### Content overview

    .
    ├── docs/ - project documentation
    ├── img/ - images for the README.md
    ├── static/ - contains stylesheets
    ├── templates/ - contains the html templates
    ├── tests/ - contains unit tests
    ├── chatbot.py - program entry point
    ├── CODE_OF_CONDUCT.md - project code of conduct
    ├── README.md - relevant information about the project
    └── requirements.txt - requirements to run the project

## :runner: Getting started

### Prerequisites and example usage

1. Clone the project and extract the folder:

```bash
git clone https://github.com/CH6832/chatbot.git
```

2. Open the entire project in an IDE of your choice.

3. Run the app:

```bash
python3 chatbot.py
```

4. the open the link in your browser and start interacting with it:

![Chatbot starting page](img/chatbot_webpage.png)

### Generate documentation

0. Create a `docs\` folder:

```sh
mkdir docs
```

1. Move into the folder:

```sh
cd docs
```

2. Initialize a WikiBot project:

```sh
sphinx-quickstart
```

3. Fill the `.rst` files with content, e.g. `index.rst`:

```sh
.. WikiBot documentation master file, created by
   sphinx-quickstart on Thu May 30 21:14:20 2024.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to WikiBot's documentation!
===================================

.. toctree::
   :maxdepth: 2
   :caption: Contents:

   installation
   usage
   api
   contributing

Introduction
============

WikiBot is a minimalistic chatbot implemented in Python that retrieves information from Wikipedia based on keywords provided by a user. This bot uses Flask for the web interface and interacts with the Wikipedia API to fetch relevant data.

Indices and tables
==================

* :ref:`genindex`
* :ref:`modindex`
* :ref:`search`
```

4. Generate the documentation:

```sh
make html
```

## :books: Resources used to create this project

* Python
  * [Python documentation](https://docs.python.org/3/)
  * [Built-in Functions](https://docs.python.org/3/library/functions.html)
  * [Python Module Index](https://docs.python.org/3/py-modindex.html)
  * [Flask](https://flask.palletsprojects.com/en/3.0.x/)
* Wikipedia API
  * [Getting started with Wikimedia APIs](https://api.wikimedia.org/wiki/Getting_started_with_Wikimedia_APIs)
* Editor
  * [Visual Studio Code](https://code.visualstudio.com/)
* Chatbots
  * Books
    * [Designing Bots: Creating Coversational Experience](https://archive.org/details/designing-bots-creating-conversational-experiences-pdfdrive/page/n5/mode/2up)
    * Artificial Intelligence: A Guide for Thinking Humans
    * [Building Chatbots with Python: Using Natural Language Processing and Machine Learning](https://www.studocu.com/vn/document/dai-hoc-mo-dia-chat/ky-thuat-robot-va-tri-tue-nhan-tao/sumit-raj-building-chatbots-with-python-using-natural-language-processing-and-machine-learning-2019-apress/80556899)
  * Articles
    * [A Survey on Chatbot Implementation in Customer Service Industry through Deep Neural Networks](https://ieeexplore.ieee.org/document/8592630)
    * [Conversational Agents and Natural Language Interaction: Techniques and Effective Practices](https://www.researchgate.net/publication/235913109_Conversational_Agents_and_Natural_Language_Interaction_Techniques_and_Effective_Practices)
  * Communities and Forums
    * [Chatbots Life](https://www.chatbotslife.com/)
  * Webinars and Conferences
    * [The Bot Podcast](https://www.youtube.com/@TheBotPodcast)
