package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.Medication;
import com.production.ehayvanbackendapi.Repositories.MedicationRepository;

import java.util.List;

public class MedicationService {
    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }
    public List<Medication> getAllMedications(){
        return medicationRepository.findAll();
    }
}
