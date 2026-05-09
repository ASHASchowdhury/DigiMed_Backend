package com.Digi_Med.Hospital.Management.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "symptom_analyses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymptomAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(length = 1000)
    private String symptoms;

    @Column(length = 2000)
    private String aiSuggestions;

    private String recommendedSpecialization;
    private String severity; // MILD, MODERATE, SEVERE
    private String disclaimer = "AI suggestions are preliminary only. Please consult a doctor for final diagnosis.";
    private LocalDateTime analyzedAt;

    @PrePersist
    protected void onCreate() {
        analyzedAt = LocalDateTime.now();
    }
}
