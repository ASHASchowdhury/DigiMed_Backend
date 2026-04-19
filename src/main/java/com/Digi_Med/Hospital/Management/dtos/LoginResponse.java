package com.Digi_Med.Hospital.Management.dtos;


public class LoginResponse {
    private String email;
    private String fullName;
    private String role;
    private String message;
    private boolean success;

    public LoginResponse(boolean success, String message, String email, String fullName, String role) {
        this.success = success;
        this.message = message;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}