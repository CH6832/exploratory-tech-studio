package com.example.contractmanagement.apigateway;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public ResponseEntity<String> fallback() {
        return new ResponseEntity<>("Service is temporarily unavailable. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}