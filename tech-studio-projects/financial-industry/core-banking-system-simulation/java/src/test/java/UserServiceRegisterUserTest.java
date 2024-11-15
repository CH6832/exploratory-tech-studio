import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the UserService's user registration functionality.
 * Contains tests that validate the successful registration of a new user
 * and verification of the user's presence in the repository after registration.
 */
public class UserServiceRegisterUserTest {

    private UserService userService;

    /**
     * Sets up the UserService instance before each test method is run.
     * Initializes a UserRepository and assigns it to the UserService, ensuring each
     * test operates with a fresh service instance.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the UserService with a new UserRepository instance
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    /**
     * Tests the registration of a new user.
     * This test verifies that after calling the registerUser method with valid
     * details, the new user is added to the user repository.
     *
     * @throws IOException if an I/O error occurs during registration.
     */
    @Test
    public void testRegisterUser() throws IOException {
        // Define new user details for registration
        String name = "Alice Johnson";
        String email = "alice.johnson@example.com";
        String password = "password123";

        // Register the new user with specified name, email, and password
        userService.registerUser(name, email, password);

        // Fetch all registered users from the repository to verify registration
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.readUsers();

        // Check that the new user is included in the list of registered users
        assertTrue(
                users.stream().anyMatch(u -> u.getEmailAddress().equals(email)),
                "User with email " + email + " should be registered."
        );
    }
}
