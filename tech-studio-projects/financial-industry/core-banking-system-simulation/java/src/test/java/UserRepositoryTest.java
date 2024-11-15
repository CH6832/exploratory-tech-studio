import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserRepository, specifically targeting the readUsers() method.
 * This class tests that the user data can be successfully read from the data source
 * and verifies the integrity of the data retrieved.
 */
public class UserRepositoryTest {

    private UserRepository userRepository;

    /**
     * Sets up the testing environment before each test method is run.
     * Initializes a new instance of UserRepository to ensure that each test
     * starts with a fresh repository.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the UserRepository before each test
        userRepository = new UserRepository();
    }

    /**
     * Tests the functionality of the readUsers() method.
     * This test verifies that:
     * 1. The method does not return a null value.
     * 2. The list returned contains at least one user.
     * 3. The first user's details are as expected.
     * This method assumes that the user data file is populated with a user
     * whose details match the assertions in this test.
     */
    @Test
    public void testReadUsers() {
        // Call the readUsers() method to fetch the list of users from the repository
        List<User> users = userRepository.readUsers();

        // Verify that the list of users returned by readUsers() is not null
        // This ensures that the method has correctly handled the file reading process
        assertNotNull(users, "The list of users should not be null");

        // Verify that the list contains at least one user
        // This checks that the file reading returned actual user data, not an empty list
        assertFalse(users.isEmpty(), "The list of users should contain at least one user");

        // Verify the details of the first user in the list
        // Here we assume the user data file contains a user with expected details
        User firstUser = users.get(0);  // Access the first user in the list

        // Check that the first user's ID matches the expected ID "1"
        assertEquals("1", firstUser.getUserId(), "The first user's ID should be '1'");

        // Verify that the full name of the first user is "John Doe"
        assertEquals("John Doe", firstUser.getFullName(), "The first user's name should be 'John Doe'");

        // Verify that the email address of the first user is "john.doe@example.com"
        assertEquals("john@example.com", firstUser.getEmailAddress(), "The first user's email should be 'john@example.com'");
    }
}
