import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.utils.ExceptionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for handling exceptions in the UserRepository.
 * Verifies behavior when user data files are missing or contain no data.
 */
public class UserRepositoryExceptionHandlingTest {

    private UserRepository userRepository;

    /**
     * Sets up the UserRepository instance before each test case to ensure
     * a fresh repository for each individual test.
     */
    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository("src/main/users.json");
    }

    /**
     * Tests the behavior of reading users when the data file is empty.
     * Simulates an empty user list and verifies that the repository returns
     * an empty list, as expected.
     */
    @Test
    public void testReadUsersEmptyFile() {
        List<User> users = userRepository.readUsers();
        assertFalse(users.isEmpty(), "The list of users should be empty when no users are present.");
    }

    /**
     * Tests the behavior when the user data file is not found.
     * Verifies that FileMissingException is thrown when attempting to read
     * from a non-existent file.
     */
    @Test
    public void testReadUsersFileNotFound() {
        assertThrows(ExceptionUtils.FileMissingException.class, () -> {
            userRepository.readUsers();
        }, "Expected FileMissingException when the user data file is not found.");
    }
}
