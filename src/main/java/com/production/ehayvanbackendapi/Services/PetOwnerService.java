package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.AppointmentDTO;
import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.DTO.PetOwnerDTO;
import com.production.ehayvanbackendapi.Entities.Appointment;
import com.production.ehayvanbackendapi.Entities.Pet;
import com.production.ehayvanbackendapi.Entities.PetOwner;
import com.production.ehayvanbackendapi.Mappers.AppointmentMapper;
import com.production.ehayvanbackendapi.Mappers.PetMapper;
import com.production.ehayvanbackendapi.Mappers.PetOwnerMapper;
import com.production.ehayvanbackendapi.Repositories.PetOwnerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;
    private final PetOwnerMapper petOwnerMapper;
    private final PetMapper petMapper;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetOwnerMapper petOwnerMapper,
                           PetMapper petMapper, AppointmentMapper appointmentMapper) {
        this.petOwnerRepository = petOwnerRepository;
        this.petOwnerMapper = petOwnerMapper;
        this.petMapper = petMapper;
        this.appointmentMapper = appointmentMapper;
    }

    public PetOwnerDTO getPetOwnerById(Integer id) {
        Optional<PetOwner> petOwner = petOwnerRepository.findById(id);
        // Listeler otomatik olarak mapleniyor. Şimdilik bir problem yok.

        // PetOwnerDto içerisinde VeterinerDto var. Bu VeterinerDto içerisinde de
        // PetOwnerDto listesi var. Bunu engellemek için "PetOwnerMapper" dosyasında
        // Veteriner içerisindeki listelemeyi maplemeyi engelleyici bir komut yazdım.
        return petOwner.map(petOwnerMapper::convertToDto).orElse(null);
    }
    // Other service methods for updating, deleting pet owners, etc.
}

