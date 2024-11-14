# **Test Plan: Online Banking System Simulation**

## **1. Overview**
This test plan outlines the testing strategy, scope, and test cases for the **Online Banking System Simulation**. The goal of testing is to ensure that the system operates correctly, performs as expected, and meets the specified requirements. The tests will be divided into **unit tests**, **integration tests**, and **functional tests**, covering all core functionalities such as **User Management**, **Account Management**, and **Transaction Management**.

---

## **2. Test Objectives**
The primary objectives of this test plan are:
- To verify that the system meets functional and non-functional requirements.
- To ensure the correct implementation of core features, including user registration, login, account creation, transactions, and the use of simulated databases (JSON files).
- To identify and fix bugs and errors in the system.
- To verify the system's robustness under normal and edge-case scenarios.

---

## **3. Scope of Testing**

The testing will cover the following components:
1. **User Management**:
    - User registration.
    - User authentication.
    - Password handling.

2. **Account Management**:
    - Account creation and management.
    - Account balance updates (deposit/withdrawal).

3. **Transaction Management**:
    - Performing deposits and withdrawals.
    - Transaction logging and storage.

4. **Database Simulation (JSON files)**:
    - Data persistence in the simulated database.
    - Correct reading and writing from JSON files.

---

## **4. Testing Approach**

### **4.1 Test Types**

#### **Unit Testing**
Unit tests will be written for individual methods and functions, ensuring that each component behaves correctly in isolation.

#### **Integration Testing**
Integration tests will verify that the system's components (services, repositories, and utilities) interact correctly. These tests will focus on the integration of services with the simulated databases (JSON files).

#### **Functional Testing**
Functional tests will simulate real-world scenarios, such as user registration, account creation, transactions, and verifying expected outcomes (balance updates, transaction logs).

#### **Boundary Testing**
Boundary tests will ensure the system handles edge cases such as:
- Validating inputs (e.g., minimum and maximum account balance).
- Handling of invalid data (e.g., empty usernames, invalid email addresses).

---

## **5. Testing Tools**
- **JUnit**: For unit and integration testing.
- **Mockito**: For mocking external dependencies during unit testing.
- **JSONassert**: For validating JSON responses and data structures.
- **Log4j**: For logging system activities, useful for debugging tests.

---

## **6. Test Cases**

### **6.1 User Management Test Cases**

#### **Test Case 1: User Registration - Success**
**Objective**: Test that a user can register successfully.
- **Input**:
    - Username: `john_doe`
    - Email: `john.doe@example.com`
    - Password: `Password123`
- **Expected Output**:
    - User is saved in `users.json`.
    - Password is hashed.
    - Registration is successful.

#### **Test Case 2: User Registration - Failure (Duplicate Username)**
**Objective**: Test that a user cannot register with an existing username.
- **Input**:
    - Username: `john_doe` (already registered)
    - Email: `new.user@example.com`
    - Password: `NewPassword123`
- **Expected Output**:
    - Error message: "Username already taken."

#### **Test Case 3: User Authentication - Success**
**Objective**: Test that the user can log in with correct credentials.
- **Input**:
    - Username: `john_doe`
    - Password: `Password123`
- **Expected Output**:
    - User authentication successful.
    - Return user details and authentication token.

#### **Test Case 4: User Authentication - Failure (Incorrect Password)**
**Objective**: Test that the user cannot log in with an incorrect password.
- **Input**:
    - Username: `john_doe`
    - Password: `WrongPassword`
- **Expected Output**:
    - Error message: "Invalid credentials."

---

### **6.2 Account Management Test Cases**

#### **Test Case 5: Account Creation - Success**
**Objective**: Test that a new account is created successfully.
- **Input**:
    - User ID: `1`
    - Account Type: `checking`
    - Initial Balance: `1000.00`
- **Expected Output**:
    - Account is saved in `accounts.json`.
    - Account number is generated.
    - Account is created successfully.

#### **Test Case 6: Deposit - Success**
**Objective**: Test that a deposit transaction updates the account balance.
- **Input**:
    - Account ID: `101`
    - Deposit Amount: `500.00`
- **Expected Output**:
    - Balance updated to `1500.00`.
    - New transaction is saved in `transactions.json`.

#### **Test Case 7: Withdraw - Success**
**Objective**: Test that a withdrawal transaction updates the account balance.
- **Input**:
    - Account ID: `101`
    - Withdrawal Amount: `200.00`
- **Expected Output**:
    - Balance updated to `1300.00`.
    - New transaction is saved in `transactions.json`.

#### **Test Case 8: Withdraw - Insufficient Funds**
**Objective**: Test that a withdrawal is not allowed if there are insufficient funds.
- **Input**:
    - Account ID: `101`
    - Withdrawal Amount: `2000.00`
- **Expected Output**:
    - Error message: "Insufficient funds."
    - No transaction is recorded.

---

### **6.3 Transaction Management Test Cases**

#### **Test Case 9: Transaction Logging - Success**
**Objective**: Test that a transaction is logged correctly in the `transactions.json`.
- **Input**:
    - Account ID: `101`
    - Transaction Type: `deposit`
    - Amount: `100.00`
    - Description: `Deposit from external bank`
- **Expected Output**:
    - Transaction is saved in `transactions.json` with all fields correctly populated.

#### **Test Case 10: Transaction Logging - Failure (Invalid Transaction Type)**
**Objective**: Test that an invalid transaction type (e.g., "transfer") is rejected.
- **Input**:
    - Account ID: `101`
    - Transaction Type: `transfer`
    - Amount: `50.00`
- **Expected Output**:
    - Error message: "Invalid transaction type."

---

### **6.4 Boundary and Edge Case Test Cases**

#### **Test Case 11: Maximum Balance Limit**
**Objective**: Test that the system enforces any balance limits (e.g., maximum account balance).
- **Input**:
    - Account ID: `101`
    - Deposit Amount: `1000000.00`
- **Expected Output**:
    - Error message: "Maximum balance limit exceeded."

#### **Test Case 12: Empty Input Validation**
**Objective**: Test that the system handles empty inputs (e.g., empty username or password).
- **Input**:
    - Username: `""`
    - Password: `""`
- **Expected Output**:
    - Error message: "Username and password cannot be empty."

---

## **7. Test Execution Strategy**

The tests will be executed as follows:
1. **Unit Tests**: These tests will be executed first to verify individual components (e.g., UserService, AccountService) and their methods.
2. **Integration Tests**: These tests will be run to verify that the system components integrate correctly (e.g., UserService with UserRepository, TransactionService with AccountRepository).
3. **Functional Tests**: After unit and integration tests pass, functional tests simulating real-world scenarios will be executed.
4. **Boundary and Edge Case Tests**: These tests will be executed last to ensure that the system handles edge cases properly.

---

## **8. Test Environment**
The tests will be executed in the following environment:
- **IDE**: IntelliJ IDEA Ultimate
- **JDK Version**: JDK 21 (latest stable version)
- **Maven Version**: 3.8.1
- **Operating System**: Windows/Linux/macOS (depending on developer environment)
- **Testing Framework**: JUnit 5, Mockito, JSONassert
- **Database Simulation**: JSON files (users.json, accounts.json, transactions.json)

---

## **9. Risks and Mitigation**
### **Risks**:
- **Inconsistent data in JSON files**: Manual editing of the JSON files could lead to inconsistent data.
    - **Mitigation**: Use automated tests to validate the integrity of JSON data.

- **Scalability**: As the number of users and transactions increases, the JSON file-based database may not scale effectively.
    - **Mitigation**: Future transition to a real database (e.g., MySQL or PostgreSQL) once the system is ready for production.
