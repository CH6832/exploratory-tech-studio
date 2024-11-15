import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserService methods related to fetching user data.
 * Contains tests to verify the successful retrieval of all users and the
 * validation of specific user details.
 */
public class UserServiceTest {

    private UserService userService;

    /**
     * Sets up a new UserService instance before each test method.
     * Initializes a UserRepository and associates it with UserService,
     * providing a fresh test environment for each test case.
     */
    @BeforeEach
    public void setUp() {
        // Create a new UserRepository instance and initialize UserService with it
        UserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    /**
     * Tests the fetchAllUsers method to ensure it returns a non-null, non-empty list.
     * Verifies that a call to fetch all users yields a valid list of users.
     */
    @Test
    public void testFetchAllUsers() {
        // Fetch the complete list of users using UserService
        List<User> users = userService.fetchAllUsers();

        // Assert that the list of users is not null
        assertNotNull(users, "The list of users should not be null");

        // Assert that the list contains at least one user
        assertFalse(users.isEmpty(), "The list of users should contain at least one user");
    }

    /**
     * Tests user details by retrieving the first user and validating their attributes.
     * Ensures that the user's ID, name, and email match expected values.
     */
    @Test
    public void testUserDetails() {
        // Fetch all users to validate specific user details
        List<User> users = userService.fetchAllUsers();

        // Check that at least one user exists in the list
        assertFalse(users.isEmpty(), "There should be at least one user");

        // Retrieve the first user in the list
        User user = users.get(0);

        // Ensure the user object is not null
        assertNotNull(user, "The user should not be null");

        // Verify the first user's ID matches expected value
        assertEquals("1", user.getUserId(), "The user's ID should be '1'");

        // Verify the first user's full name matches expected value
        assertEquals("John Doe", user.getFullName(), "The user's name should be 'John Doe'");

        // Verify the first user's email address matches expected value
        assertEquals("john@example.com", user.getEmailAddress(), "The user's email should be 'john@example.com'");
    }
}
