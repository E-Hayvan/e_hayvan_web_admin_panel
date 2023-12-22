package com.production.ehayvanbackendapi.Mappers;

import com.production.ehayvanbackendapi.DTO.PetOwnerDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdatePetOwnerDTO;
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
                mapper -> {
                    mapper.map(src -> src.getVet().getUser().getName(), PetOwnerDTO::setVetName);
                    mapper.map(src -> src.getVet().getVetID(), PetOwnerDTO::setVetID);
                }
        );
//        this.modelMapper.createTypeMap(CreateOrUpdatePetOwnerDTO.class, PetOwner.class).addMappings(
//                mapper -> {
//                    mapper.map(src -> src.getVeterinarianID(),
//                            (dest, val) -> dest.getVet().setVetID((Integer) val));
//                }
//        );
    }

    public PetOwnerDTO convertToDto(PetOwner petOwner) {
        return modelMapper.map(petOwner, PetOwnerDTO.class);
    }

    public PetOwner convertToEntity(CreateOrUpdatePetOwnerDTO petOwnerDTO) {
        return modelMapper.map(petOwnerDTO, PetOwner.class);
    }
}

