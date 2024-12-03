package com.efilingusgaap.financialuserservice;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for the Financial User Service.
 * Contains unit tests for the UserService layer.
 */
class FinancialUserServiceApplicationTests {

	@InjectMocks
	private UserService userService; // Injecting the UserService for testing.

	@Mock
	private UserRepository userRepository; // Mocking the UserRepository to simulate data operations.

	/**
	 * Initializes mocks for the test class.
	 */
	public FinancialUserServiceApplicationTests() {
		MockitoAnnotations.openMocks(this); // Initializes the mocks for use.
	}

	/**
	 * Test to verify that all users are retrieved correctly.
	 */
	@Test
	void testGetAllUsers() {
		// Mocking the behavior of the repository.
		when(userRepository.findAllUsers()).thenReturn(List.of(new HashMap<>() {{
			put("name", "Jack Example");
			put("email", "jack.example@example.com");
		}}));

		// Calling the service method to retrieve users.
		List<Map<String, Object>> users = userService.getAllUsers();

		// Assertions to verify the behavior.
		assertFalse(users.isEmpty()); // Ensures the user list is not empty.
		assertEquals("Jack Example", users.get(0).get("name")); // Verifies the name of the first user.
	}

	/**
	 * Test to verify the creation of a new user.
	 */
	@Test
	void testCreateUser() {
		// Mock user data for testing.
		Map<String, Object> user = new HashMap<>();
		user.put("name", "Jack Example");
		user.put("email", "jack.example@example.com");

		// Call the service method to create a user.
		userService.createUser(user);

		// Verify that the repository's saveUser method was called exactly once.
		verify(userRepository, times(1)).saveUser(user);
	}
}
