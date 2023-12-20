package com.production.ehayvanbackendapi.Mappers;

import com.production.ehayvanbackendapi.DTO.MedTypeDTO;
import com.production.ehayvanbackendapi.Entities.MedType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MedTypeMapper {
    private final ModelMapper modelMapper;

    public MedTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //eklenmeli mi?
    }

    public MedTypeDTO convertToDto(MedType medType) {
        return modelMapper.map(medType, MedTypeDTO.class);
    }

    public MedType convertToEntity(MedTypeDTO medTypeDTO) {
        return modelMapper.map(medTypeDTO, MedType.class);
    }
}

