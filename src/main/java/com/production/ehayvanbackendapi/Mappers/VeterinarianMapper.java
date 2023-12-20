package com.production.ehayvanbackendapi.Mappers;

import com.production.ehayvanbackendapi.DTO.VeterinarianDTO;
import com.production.ehayvanbackendapi.Entities.Veterinarian;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VeterinarianMapper {
    private final ModelMapper modelMapper;

    public VeterinarianMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //DO NOT FORGET
    }

    public VeterinarianDTO convertToDto(Veterinarian veterinarian) {
        return modelMapper.map(veterinarian, VeterinarianDTO.class);
    }

    public Veterinarian convertToEntity(VeterinarianDTO veterinarianDTO) {
        return modelMapper.map(veterinarianDTO, Veterinarian.class);
    }
}

