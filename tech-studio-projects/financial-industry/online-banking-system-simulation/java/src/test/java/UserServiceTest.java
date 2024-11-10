import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Initialize the UserService before each test
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void testFetchAllUsers() {
        // Call the method to fetch all users
        List<User> users = userService.fetchAllUsers();

        // Verify that the list of users is not null
        assertNotNull(users, "The list of users should not be null");

        // Verify that the list contains at least one user
        assertFalse(users.isEmpty(), "The list of users should contain at least one user");
    }

    @Test
    public void testUserDetails() {
        // Fetch all users
        List<User> users = userService.fetchAllUsers();

        // Check that at least one user exists
        assertFalse(users.isEmpty(), "There should be at least one user");

        // Get the first user and verify their details
        User user = users.getFirst();
        assertNotNull(user, "The user should not be null");
        assertEquals("1", user.getUserId(), "The user's ID should be '1'");
        assertEquals("John Doe", user.getFullName(), "The user's name should be 'John Doe'");
        assertEquals("john.doe@example.com", user.getEmailAddress(), "The user's email should be 'john.doe@example.com'");
    }
}
