package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.Pet;
import com.production.ehayvanbackendapi.Repositories.PetRepository;

import java.util.List;

public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }
}
