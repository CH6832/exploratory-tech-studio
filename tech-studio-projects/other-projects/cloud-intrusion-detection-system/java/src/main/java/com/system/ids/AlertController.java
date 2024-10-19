package com.system.ids;

import com.system.ids.Alert;
import com.system.ids.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing alerts.
 */
@RestController
@RequestMapping("/alerts")
public class AlertController {
    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    /**
     * Endpoint to get all alerts.
     *
     * @return list of alerts
     */
    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAlerts();
    }

    /**
     * Endpoint to create a new alert.
     *
     * @param alert the alert to create
     * @return created alert
     */
    @PostMapping
    public Alert createAlert(@RequestBody Alert alert) {
        alertService.addAlert(alert);
        return alert;
    }
}
