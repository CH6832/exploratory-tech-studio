# **Design Document: Online Banking System Simulation**

## **1. Overview**
This document outlines the design for an **Online Banking System Simulation** built using Java. It covers the system architecture, components, database structure, and detailed design of core functionalities. The system is intended to simulate the core banking operations like user management, account management, transaction processing, and security features.

---

## **2. System Architecture**

### **2.1 Architectural Style: Monolithic Architecture**

The system is designed as a **monolithic application** where all features are packaged together in one codebase. The main components of the system communicate directly with each other, and the backend processes such as authentication, transaction processing, and account management are tightly integrated.

### **2.2 High-Level Architecture**

The system follows a **Layered Architecture** pattern, with the following layers:
- **Presentation Layer** (For UI, even though it's not included yet)
- **Business Logic Layer** (For processing user actions and business operations)
- **Data Access Layer** (For reading and writing data to JSON files, simulating a database)

The architecture uses standard **Service-Repositories** to interact with data, and **Utility classes** for handling common tasks (like JSON serialization/deserialization and logging).

```
+--------------------------------------------------+
|                  Online Banking System           |
+--------------------------------------------------+
|  Presentation Layer (UI - future)                |
|  -> User Interface (currently not implemented)   |
+--------------------------------------------------+
|  Business Logic Layer (Services)                 |
|  -> UserService                                  |
|  -> AccountService                               |
|  -> TransactionService                           |
+--------------------------------------------------+
|  Data Access Layer                               |
|  -> UserRepository                               |
|  -> AccountRepository                            |
|  -> TransactionRepository                        |
+--------------------------------------------------+
|  Utility Layer                                   |
|  -> JsonUtils (Serialization/Deserialization)     |
|  -> Logging (Log4j)                              |
+--------------------------------------------------+
```

#### **Data Flow:**
1. **User Registration**:
    - User enters registration details (username, email, password) via UI.
    - The **UserService** processes the data, hashes the password, and saves the user to the **JSON file** via **UserRepository**.

2. **User Authentication**:
    - The system checks credentials by comparing the hashed password in the repository.
    - Upon success, a session token or JWT could be generated for user sessions (not implemented here yet).

3. **Transaction Flow**:
    - The user performs a transaction (deposit/withdrawal).
    - The **TransactionService** validates the request and updates the balance in the **AccountRepository**.
    - A new transaction is added to the **Transactions JSON file**.

---

## **3. Component Design**

### **3.1 User Management**
- **User**: Represents a bank user (customer or admin). Contains personal information like username, password (hashed), email, and roles (admin or user).
- **UserService**: Handles the business logic for user operations like registration, login, password management.
- **UserRepository**: Handles reading and writing the **User** data from/to the **users.json** file.

**Component Diagram for User Management**:
```
+----------------+       +-------------------+       +--------------------+
| User (Model)   |<----->| UserService (Logic)|<----->| UserRepository     |
+----------------+       +-------------------+       +--------------------+
        ↑                                                    ↑
        |                                                    |
        +---------------------------> JSON (users.json) <---+
```

### **3.2 Account Management**
- **Account**: Represents the bank account. Contains information such as account number, account type, balance, and currency.
- **AccountService**: Contains business logic for managing account creation, balance updates, and account details.
- **AccountRepository**: Reads and writes account data to **accounts.json** file.

**Component Diagram for Account Management**:
```
+-------------------+       +---------------------+       +--------------------+
| Account (Model)   |<----->| AccountService      |<----->| AccountRepository  |
+-------------------+       +---------------------+       +--------------------+
        ↑                                                     ↑
        |                                                     |
        +---------------------------> JSON (accounts.json) <--+
```

### **3.3 Transaction Management**
- **Transaction**: Represents the transaction entity with details such as type (deposit/withdrawal), amount, date, and status.
- **TransactionService**: Handles transaction processing (validation, update).
- **TransactionRepository**: Responsible for storing and retrieving transaction details from **transactions.json**.

**Component Diagram for Transaction Management**:
```
+--------------------+       +---------------------+       +-----------------------+
| Transaction (Model)|<----->| TransactionService  |<----->| TransactionRepository |
+--------------------+       +---------------------+       +-----------------------+
        ↑                                                      ↑
        |                                                      |
        +---------------------------> JSON (transactions.json) <---+
```

### **3.4 Utilities**
- **JsonUtils**: Utility class to handle JSON serialization and deserialization for all data models (User, Account, Transaction).
- **Logging**: Utility for logging important actions such as user logins, account changes, transactions, and errors (using Log4j).

**Component Diagram for Utilities**:
```
+---------------------+         +------------------------+
| JsonUtils (Utility) |<------->| Logging (Log4j)        |
+---------------------+         +------------------------+
```

---

## **4. Sequence Diagrams**

### **4.1 User Registration Sequence**
This diagram shows the flow when a user registers in the system:

```
+------------+         +---------------+           +------------------+        +------------------+
|   User     |         | UserService   |           | UserRepository   |        |   users.json     |
+------------+         +---------------+           +------------------+        +------------------+
      |                        |                          |                         |
      |     Submit Register     |                          |                         |
      +------------------------>|                          |                         |
      |                        |      Hash Password       |                         |
      |                        |------------------------->|                         |
      |                        |                          |                         |
      |                        |   Add User to Repository |                         |
      |                        |------------------------->|                         |
      |                        |                          |                         |
      |                        |    Save to JSON File     |------------------------>|
      |                        |<-------------------------|                         |
      |                        |                          |                         |
      |     Confirmation       |                          |                         |
      +<-----------------------+                          |                         |
```

### **4.2 Deposit Transaction Sequence**
This diagram illustrates the process when a user performs a deposit:

```
+------------+        +-------------------+       +------------------------+       +------------------+
|   User     |        | TransactionService|       | AccountRepository      |       | accounts.json    |
+------------+        +-------------------+       +------------------------+       +------------------+
      |                       |                             |                             |
      |       Request Deposit  |                             |                             |
      +----------------------->|                             |                             |
      |                       |  Validate Deposit Amount   |                             |
      |                       |--------------------------->|                             |
      |                       |                             |                             |
      |                       | Update Account Balance     |                             |
      |                       |--------------------------->|                             |
      |                       |                             |  Save Updated Balance      |
      |                       |                             |------------------------->|
      |                       |                             |                             |
      |    Confirm Transaction|                             |                             |
      +<----------------------|                             |                             |
```

---

## **5. Database Design**

Although we are simulating the database with **JSON files**, it's important to have a structured format for each entity (User, Account, and Transaction).

### **5.1 Users JSON (users.json)**
```json
[
  {
    "userId": "1",
    "username": "john_doe",
    "passwordHash": "$2a$12$...",
    "email": "john.doe@example.com",
    "roles": ["user"],
    "isActive": true
  },
  {
    "userId": "2",
    "username": "admin_user",
    "passwordHash": "$2a$12$...",
    "email": "admin@example.com",
    "roles": ["admin"],
    "isActive": true
  }
]
```

### **5.2 Accounts JSON (accounts.json)**
```json
[
  {
    "accountId": "101",
    "userId": "1",
    "accountNumber": "123-456-7890",
    "accountType": "checking",
    "balance": 1500.00,
    "currency": "USD"
  }
]
```

### **5.3 Transactions JSON (transactions.json)**
```json
[
  {
    "transactionId": "1001",
    "accountId": "101",
    "transactionType": "deposit",
    "amount": 500.00,
    "date": "2024-11-10T10:00:00Z",
    "status": "completed",
    "description": "Deposit from bank transfer"
  }
]
```
