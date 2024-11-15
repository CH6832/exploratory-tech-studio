import com.system.banking.repository.UserRepository;
import com.system.banking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserService's login functionality.
 * This class includes test cases for:
 * 1. Successful login with correct credentials.
 * 2. Failed login with incorrect credentials.
 * 3. Failed login when the user does not exist in the system.
 */
public class UserServiceLoginTest {

    private UserService userService;

    /**
     * Sets up the UserService instance before each test method is run.
     * Initializes a UserRepository and passes it to UserService, ensuring that
     * each test has an isolated, freshly initialized service.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the UserService with a new UserRepository instance
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    /**
     * Tests a successful login scenario.
     * This test verifies that when a user provides the correct email and password,
     * the login attempt succeeds. The test assumes there exists a user with
     * email "john@example.com" and password "F1a2B3c4D5e6" in the user repository.
     */
    @Test
    public void testLoginUserSuccessful() {
        // Define the correct email and password for the test user
        String email = "john@example.com";
        String password = "F1a2B3c4D5e6";  // Correct password for user "John Doe"

        // Attempt login with correct credentials
        boolean loginResult = userService.loginUser(email, password);

        // Assert that login succeeds with correct credentials
        assertTrue(loginResult, "Login should be successful with correct credentials");
    }

    /**
     * Tests a failed login scenario due to incorrect password.
     * This test verifies that when a user provides an incorrect password,
     * the login attempt fails. The test assumes a user with email "john@example.com"
     * exists but with a different password than the one provided in this test.
     */
    @Test
    public void testLoginUserFailure() {
        // Define the correct email but an incorrect password
        String email = "john@example.com";
        String password = "wrongpassword";  // Incorrect password

        // Attempt login with incorrect credentials
        boolean loginResult = userService.loginUser(email, password);

        // Assert that login fails with incorrect credentials
        assertFalse(loginResult, "Login should fail with incorrect credentials");
    }

    /**
     * Tests a failed login scenario when the user does not exist.
     * This test verifies that if a user attempts to log in with an email
     * that is not registered in the system, the login attempt fails.
     */
    @Test
    public void testLoginUserNotFound() {
        // Define an email that does not belong to any registered user
        String email = "nonexistent.user@example.com";
        String password = "password123";  // Password is irrelevant as user does not exist

        // Attempt login with a non-existent user
        boolean loginResult = userService.loginUser(email, password);

        // Assert that login fails for a non-existent user
        assertFalse(loginResult, "Login should fail for a non-existent user");
    }
}
