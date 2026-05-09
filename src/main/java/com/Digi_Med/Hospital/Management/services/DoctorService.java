package com.Digi_Med.Hospital.Management.services;

import com.Digi_Med.Hospital.Management.dtos.DoctorRegistrationRequest;
import com.Digi_Med.Hospital.Management.dtos.DoctorResponseDTO;
import java.util.List;

public interface DoctorService {
    DoctorResponseDTO registerDoctor(DoctorRegistrationRequest request);
    DoctorResponseDTO getDoctorById(Long id);
    DoctorResponseDTO getDoctorByEmail(String email);
    DoctorResponseDTO getDoctorByLicenseNumber(String licenseNumber);
    List<DoctorResponseDTO> getAllDoctors();
    List<DoctorResponseDTO> getDoctorsBySpecialization(String specialization);
    List<DoctorResponseDTO> getDoctorsByDepartment(String department);
    DoctorResponseDTO updateDoctor(Long id, DoctorRegistrationRequest request);
    void deleteDoctor(Long id);
    void activateDoctor(Long id);
    void deactivateDoctor(Long id);
    long getTotalDoctorCount();
}
