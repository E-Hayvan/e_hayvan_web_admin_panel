package com.production.ehayvanbackendapi.Mappers;
import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.Entities.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    private final ModelMapper modelMapper;

    public PetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PetDTO convertToDto(Pet pet) {
        return modelMapper.map(pet, PetDTO.class);
    }

    public Pet convertToEntity(PetDTO petDTO) {
        return modelMapper.map(petDTO, Pet.class);
    }
}

