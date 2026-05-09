package com.Digi_Med.Hospital.Management.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DoctorResponseDTO {
    private Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String specialization;
    private String qualification;
    private String experience;
    private String licenseNumber;
    private String phoneNumber;
    private String email;
    private String consultationFee;
    private String availability;
    private String status;
    private String department;
    private String chamberAddress;
    private LocalDateTime createdAt;
}
