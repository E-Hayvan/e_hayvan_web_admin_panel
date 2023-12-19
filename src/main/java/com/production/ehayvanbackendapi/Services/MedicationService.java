package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.MedicationDTO;
import com.production.ehayvanbackendapi.Entities.Medication;
import com.production.ehayvanbackendapi.Mappers.MedicationMapper;
import com.production.ehayvanbackendapi.Repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, MedicationMapper medicationMapper) {
        this.medicationRepository = medicationRepository;
        this.medicationMapper = medicationMapper;
    }

    public MedicationDTO getMedicationById(Integer id) {
        Medication medication = medicationRepository.findById(id).orElse(null);
        return medication != null ? medicationMapper.convertToDto(medication) : null;
    }

    public MedicationDTO saveMedication(MedicationDTO medicationDTO) {
        Medication medication = medicationMapper.convertToEntity(medicationDTO);
        Medication savedMedication = medicationRepository.save(medication);
        return medicationMapper.convertToDto(savedMedication);
    }

    // Other service methods for updating, deleting medications, etc.
}

