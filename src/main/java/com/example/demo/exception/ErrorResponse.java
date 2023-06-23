package com.example.demo.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    // Constructors, getters, and setters

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    // Getters and setters
}
