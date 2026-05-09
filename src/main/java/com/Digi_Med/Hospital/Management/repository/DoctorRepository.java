package com.Digi_Med.Hospital.Management.repository;

import com.Digi_Med.Hospital.Management.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByLicenseNumber(String licenseNumber);
    Optional<Doctor> findByUser_Email(String email);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByDepartment(String department);
    List<Doctor> findByStatus(String status);
    List<Doctor> findBySpecializationAndStatus(String specialization, String status);
}
