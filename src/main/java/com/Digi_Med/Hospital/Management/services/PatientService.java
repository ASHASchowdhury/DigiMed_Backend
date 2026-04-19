package com.Digi_Med.Hospital.Management.services;

import com.Digi_Med.Hospital.Management.dtos.PatientRegistrationRequest;
import com.Digi_Med.Hospital.Management.dtos.PatientResponseDTO;
import java.util.List;

public interface PatientService {
    PatientResponseDTO registerPatient(PatientRegistrationRequest request);
    PatientResponseDTO getPatientById(Long id);
    PatientResponseDTO getPatientByRegistrationNumber(String registrationNumber);
    PatientResponseDTO getPatientByPhoneNumber(String phoneNumber);
    List<PatientResponseDTO> getAllPatients();
    List<PatientResponseDTO> searchPatientsByName(String name);
    List<PatientResponseDTO> getPatientsByCity(String city);
    PatientResponseDTO updatePatient(Long id, PatientRegistrationRequest request);
    void deletePatient(Long id);
    void activatePatient(Long id);
    void deactivatePatient(Long id);
    long getTotalPatientCount();
}