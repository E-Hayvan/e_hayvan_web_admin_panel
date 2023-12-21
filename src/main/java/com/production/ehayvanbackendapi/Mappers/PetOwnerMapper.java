package com.production.ehayvanbackendapi.Mappers;

import com.production.ehayvanbackendapi.DTO.PetOwnerDTO;
import com.production.ehayvanbackendapi.Entities.Pet;
import com.production.ehayvanbackendapi.Entities.PetOwner;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetOwnerMapper {
    private final ModelMapper modelMapper;

    public PetOwnerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(PetOwner.class, PetOwnerDTO.class).addMappings(
                mapper -> mapper.skip(src -> src.getVet().getPetOwners(), (dest, val) -> dest.getVet().setPetOwners(
                        (List<PetOwnerDTO>) val))
        );
    }

    public PetOwnerDTO convertToDto(PetOwner petOwner) {
        return modelMapper.map(petOwner, PetOwnerDTO.class);
    }

    public PetOwner convertToEntity(PetOwnerDTO petOwnerDTO) {
        return modelMapper.map(petOwnerDTO, PetOwner.class);
    }
}

