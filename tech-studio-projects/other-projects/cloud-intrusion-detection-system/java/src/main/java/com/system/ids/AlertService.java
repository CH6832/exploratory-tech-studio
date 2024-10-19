package com.system.ids;

import system.ids.model.Alert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for managing alerts.
 */
@Service
public class AlertService {
    private List<Alert> alerts = new ArrayList<>();

    /**
     * Adds a new alert to the system.
     *
     * @param alert the alert to add
     */
    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    /**
     * Gets all alerts in the system.
     *
     * @return list of alerts
     */
    public List<Alert> getAlerts() {
        return new ArrayList<>(alerts); // Return a copy of alerts
    }
}
