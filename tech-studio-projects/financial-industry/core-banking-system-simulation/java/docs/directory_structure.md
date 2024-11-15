# Directory structure

```

    online-banking-system/
    │
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   ├── com/
    │   │   │   │   ├── bank/
    │   │   │   │   │   ├── model/                # Data models (User, Account, Transaction)
    │   │   │   │   │   │   ├── User.java
    │   │   │   │   │   │   ├── Account.java
    │   │   │   │   │   │   ├── Transaction.java
    │   │   │   │   │   ├── service/              # Business logic (UserService, AccountService)
    │   │   │   │   │   │   ├── UserService.java
    │   │   │   │   │   │   ├── AccountService.java
    │   │   │   │   │   ├── repository/           # JSON file interaction (UserRepository, AccountRepository)
    │   │   │   │   │   │   ├── UserRepository.java
    │   │   │   │   │   │   ├── AccountRepository.java
    │   │   │   │   │   ├── controller/           # Controllers for user actions
    │   │   │   │   │   │   ├── UserController.java
    │   │   │   │   │   │   ├── AccountController.java
    │   │   │   │   │   ├── util/                 # Utility classes (JSON handling, logging, exceptions)
    │   │   │   │   │   │   ├── JsonUtils.java
    │   │   │   │   │   │   ├── Logging.java
    │   │   │   │   │   │   ├── ExceptionUtils.java
    │   │   │   │   │   └── Application.java      # Main entry point
    │   │   └── resources/
    │   │       ├── users.json
    │   │       ├── accounts.json
    │   │       └── transactions.json
    │
    ├── src/test/java/ # Unit tests
    │   ├── com/
    │   │   ├── bank/
    │   │   │   ├── service/
    │   │   │   │   ├── UserServiceTest.java
    │   │   │   │   ├── AccountServiceTest.java
    │   │   │   ├── repository/
    │   │   │   │   ├── UserRepositoryTest.java
    │   │   │   │   ├── AccountRepositoryTest.java
    │   │   │   ├── util/
    │   │   │   │   ├── JsonUtilsTest.java
    │   │   │   └── ApplicationTest.java          # Basic integration tests
    │
    ├── pom.xml                                    # Maven configuration file
    ├── requirements.md                           # This file (project requirements)
    └── README.md                                  # Project description and setup instructions

````