# XBRL Taxonomy Package Conformant Processor

## :newspaper: About the Project

A small command line program that checks whether an XBRL Taxonomy Package complies with the [Standard Taxonomy Package 1.0](https://www.xbrl.org/Specification/taxonomy-package/REC-2016-04-19/taxonomy-package-REC-2016-04-19.html) and adjusts it if necessary.

### How It Works

1. Run the application using the command: 
   ```sh
   java -jar target/xbrl-taxonomy-processor.jar EDINET "full/path/to/input/archive.zip"
   ```
   Here, `EDINET` represents the Electronic Disclosure System provided by the [JFSA](https://www.fsa.go.jp/en/). The path should point to the XBRL Taxonomy Package (ZIP). Packages for testing are located in the `input` folder.

2. The `TPChecker` class analyzes the package according to the [Taxonomy Package 1.0 standard](https://www.xbrl.org/Specification/taxonomy-package/REC-2016-04-19/taxonomy-package-REC-2016-04-19.html). The results of the analysis are displayed in the command line.

3. Based on the results calculated by the `TPChecker`, the next step is to fix the package. The `TPFixer` class implements the relevant methods for package correction. When initialized, the package to fix is copied to the `output` folder. The defined methods in this class are responsible for fixing the package, resulting in a corrected ZIP archive containing all relevant data.

### Content Overview

```
.
├── dist/               - Java archive (JAR) file to install
├── docs/               - Generated documentation
├── images/readme/      - All images for the app
├── input/              - Taxonomy packages
├── logs/               - Log files
├── src/                - Source code
├── tests/              - Code and data for tests
├── .gitignore          - Contains folders/files ignored by Git
├── CHANGELOG.md        - Log file about project changes
├── COPYRIGHT           - Project copyright
├── LICENSE             - License text
├── README.md           - Project overview and starting point
├── requirements.txt    - Requirements to run the project (if applicable)
├── SECURITY.md         - Security information
└── pom.xml             - Maven project metadata
```

## :notebook: Features

* Checking and fixing:
  * XML format checking
  * Case sensitivity checking
  * Archive format check
  * Top-level directory checking and fixing
  * META-INF folder checking and fixing
  * `taxonomyPackage.xml` checking and fixing
  * `catalog.xml` checking and fixing
  * URL resolution checking and fixing
  * Entry point localization

## :runner: Getting Started

### Prerequisites and Example Usage

0. Install Java Development Kit (JDK) and Apache Maven.

1. Navigate to the project directory.

```sh
cd path/to/your/project
```

2. Build the project using Maven:

```sh
mvn clean package
```

3. Run the application:

```sh
java -jar target/xbrl-taxonomy-processor.jar [PROVIDER] [PATH/TO/PKG]
```

Example:

```sh
java -jar target/xbrl-taxonomy-processor.jar EDINET "input/ALL_20221101/ALL_20221101.zip"
```

**Example Output:**

```
Input information:
------------------
    Provider -> EDINET
    Package  -> ..\input\ALL_20221101\ALL_20221101.zip

Analysis results:
------------------
    DONE: Package is ZIP
    ERROR: Package has no single top-level directory
    ERROR: Package has no META-INF folder
    ERROR: Package has no catalog.xml
    ERROR: Package has no taxonomy-package.xml

Fixing package...
Output path: ..\output\ALL_20221101\ALL_20221101_fixed.zip
    Final zip generated

Output result:
--------------
    ..\output\ALL_20221101\ALL_20221101_fixed.zip is fixed!
```

### Run Tests

1. Move to the `tests/` directory:

```sh
cd tests/
```

2. Run the tests using Maven:

```sh
mvn test
```

3. Alternatively, you can run individual tests using:

```sh
mvn -Dtest=YourTestClass test
```

### Create Documentation

1. Move to the `docs/` folder:

```sh
cd docs
```

2. Initialize the documentation project (if not already done):

```sh
sphinx-quickstart 
```

3. Build/rebuild the documentation:

```sh
make html
```

4. Open `index.html` in your browser from the `docs/build/html` directory.

![index.html](/images/readme/docs_index.jpg)

### Build and Install the `.jar` Package

0. Upgrade packages:

```sh
mvn clean install
```

1. Build the package:

```sh
mvn package
```

2. Install the package:

```sh
mvn install:install-file -Dfile=target/xbrl-taxonomy-processor.jar -DgroupId=com.example -DartifactId=xbrl-taxonomy-processor -Dversion=1.0 -Dpackaging=jar
```

## :bulb: Tips and Tricks

- Use absolute paths for input XBRL taxonomy packages to avoid any path-related issues.
- Ensure your input packages adhere to the XBRL Taxonomy Package 1.0 standard for accurate processing.

## :wrench: Troubleshooting

If you encounter any issues while using the tool, consider the following troubleshooting steps:

- Check if all prerequisites are installed correctly.
- Verify that the input XBRL taxonomy package is valid and adheres to the required format.
- Refer to the error messages for clues on what might be going wrong.
- Search for similar issues in the project's GitHub repository or online forums.

## :loudspeaker: Contributing

We welcome contributions from the community! If you'd like to contribute to the project, please follow these steps:

* Fork the repository.
* Create a new branch for your feature or bug fix.
* Make your changes and ensure all tests pass.
* Submit a pull request with a clear description of your changes.

## :open_book: Documentation

For more information on how to use the tool and its features, open the [official documentation](/docs/build/html/index.html) in a browser of your choice.

## :rocket: Roadmap

Here are some planned features and enhancements for future releases:

- Support for additional XBRL taxonomy package standards.
- Improved error handling and logging.
- Integration with other XBRL processing tools.

## :raising_hand: Support

If you need any assistance or have any questions about the project, feel free to reach out to us via email or open a new issue in the GitHub repository.

## :bookmark: License

This project is licensed under the terms of the [GPL v3](LICENSE).

## :copyright: Copyright

See the [COPYRIGHT](COPYRIGHT) file for copyright and licensing details.

## :books: Resources Used to Create This Project

* Java
  * [Java SE Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
  * [Maven Documentation](https://maven.apache.org/guides/index.html)
* XBRL
  * [Extensible Business Reporting Language (XBRL) 2.1](https://www.xbrl.org/Specification/XBRL-2.1/REC-2003-12-31/XBRL-2.1-REC-2003-12-31+corrected-errata-2013-02-20.html)
  * [Taxonomy Packages 1.0](https://www.xbrl.org/Specification/taxonomy-package/REC-2016-04-19/taxonomy-package-REC-2016-04-19.html)
* XML
  * [Extensible Markup Language (XML) 1.0 (Fifth Edition)](https://www.w3.org/TR/xml/)
  * [W3C XML Schema Definition Language (XSD) 1.1 Part 1: Structures](https://www.w3.org/TR/xmlschema11-1/)
* Command Line Tools
  * [Master the Art of Command Line: Your Ultimate Guide to Developing Powerful Tools](https://hackernoon.com/master-the-art-of-command-line-your-ultimate-guide-to-developing-powerful-tools)
  * [Command Line Interface Guidelines](https://clig.dev/)
* Markdown
  * [Basic Syntax](https://www.markdownguide.org/basic-syntax/)
  * [Complete List of GitHub Markdown Emojis](https://dev.to/nikolab/complete-list-of-github-markdown-emoji-markup-5aia)
  * [Awesome Template](http://github.com/Human-Activity-Recognition/blob/main/README.md)
  * [.gitignore File](https://git-scm.com/docs/gitignore)
* Editor
  * [Eclipse IDE](https://www.eclipse.org/)
