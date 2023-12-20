package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.Entities.Pet;
import com.production.ehayvanbackendapi.Mappers.PetMapper;
import com.production.ehayvanbackendapi.Repositories.PetRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Autowired
    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    public PetDTO getPetById(Integer id) {
        Pet pet = petRepository.findById(id).orElse(null);
        return pet != null ? petMapper.convertToDto(pet) : null;
    }

//    public List<PetDTO> getAllPets() {
//        List<Pet> pets = petRepository.findAll();
//        return petMapper.mapList(pets, PetDTO.class);
//    }

    // Other service methods
}

