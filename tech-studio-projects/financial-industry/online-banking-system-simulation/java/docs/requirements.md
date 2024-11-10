# **Online Banking System Simulation Requirements**

## **Project Overview**
This project is a simulation of an online banking system designed to handle user management, account management, transaction processing, and security features using Java, with data persistence simulated through JSON files. It is built as a monolithic application using Maven as the build tool and IntelliJ IDEA Ultimate as the IDE.

## **Core Technologies**
- **Java** (latest stable version, e.g., Java 17 or Java 21)
- **Maven** (latest version for project management and dependency management)
- **Jackson** or **Gson** (latest stable versions) for JSON processing (serialization/deserialization)
- **Junit 5** (latest version) for unit and integration testing
- **Log4j** (latest version) for logging
- **bcrypt** (latest version) for password hashing
- **SLF4J** (latest version) for logging abstraction

---

## **Features to Implement**

### **User Management**
- **User Registration**:
    - Allow users to register by providing basic information (e.g., name, email, username, password).
    - Store user information securely, using password hashing (bcrypt).

- **User Login**:
    - Users should log in using their username/email and password.
    - Validate credentials (hashed password check) and authenticate users.

- **Password Hashing**:
    - Use **bcrypt** to hash and securely store passwords.

- **Password Reset**:
    - Users should be able to reset their password via a link sent to their registered email.

- **Profile Management**:
    - Allow users to update their profile information (name, email, password).

- **Session Management**:
    - Implement session or token-based authentication (e.g., **JWT** or **session IDs**).
    - Ensure automatic logout after inactivity.

- **User Roles**:
    - Support different user roles (e.g., **Admin** and **Customer**).
    - Admins can manage user activation/deactivation and account management.

### **Account Management**
- **Account Creation**:
    - Allow users to create bank accounts (checking, savings, etc.).
    - Generate unique account numbers for each account.

- **Account Details**:
    - Provide functionality for users to view their account details (balance, account type).

- **Account Deactivation**:
    - Allow users to deactivate or close an account (after ensuring no pending transactions).

- **Account Types**:
    - Implement support for multiple account types (e.g., **checking**, **savings**, **business**).
    - Support for different account features like interest rates for savings.

### **Transaction Management**
- **Deposit**:
    - Users can deposit money into their accounts.

- **Withdrawal**:
    - Users can withdraw money from their accounts, with balance checks.

- **Transfer**:
    - Users can transfer money between their accounts or to other users’ accounts.

- **Transaction History**:
    - Keep a history of all transactions for each account (date, amount, type, status).

- **Transaction Fees**:
    - Implement transaction fees, especially for international transfers or account types with limits.

- **Transaction Status**:
    - Track the status of each transaction (e.g., completed, pending, failed).

### **Security & Compliance**
- **Two-Factor Authentication (2FA)**:
    - Allow users to enable 2FA for added security (via SMS or email verification).

- **Encryption**:
    - Encrypt sensitive data (e.g., passwords, account details) using modern encryption techniques (e.g., AES).

- **Audit Logs**:
    - Keep an audit log of critical actions (e.g., login attempts, account actions, transactions).

- **Anti-Fraud Mechanisms**:
    - Implement basic fraud detection (e.g., alert users of suspicious activity).

- **Session Timeout**:
    - Automatically log users out after a period of inactivity to ensure security.

### **Data Management (Simulated Database)**
- **JSON File Structure**:
    - Use **JSON** files to simulate database tables:
        - `users.json`: Stores user data (e.g., username, password hash, roles, active status).
        - `accounts.json`: Stores account information (e.g., account number, account type, balance).
        - `transactions.json`: Stores transaction data (e.g., amount, date, type, account ID).

- **JSON Data Handling**:
    - Use **Jackson** or **Gson** to serialize and deserialize Java objects to/from JSON.
    - Implement **CRUD** operations for managing users, accounts, and transactions.

- **Data Consistency**:
    - Ensure that the JSON files are updated correctly to reflect changes in the system (add, remove, update).
    - Implement basic **data backup** mechanisms.

### **Reporting and Notifications**
- **Email/SMS Notifications**:
    - Send notifications for key events (successful login, transaction alerts, account updates).
    - Implement a mock notification system (SMS or email).

- **Account Statements**:
    - Generate account statements that summarize transactions (e.g., monthly or yearly statements).

- **Balance Alerts**:
    - Allow users to set alerts for low balances or large transactions.

### **Admin Features**
- **User Management**:
    - Admins can view, activate, deactivate, or delete user accounts.

- **Account Monitoring**:
    - Admins can view detailed information about any user’s accounts and their transaction histories.

- **Transaction Monitoring**:
    - Admins can monitor pending or flagged transactions and approve/reject them.

### **Testing**
- **Unit Tests**:
    - Use **JUnit 5** for testing individual methods and components of the system (e.g., account balance update, transaction validation).

- **Integration Tests**:
    - Test interactions between components (e.g., successful deposit, account balance update, transaction record creation).

- **Mock Data**:
    - Use mock data for testing system functionalities (mock users, accounts, transactions).

- **Edge Cases**:
    - Ensure edge cases (e.g., insufficient funds, invalid account numbers) are tested.

### **Logging and Monitoring**
- **Logging**:
    - Implement logging with **Log4j** or **SLF4J** to log critical system events (e.g., user logins, transactions, account changes).

- **Error Handling**:
    - Implement clear error handling and logging for system failures.

---

## **Installation & Setup**
1. Clone the repository.
2. Open the project in **IntelliJ IDEA Ultimate**.
3. Import the Maven project.
4. Ensure Java 17 or Java 21 is set as the SDK in your project settings.
5. Install the necessary dependencies by running `mvn clean install` in the terminal.
6. Run the `Application.java` class to start the simulation.
