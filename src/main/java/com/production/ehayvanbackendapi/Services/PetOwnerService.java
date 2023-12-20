package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.PetOwnerDTO;
import com.production.ehayvanbackendapi.Entities.PetOwner;
import com.production.ehayvanbackendapi.Mappers.PetOwnerMapper;
import com.production.ehayvanbackendapi.Repositories.PetOwnerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;
    private final PetOwnerMapper petOwnerMapper;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetOwnerMapper petOwnerMapper) {
        this.petOwnerRepository = petOwnerRepository;
        this.petOwnerMapper = petOwnerMapper;
    }

    public PetOwnerDTO getPetOwnerById(Integer id) {
        PetOwner petOwner = petOwnerRepository.findById(id).orElse(null);
        return petOwner != null ? petOwnerMapper.convertToDto(petOwner) : null;
    }


    // Other service methods for updating, deleting pet owners, etc.
}

