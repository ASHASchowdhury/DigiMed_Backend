package com.Digi_Med.Hospital.Management.controller;


import com.Digi_Med.Hospital.Management.dtos.PatientRegistrationRequest;
import com.Digi_Med.Hospital.Management.dtos.PatientResponseDTO;
import com.Digi_Med.Hospital.Management.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<PatientResponseDTO> registerPatient(@Valid @RequestBody PatientRegistrationRequest request) {
        PatientResponseDTO response = patientService.registerPatient(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<PatientResponseDTO> getPatientByRegistrationNumber(@PathVariable String registrationNumber) {
        return ResponseEntity.ok(patientService.getPatientByRegistrationNumber(registrationNumber));
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<PatientResponseDTO> getPatientByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(patientService.getPatientByPhoneNumber(phoneNumber));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientResponseDTO>> searchPatients(@RequestParam String name) {
        return ResponseEntity.ok(patientService.searchPatientsByName(name));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<PatientResponseDTO>> getPatientsByCity(@PathVariable String city) {
        return ResponseEntity.ok(patientService.getPatientsByCity(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRegistrationRequest request) {
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<String> activatePatient(@PathVariable Long id) {
        patientService.activatePatient(id);
        return ResponseEntity.ok("Patient activated successfully");
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivatePatient(@PathVariable Long id) {
        patientService.deactivatePatient(id);
        return ResponseEntity.ok("Patient deactivated successfully");
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalPatientCount() {
        return ResponseEntity.ok(patientService.getTotalPatientCount());
    }
}