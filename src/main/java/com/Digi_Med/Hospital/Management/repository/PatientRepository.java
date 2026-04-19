package com.Digi_Med.Hospital.Management.repository;

import com.Digi_Med.Hospital.Management.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPhoneNumber(String phoneNumber);
    Optional<Patient> findByRegistrationNumber(String registrationNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    List<Patient> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    List<Patient> findByCity(String city);
}