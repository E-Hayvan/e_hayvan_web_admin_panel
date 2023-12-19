package com.production.ehayvanbackendapi.Mappers;

import com.production.ehayvanbackendapi.DTO.MedicationDTO;
import com.production.ehayvanbackendapi.Entities.Medication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {
    private final ModelMapper modelMapper;

    public MedicationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MedicationDTO convertToDto(Medication medication) {
        return modelMapper.map(medication, MedicationDTO.class);
    }

    public Medication convertToEntity(MedicationDTO medicationDTO) {
        return modelMapper.map(medicationDTO, Medication.class);
    }
}

