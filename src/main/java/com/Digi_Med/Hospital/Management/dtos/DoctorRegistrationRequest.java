package com.Digi_Med.Hospital.Management.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DoctorRegistrationRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    private String qualification;

    private String experience;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    private String phoneNumber;

    @Email(message = "Invalid email format")
    private String email;

    private String consultationFee;

    private String availability;

    private String department;

    private String chamberAddress;

    @NotBlank(message = "Login email is required")
    @Email(message = "Invalid login email format")
    private String loginEmail;

    @NotBlank(message = "Password is required")
    private String loginPassword;
}
