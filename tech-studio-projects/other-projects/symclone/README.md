# SymClone

## :newspaper: About the project

Discover the limitless potential of SymClone, a robust Python library meticulously crafted to empower users with unparalleled capabilities in symbolic mathematics, rivaling the esteemed SymPy library. Seamlessly navigate the realm of advanced mathematical computations as SymClone unleashes a spectrum of features, enabling you to effortlessly manipulate symbolic expressions, conduct intricate calculus operations, elegantly format expressions for seamless readability, and embark on a journey of mathematical exploration like never before. Elevate your computational prowess with SymClone and unlock a world of boundless mathematical possibilities at your fingertips.

### Content overview

    .
    ├── dist/ - contains the generated distribution packages
    ├── symclone/ - symclone source code
    ├── symclone_package.egg-info/ - metadata about the package
    ├── tests/ - project tests
    ├── .gitignore - contains folders/files ignored by git
    ├── CODE_OF_CONDUCT.md - project code of conduct
    ├── COPYRIGHT - project copyright
    ├── example_usage.py - example usage of symclone
    ├── LICENSE - license text
    ├── pyproject.toml - build system and dependencies specifications
    ├── README.md - relevant information about the project
    ├── requirements.txt - requirements to run the project    
    └── setup.py - configurations for setuptools

## :notebook: Features

- **Symbolic Expression Handling**: The project allows users to work with symbolic expressions, including mathematical operations like addition, subtraction, multiplication, division, exponentiation, and trigonometric functions.
- **Expression Evaluation**: Users can evaluate expressions numerically by substituting specific values for symbolic variables.
- **Derivative Calculation**: The project supports the calculation of derivatives of expressions with respect to given symbols.
- **Integral Calculation**: Users can compute definite integrals of expressions over specified ranges.
- **Limit Calculation**: The project provides functionality to compute the limit of expressions as symbols approach specific values.
- **Series Expansion**: Users can compute Taylor series expansions of expressions around given points up to a specified order.
- **Custom Exception Handling**: The project includes custom exception handling to provide meaningful error messages when encountering issues like undefined limits or errors during computation.
- **Modular Design**: The code is organized into separate modules for expressions, symbols, and mathematical operations, promoting code reusability and maintainability.
- **User-Friendly Interface**: The provided example usage script demonstrates how users can interact with the project in a straightforward manner, making it accessible for those unfamiliar with the underlying implementation.
- **Testability**: The project components are designed to be easily testable, allowing for thorough testing to ensure correctness and reliability.

## :runner: Getting started

### Prerequisites and example usage

0. Clone repository:

```sh
git clone https://github.com/CH6832/symclone.git
```

1. Move into `symclone` directory. 

```sh
cd symclone
```

2. Install requirements:

```sh
pip3 install -r requirements.txt
```

3. Run script to see how it works:

```sh
python3 example_usages.py
```

### Build and install the `.whl` package

0. Upgrade packages:

```sh
py -m pip install --upgrade build 
```

1. Build package:

```sh
py -m build
```

2. Install the package:

```sh
py -m pip install "dist/example_package_CHRISTOPH_HARTLEB-0.0.1-py3-none-any.whl"
```

### Generate documentation

0. Create a `docs\` folder:

```sh
mkdir docs
```

1. Move into the folder:

```sh
cd docs
```

2. Initialize a Sphinx project:

```sh
sphinx-quickstart
```

3. Fill the `.rst` files with content, e.g. `index.rst`:

```sh
.. SymClone documentation master file, created by
   sphinx-quickstart on Sat May 28 2024.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to SymClone's documentation!
====================================

.. toctree::
   :maxdepth: 2
   :caption: Contents:

   modules
   Expression
   FuzzyBoolean
   MathOperations
   Symbol

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

5. Open the `docs/_build/html/index.html` file in a browser of your choice.

## :books: Resources used to create this project

* Python
  * [Python 3.12 documentation](https://docs.python.org/3/)
  * [Built-in Functions](https://docs.python.org/3/library/functions.html)
  * [Python Module Index](https://docs.python.org/3/py-modindex.html)
  * [math — Mathematical functions](https://docs.python.org/3/library/math.html)
  * [Packaging Python Projects](https://packaging.python.org/en/latest/tutorials/packaging-projects/)
  * [unittest](https://docs.python.org/3/library/unittest.html)
  * [Sphinx](https://www.sphinx-doc.org/en/master/)
* Markdwon
  * [Basic syntax](https://www.markdownguide.org/basic-syntax/)
  * [Complete list of github markdown emofis](https://dev.to/nikolab/complete-list-of-github-markdown-emoji-markup-5aia)
  * [Awesome template](http://github.com/Human-Activity-Recognition/blob/main/README.md)
  * [.gitignore file](https://git-scm.com/docs/gitignore)
* Editor
  * [Visual Studio Code](https://code.visualstudio.com/)

## :bookmark: License

[Individual LICENSE](LICENSE) :copyright: 2024 Christoph Hartleb

## :copyright: Copyright

See the [COPYRIGHT](COPYRIGHT) file for copyright and licensing details.

## :straight_ruler: Code of Conduct

Please review our [Code of Conduct](CODE_OF_CONDUCT.md) before contributing to this project.
