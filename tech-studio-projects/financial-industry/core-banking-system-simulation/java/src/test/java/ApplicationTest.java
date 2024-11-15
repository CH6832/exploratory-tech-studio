import com.system.banking.model.Account;
import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.repository.AccountRepository;
import com.system.banking.service.UserService;
import com.system.banking.service.AccountService;
import com.system.banking.utils.JsonUtils;
import com.system.banking.utils.Logging;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for application integration testing. Verifies User and Account service interactions.
 * Includes setup and teardown steps, as well as individual test cases for user and account operations.
 */
public class ApplicationTest {

    private UserService userService;
    private AccountService accountService;
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    /**
     * Sets up the testing environment before each test case by initializing the
     * necessary repositories and services, and optionally clearing out any existing data files.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the repositories and services for each test case
        userRepository = new UserRepository();
        accountRepository = new AccountRepository();
        userService = new UserService(userRepository);
        accountService = new AccountService(accountRepository);

        // Clean up data files for a fresh state before each test
        // cleanUpData();
    }

    /**
     * Deletes any existing user and account data files, ensuring a clean slate for each test case.
     * Called within the setup method to maintain consistency across tests.
     */
    private void cleanUpData() {
        File usersFile = new File(JsonUtils.USERS_FILE_PATH);
        File accountsFile = new File(JsonUtils.ACCOUNTS_FILE_PATH);

        if (usersFile.exists()) {
            usersFile.delete();
        }
        if (accountsFile.exists()) {
            accountsFile.delete();
        }
    }

    /**
     * Tests user registration functionality, verifies that a new user is added successfully
     * and that the retrieved user's details match the expected values.
     *
     * @throws Exception if an error occurs during user registration
     */
    @Test
    public void testUserRegistration() throws Exception {
        // Register a new user in the system
        userService.registerUser("John Doe", "john.doe@example.com", "password123");

        // Retrieve the registered user by email from the repository
        User registeredUser = userRepository.getUserByEmail("john.doe@example.com");

        // Verify that the user was registered and details are correct
        assertNotNull(registeredUser, "User should be registered successfully.");
        assertEquals("John Doe", registeredUser.getFullName(), "User's full name should match.");
        assertEquals("john.doe@example.com", registeredUser.getEmailAddress(), "User's email should match.");
    }

    /**
     * Tests account creation for a registered user, ensuring the account is created
     * with the correct type and initial balance.
     *
     * @throws Exception if an error occurs during account creation
     */
    @Test
    public void testAccountCreation() throws Exception {
        // Register a user before creating an account
        userService.registerUser("Jane Smith", "jane.smith@example.com", "password456");

        // Retrieve the registered user
        User registeredUser = userRepository.getUserByEmail("jane.smith@example.com");

        // Create a new Savings account for the user
        Account newAccount = accountService.createAccount(registeredUser.getUserId(), "Savings");

        // Verify that the account is created and initialized correctly
        assertNotNull(newAccount, "Account should be created.");
        assertEquals("Savings", newAccount.getAccountType(), "Account type should be 'Savings'.");
        assertEquals(0.00, newAccount.getBalance(), "Account balance should be initialized to 0.");
    }

    /**
     * Tests account balance updates after a deposit is made, ensuring the balance
     * reflects the deposited amount accurately.
     *
     * @throws Exception if an error occurs during account balance update
     */
    @Test
    public void testAccountBalanceAfterDeposit() throws Exception {
        // Register a user and create an account
        userService.registerUser("Bob Johnson", "bob.johnson@example.com", "password789");
        User registeredUser = userRepository.getUserByEmail("bob.johnson@example.com");
        Account account = accountService.createAccount(registeredUser.getUserId(), "Checking");

        // Deposit funds into the account
        account.setBalance(100.00);
        accountService.updateAccount(account);  // Save the updated account balance

        // Retrieve the account and verify the balance after deposit
        Account updatedAccount = accountRepository.getAccountByNumber(account.getAccountNumber());
        assertEquals(100.00, updatedAccount.getBalance(), "Account balance should be 100.00 after deposit.");
    }

    /**
     * Tests account balance updates after a withdrawal is made, verifying the balance
     * reflects the reduced amount accurately.
     *
     * @throws Exception if an error occurs during withdrawal or balance update
     */
    @Test
    public void testAccountBalanceAfterWithdrawal() throws Exception {
        // Register a user, create an account, and deposit funds
        userService.registerUser("Alice Green", "alice.green@example.com", "password101");
        User registeredUser = userRepository.getUserByEmail("alice.green@example.com");
        Account account = accountService.createAccount(registeredUser.getUserId(), "Checking");
        account.setBalance(200.00);
        accountService.updateAccount(account);

        // Withdraw funds from the account
        account.setBalance(account.getBalance() - 50.00);
        accountService.updateAccount(account);

        // Retrieve and verify the account balance after withdrawal
        Account updatedAccount = accountRepository.getAccountByNumber(account.getAccountNumber());
        assertEquals(150.00, updatedAccount.getBalance(), "Account balance should be 150.00 after withdrawal.");
    }

    /**
     * Tests login functionality with invalid credentials to ensure the system
     * does not authenticate a non-existent or invalid user.
     */
    @Test
    public void testInvalidUserLogin() {
        // Attempt to log in with incorrect credentials
        boolean loginResult = userService.loginUser("nonexistent@example.com", "wrongpassword");

        // Assert that login fails
        assertFalse(loginResult, "Login should fail for invalid credentials.");
    }

    /**
     * Tests login functionality with valid credentials to ensure that an existing
     * user with correct credentials is authenticated successfully.
     *
     * @throws Exception if an error occurs during user registration or login
     */
    @Test
    public void testValidUserLogin() throws Exception {
        // Register a user to test login
        userService.registerUser("Sam Lee", "sam.lee@example.com", "securepassword");

        // Attempt to log in with correct credentials
        boolean loginResult = userService.loginUser("sam.lee@example.com", "securepassword");

        // Assert that login succeeds
        assertTrue(loginResult, "Login should succeed for valid credentials.");
    }
}
