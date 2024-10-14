package com.contractmanagement.system;

import com.contractmanagement.system.Notification;
import com.contractmanagement.system.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
        return new ResponseEntity<>("Notification sent successfully!", HttpStatus.CREATED);
    }
}
