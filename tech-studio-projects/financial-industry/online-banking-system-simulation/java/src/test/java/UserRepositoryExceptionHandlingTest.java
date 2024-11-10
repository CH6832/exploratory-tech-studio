import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryExceptionHandlingTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the UserRepository before each test
        userRepository = new UserRepository();
    }

    @Test
    public void testReadUsersEmptyFile() {
        // Simulate an empty user list (can mock the file reading to simulate an empty list)
        List<User> users = userRepository.readUsers();

        // Verify that the list is empty
        assertTrue(((java.util.List<?>) users).isEmpty(), "The list of users should be empty when no users are present");
    }

    @Test
    public void testReadUsersFileNotFound() {
        // Simulate a scenario where the file is not found or can't be read
        // In a real scenario, you would mock the file access or throw an exception
        assertThrows(RuntimeException.class, () -> {
            // Simulate a failure when reading users (can throw an exception like FileNotFoundException)
            userRepository.readUsers();
        }, "Expected an exception when the file is not found");
    }
}
