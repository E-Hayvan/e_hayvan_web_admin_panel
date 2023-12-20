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
        this.modelMapper.createTypeMap(Medication.class, MedicationDTO.class).addMappings(
                mapper -> {
                    mapper.map(src -> src.getPetID().getPetID(), MedicationDTO::setPetID);
                    mapper.map(src -> src.getScheduleID().getScheduleID(), MedicationDTO::setScheduleID);
                    mapper.map(src -> src.getMedTypeID().getMedTypeID(), MedicationDTO::setMedTypeID);
                }
        );
    }

    public MedicationDTO convertToDto(Medication medication) {
        return modelMapper.map(medication, MedicationDTO.class);
    }

    public Medication convertToEntity(MedicationDTO medicationDTO) {
        return modelMapper.map(medicationDTO, Medication.class);
    }
}

