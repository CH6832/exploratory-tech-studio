# Directory structure

```

    online-banking-system/
    │
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   ├── com/
    │   │   │   │   ├── bank/
    │   │   │   │   │   ├── model/             # Data models (User, Account, Transaction)
    │   │   │   │   │   ├── service/           # Business logic (UserService, AccountService)
    │   │   │   │   │   ├── repository/        # JSON file interaction (UserRepository, AccountRepository)
    │   │   │   │   │   ├── controller/        # Controllers for user actions
    │   │   │   │   │   └── util/              # Utility classes (JSON handling, logging)
    │   │   │   │   └── Application.java       # Main entry point
    │   │   └── resources/
    │   │       ├── users.json
    │   │       ├── accounts.json
    │   │       └── transactions.json
    │
    ├── src/test/java/                             # Unit tests
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
    │   │   │   └── ApplicationTest.java         # Basic integration tests
    │
    ├── pom.xml                                     # Maven configuration file
    ├── requirements.md                            # This file (project requirements)
    └── README.md                                   # Project description and setup instructions

````