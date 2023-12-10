package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.PetOwner;
import com.production.ehayvanbackendapi.Repositories.PetOwnerRepository;

import java.util.List;

public class PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;

    public PetOwnerService(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }
    public List<PetOwner> getAllPetOwners(){
        return petOwnerRepository.findAll();
    }
}
