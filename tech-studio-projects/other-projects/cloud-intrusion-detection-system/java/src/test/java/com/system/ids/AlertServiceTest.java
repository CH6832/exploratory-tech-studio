package com.system.ids;

import com.system.ids.Alert;
import com.system.ids.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AlertServiceTest {
    private AlertService alertService;

    @BeforeEach
    public void setUp() {
        alertService = new AlertService();
    }

    @Test
    public void testAddAndRetrieveAlert() {
        Alert alert = new Alert("1", "Test alert", LocalDateTime.now());
        alertService.addAlert(alert);

        assertEquals(1, alertService.getAlerts().size());
        assertEquals("Test alert", alertService.getAlerts().get(0).getMessage());
    }

    @Test
    public void testGetAlertsEmpty() {
        assertTrue(alertService.getAlerts().isEmpty());
    }
}
