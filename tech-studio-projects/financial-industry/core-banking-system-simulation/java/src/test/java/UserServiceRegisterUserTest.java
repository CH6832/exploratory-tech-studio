import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceRegisterUserTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Initialize the UserService before each test
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void testRegisterUser() throws IOException {
        // Register a new user
        String name = "Alice Johnson";
        String email = "alice.johnson@example.com";
        String password = "password123";

        // Register the user
        userService.registerUser(name, email, password);

        // Fetch all users to verify the new user was added
        List<User> users = userService.fetchAllUsers();
        assertTrue(users.stream().anyMatch(u -> u.getEmailAddress().equals(email)), "User with email " + email + " should be registered.");
    }
}
