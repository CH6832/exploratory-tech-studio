package com.contractmanagement.system;

import com.contractmanagement.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring Data repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Custom query method to find a user by username
}
