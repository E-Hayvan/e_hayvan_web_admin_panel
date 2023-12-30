package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.MedicationDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdateMedicationDTO;
import com.production.ehayvanbackendapi.Entities.Medication;
import com.production.ehayvanbackendapi.Mappers.MedicationMapper;
import com.production.ehayvanbackendapi.Repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    public MedicationService(MedicationRepository medicationRepository, MedicationMapper medicationMapper) {
        this.medicationRepository = medicationRepository;
        this.medicationMapper = medicationMapper;
    }

    public MedicationDTO getMedicationById(Integer id) {
        Medication medication = medicationRepository.findById(id).orElse(null);
        return medication != null ? medicationMapper.convertToDto(medication) : null;
    }

    public MedicationDTO postMedication(CreateOrUpdateMedicationDTO medicationDto){
        // Create a new medication from the dto.
        Medication newMedication = medicationMapper.convertToEntity(medicationDto);

        // Attempt posting new medication data to DB. If fails, return null.
        try{
            medicationRepository.save(newMedication);
            return medicationMapper.convertToDto(newMedication);
        }
        catch (Exception e){
            return null;
        }
    }

    public MedicationDTO deleteMedication(Integer id) {
        // Find the target medication to delete.
        Optional<Medication> targetMedication = medicationRepository.findById(id);

        // Delete medication and return DTO if it exists.
        if (targetMedication.isPresent()) {
            medicationRepository.delete(targetMedication.get());
            return medicationMapper.convertToDto(targetMedication.get());
        }
        // Return null if the target entity does not exist.
        return null;
    }

    // Other service methods for updating, deleting medications, etc.
}

