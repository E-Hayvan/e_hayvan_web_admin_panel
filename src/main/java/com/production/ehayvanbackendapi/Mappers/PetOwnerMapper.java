package com.production.ehayvanbackendapi.Mappers;

import com.production.ehayvanbackendapi.DTO.PetOwnerDTO;
import com.production.ehayvanbackendapi.Entities.PetOwner;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PetOwnerMapper {
    private final ModelMapper modelMapper;

    public PetOwnerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PetOwnerDTO convertToDto(PetOwner petOwner) {
        return modelMapper.map(petOwner, PetOwnerDTO.class);
    }

    public PetOwner convertToEntity(PetOwnerDTO petOwnerDTO) {
        return modelMapper.map(petOwnerDTO, PetOwner.class);
    }
}

