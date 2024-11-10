import com.system.banking.repository.UserRepository;
import com.system.banking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceLoginTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Initialize the UserService before each test
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void testLoginUserSuccessful() {
        String email = "john.doe@example.com";
        String password = "password123";  // Correct password for user "John Doe"

        // Attempt login with correct credentials
        boolean loginResult = userService.loginUser(email, password);

        // Verify that the login was successful
        assertTrue(loginResult, "Login should be successful with correct credentials");
    }

    @Test
    public void testLoginUserFailure() {
        String email = "john.doe@example.com";
        String password = "wrongpassword";  // Incorrect password

        // Attempt login with incorrect credentials
        boolean loginResult = userService.loginUser(email, password);

        // Verify that the login was unsuccessful
        assertFalse(loginResult, "Login should fail with incorrect credentials");
    }

    @Test
    public void testLoginUserNotFound() {
        String email = "nonexistent.user@example.com";
        String password = "password123";  // No such user

        // Attempt login with a non-existent user
        boolean loginResult = userService.loginUser(email, password);

        // Verify that the login was unsuccessful
        assertFalse(loginResult, "Login should fail for a non-existent user");
    }
}
