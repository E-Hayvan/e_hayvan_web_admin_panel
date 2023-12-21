package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.AppointmentDTO;
import com.production.ehayvanbackendapi.DTO.MedicationDTO;
import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.Entities.Appointment;
import com.production.ehayvanbackendapi.Entities.Medication;
import com.production.ehayvanbackendapi.Entities.Pet;
import com.production.ehayvanbackendapi.Mappers.AppointmentMapper;
import com.production.ehayvanbackendapi.Mappers.MedicationMapper;
import com.production.ehayvanbackendapi.Mappers.PetMapper;
import com.production.ehayvanbackendapi.Repositories.PetRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Pet> pet = petRepository.findById(id);
        // Listeler otomatik olarak mapleniyor. Şimdilik bir problem yok.
        return pet.map(petMapper::convertToDto).orElse(null);
    }

//    public List<PetDTO> getAllPets() {
//        List<Pet> pets = petRepository.findAll();
//        return petMapper.mapList(pets, PetDTO.class);
//    }

    // Other service methods
}

