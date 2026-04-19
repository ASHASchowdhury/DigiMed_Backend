package com.Digi_Med.Hospital.Management.serviceImpl;

import com.Digi_Med.Hospital.Management.dtos.PatientRegistrationRequest;
import com.Digi_Med.Hospital.Management.dtos.PatientResponseDTO;
import com.Digi_Med.Hospital.Management.models.Patient;
import com.Digi_Med.Hospital.Management.models.User;
import com.Digi_Med.Hospital.Management.models.Role;
import com.Digi_Med.Hospital.Management.repository.PatientRepository;
import com.Digi_Med.Hospital.Management.repository.UserRepository;
import com.Digi_Med.Hospital.Management.services.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public PatientResponseDTO registerPatient(PatientRegistrationRequest request) {
        // Check if email exists
        if (userRepository.existsByEmail(request.getLoginEmail())) {
            throw new RuntimeException("Email already registered: " + request.getLoginEmail());
        }

        // Check if phone exists
        if (patientRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered: " + request.getPhoneNumber());
        }

        // Create User
        User user = new User();
        user.setEmail(request.getLoginEmail());
        user.setPassword(request.getLoginPassword()); // Will encode later
        user.setFullName(request.getFirstName() + " " + request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.PATIENT);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        // Create Patient
        Patient patient = new Patient();
        patient.setUser(savedUser);
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setAlternatePhone(request.getAlternatePhone());
        patient.setEmail(request.getEmail());
        patient.setAddressLine1(request.getAddressLine1());
        patient.setAddressLine2(request.getAddressLine2());
        patient.setCity(request.getCity());
        patient.setState(request.getState());
        patient.setPincode(request.getPincode());
        patient.setCountry(request.getCountry());
        patient.setEmergencyContactName(request.getEmergencyContactName());
        patient.setEmergencyContactNumber(request.getEmergencyContactNumber());
        patient.setEmergencyContactRelation(request.getEmergencyContactRelation());
        patient.setMedicalHistory(request.getMedicalHistory());
        patient.setAllergies(request.getAllergies());
        patient.setCurrentMedications(request.getCurrentMedications());

        Patient savedPatient = patientRepository.save(patient);
        return convertToResponseDTO(savedPatient);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return convertToResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO getPatientByRegistrationNumber(String registrationNumber) {
        Patient patient = patientRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Patient not found with registration number: " + registrationNumber));
        return convertToResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO getPatientByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Patient not found with phone number: " + phoneNumber));
        return convertToResponseDTO(patient);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientResponseDTO> searchPatientsByName(String name) {
        return patientRepository.findByFirstNameContainingOrLastNameContaining(name, name).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientResponseDTO> getPatientsByCity(String city) {
        return patientRepository.findByCity(city).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PatientResponseDTO updatePatient(Long id, PatientRegistrationRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setAlternatePhone(request.getAlternatePhone());
        patient.setEmail(request.getEmail());
        patient.setAddressLine1(request.getAddressLine1());
        patient.setAddressLine2(request.getAddressLine2());
        patient.setCity(request.getCity());
        patient.setState(request.getState());
        patient.setPincode(request.getPincode());
        patient.setCountry(request.getCountry());
        patient.setEmergencyContactName(request.getEmergencyContactName());
        patient.setEmergencyContactNumber(request.getEmergencyContactNumber());
        patient.setEmergencyContactRelation(request.getEmergencyContactRelation());
        patient.setMedicalHistory(request.getMedicalHistory());
        patient.setAllergies(request.getAllergies());
        patient.setCurrentMedications(request.getCurrentMedications());

        Patient updatedPatient = patientRepository.save(patient);
        return convertToResponseDTO(updatedPatient);
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }

    @Override
    @Transactional
    public void activatePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        patient.setStatus("ACTIVE");
        patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void deactivatePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        patient.setStatus("INACTIVE");
        patientRepository.save(patient);
    }

    @Override
    public long getTotalPatientCount() {
        return patientRepository.count();
    }

    private PatientResponseDTO convertToResponseDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setRegistrationNumber(patient.getRegistrationNumber());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setFullName(patient.getFullName());
        dto.setDateOfBirth(patient.getDateOfBirth());

        // Calculate age
        if (patient.getDateOfBirth() != null) {
            int age = Period.between(patient.getDateOfBirth(), LocalDate.now()).getYears();
            dto.setAge(age);
        }

        dto.setGender(patient.getGender());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setEmail(patient.getEmail());
        dto.setCity(patient.getCity());
        dto.setStatus(patient.getStatus());
        dto.setRegistrationDate(patient.getRegistrationDate());

        return dto;
    }
}