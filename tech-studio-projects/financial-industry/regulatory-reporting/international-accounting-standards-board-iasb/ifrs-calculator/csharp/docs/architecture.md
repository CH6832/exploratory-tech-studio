# System Architecture

The IFRS Calculator follows a modular architecture ensuring separation of concerns and maintainability.

---

## Overview

- **Modules**:
  - Fair Value Module
  - Revenue Allocation Module
  - Lease Liability Module
  - Defined Benefit Obligation Module
  - Deferred Tax Module
  - Goodwill Module
  - Recoverable Amount Module
- **Command-Line Interface (CLI)**:
  - Handles user inputs and dispatches commands to respective modules.

---

## Architecture Diagram

```

    +------------------+
    |  User Interface  |
    +--------+---------+
            |
            v
    +--------+---------+
    |   CLI Dispatcher  |
    +--------+---------+
            |
            v
    +--------+---------+
    | Calculation Core |
    |  (Modules)       |
    +--------+---------+
            |
            v
    +--------+---------+
    |  Utility Services |
    +-------------------+

```

---

## Design Principles

1. **Modular Design**: Separate each functionality into individual modules.
2. **Single Responsibility**: Each module focuses on one specific calculation.
3. **Dependency Injection**: Promotes testability and decouples modules.
4. **Extensibility**: Adding new IFRS standards is simple and requires minimal code changes.
