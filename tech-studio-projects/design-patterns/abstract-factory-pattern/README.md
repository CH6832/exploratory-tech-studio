# Abstract Factory Pattern

## Description

The abstract factory pattern in software engineering is a design pattern that provides a way to create families of related objects without imposing their concrete classes, by encapsulating a group of individual factories that have a common theme without specifying their concrete classes.[1] According to this pattern, a client software component creates a concrete implementation of the abstract factory and then uses the generic interface of the factory to create the concrete objects that are part of the family. The client does not know which concrete objects it receives from each of these internal factories, as it uses only the generic interfaces of their products. This pattern separates the details of implementation of a set of objects from their general usage and relies on object composition, as object creation is implemented in methods exposed in the factory interface. [https://en.wikipedia.org/wiki/Abstract_factory_pattern](https://en.wikipedia.org/wiki/Abstract_factory_pattern)

## Scenarios

* System needs to be independent of product creation, composition, and representation
* A family of related product objects is designed to be used together
* The system needs to enforce consistency among objects created by a factory
* The system should be able to handle new types of products

## Examples of Use Cases

* GUI Toolkits
* Document Creation System
* Game Development
