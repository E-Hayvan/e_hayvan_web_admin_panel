package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.PetType;
import com.production.ehayvanbackendapi.Repositories.PetTypeRepository;

import java.util.List;

public class PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }
    public List<PetType> getAllPetTypes(){
        return petTypeRepository.findAll();
    }
}
