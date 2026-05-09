package com.Digi_Med.Hospital.Management.serviceImpl;

import com.Digi_Med.Hospital.Management.dtos.DoctorRegistrationRequest;
import com.Digi_Med.Hospital.Management.dtos.DoctorResponseDTO;
import com.Digi_Med.Hospital.Management.models.Doctor;
import com.Digi_Med.Hospital.Management.models.Role;
import com.Digi_Med.Hospital.Management.models.User;
import com.Digi_Med.Hospital.Management.repository.DoctorRepository;
import com.Digi_Med.Hospital.Management.repository.UserRepository;
import com.Digi_Med.Hospital.Management.services.DoctorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public DoctorResponseDTO registerDoctor(DoctorRegistrationRequest request) {
        // Check if email exists
        if (userRepository.existsByEmail(request.getLoginEmail())) {
            throw new RuntimeException("Email already registered: " + request.getLoginEmail());
        }

        // Check if license number exists
        if (doctorRepository.findByLicenseNumber(request.getLicenseNumber()).isPresent()) {
            throw new RuntimeException("License number already registered: " + request.getLicenseNumber());
        }

        // Create User account
        User user = new User();
        user.setEmail(request.getLoginEmail());
        user.setPassword(passwordEncoder.encode(request.getLoginPassword()));
        user.setFullName("Dr. " + request.getFirstName() + " " + request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.DOCTOR);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        // Create Doctor profile
        Doctor doctor = new Doctor();
        doctor.setUser(savedUser);
        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setExperience(request.getExperience());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setPhoneNumber(request.getPhoneNumber());
        doctor.setEmail(request.getEmail());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setAvailability(request.getAvailability());
        doctor.setDepartment(request.getDepartment());
        doctor.setChamberAddress(request.getChamberAddress());
        doctor.setStatus("ACTIVE");

        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToResponseDTO(savedDoctor);
    }

    @Override
    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        return convertToResponseDTO(doctor);
    }

    @Override
    public DoctorResponseDTO getDoctorByEmail(String email) {
        Doctor doctor = doctorRepository.findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found with email: " + email));
        return convertToResponseDTO(doctor);
    }

    @Override
    public DoctorResponseDTO getDoctorByLicenseNumber(String licenseNumber) {
        Doctor doctor = doctorRepository.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new RuntimeException("Doctor not found with license: " + licenseNumber));
        return convertToResponseDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponseDTO> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponseDTO> getDoctorsByDepartment(String department) {
        return doctorRepository.findByDepartment(department).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DoctorResponseDTO updateDoctor(Long id, DoctorRegistrationRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setExperience(request.getExperience());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setPhoneNumber(request.getPhoneNumber());
        doctor.setEmail(request.getEmail());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setAvailability(request.getAvailability());
        doctor.setDepartment(request.getDepartment());
        doctor.setChamberAddress(request.getChamberAddress());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return convertToResponseDTO(updatedDoctor);
    }

    @Override
    @Transactional
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }

    @Override
    @Transactional
    public void activateDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        doctor.setStatus("ACTIVE");
        doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public void deactivateDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        doctor.setStatus("INACTIVE");
        doctorRepository.save(doctor);
    }

    @Override
    public long getTotalDoctorCount() {
        return doctorRepository.count();
    }

    private DoctorResponseDTO convertToResponseDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setFullName(doctor.getFullName());
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setQualification(doctor.getQualification());
        dto.setExperience(doctor.getExperience());
        dto.setLicenseNumber(doctor.getLicenseNumber());
        dto.setPhoneNumber(doctor.getPhoneNumber());
        dto.setEmail(doctor.getEmail());
        dto.setConsultationFee(doctor.getConsultationFee());
        dto.setAvailability(doctor.getAvailability());
        dto.setStatus(doctor.getStatus());
        dto.setDepartment(doctor.getDepartment());
        dto.setChamberAddress(doctor.getChamberAddress());
        dto.setCreatedAt(doctor.getCreatedAt());
        return dto;
    }
}
