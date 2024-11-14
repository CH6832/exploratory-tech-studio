import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the UserRepository before each test
        userRepository = new UserRepository();
    }

    @Test
    public void testReadUsers() {
        // Call the method to fetch users
        List<User> users = userRepository.readUsers();

        // Verify that the list of users is not null
        assertNotNull(users, "The list of users should not be null");

        // Verify that the list contains at least one user
        assertFalse(users.isEmpty(), "The list of users should contain at least one user");

        // Verify the first user’s details
        User firstUser = users.getFirst();
        assertEquals("1", firstUser.getUserId(), "The first user's ID should be '1'");
        assertEquals("John Doe", firstUser.getFullName(), "The first user's name should be 'John Doe'");
        assertEquals("john.doe@example.com", firstUser.getEmailAddress(), "The first user's email should be 'john.doe@example.com'");
    }
}
