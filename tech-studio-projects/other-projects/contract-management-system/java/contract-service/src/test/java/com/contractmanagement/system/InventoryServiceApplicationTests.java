package com.contractmanagement.system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryServiceApplicationTests {

    @Test
    void contextLoads() {
        // This test checks if the application context loads successfully.
    }

    @Test
    void testApplicationStarts() {
        // Additional test to ensure the application starts without any issues
        InventoryServiceApplication.main(new String[] {});
    }

}
