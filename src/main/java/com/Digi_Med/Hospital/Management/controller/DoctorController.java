package com.Digi_Med.Hospital.Management.controller;

import com.Digi_Med.Hospital.Management.dtos.DoctorRegistrationRequest;
import com.Digi_Med.Hospital.Management.dtos.DoctorResponseDTO;
import com.Digi_Med.Hospital.Management.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/register")
    public ResponseEntity<DoctorResponseDTO> registerDoctor(@Valid @RequestBody DoctorRegistrationRequest request) {
        DoctorResponseDTO response = doctorService.registerDoctor(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DoctorResponseDTO> getDoctorByEmail(@PathVariable String email) {
        return ResponseEntity.ok(doctorService.getDoctorByEmail(email));
    }

    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<DoctorResponseDTO> getDoctorByLicenseNumber(@PathVariable String licenseNumber) {
        return ResponseEntity.ok(doctorService.getDoctorByLicenseNumber(licenseNumber));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctorsBySpecialization(@PathVariable String specialization) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialization(specialization));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctorsByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(doctorService.getDoctorsByDepartment(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Long id,
                                                          @Valid @RequestBody DoctorRegistrationRequest request) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<String> activateDoctor(@PathVariable Long id) {
        doctorService.activateDoctor(id);
        return ResponseEntity.ok("Doctor activated successfully");
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateDoctor(@PathVariable Long id) {
        doctorService.deactivateDoctor(id);
        return ResponseEntity.ok("Doctor deactivated successfully");
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalDoctorCount() {
        return ResponseEntity.ok(doctorService.getTotalDoctorCount());
    }
}
