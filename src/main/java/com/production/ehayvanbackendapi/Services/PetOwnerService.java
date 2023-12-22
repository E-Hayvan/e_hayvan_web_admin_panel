package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.AppointmentDTO;
import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.DTO.PetOwnerDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdateCustomerDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdatePetOwnerDTO;
import com.production.ehayvanbackendapi.Entities.*;
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

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetOwnerMapper petOwnerMapper) {
        this.petOwnerRepository = petOwnerRepository;
        this.petOwnerMapper = petOwnerMapper;
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

    public PetOwnerDTO postPetOwner(CreateOrUpdatePetOwnerDTO petOwnerDTO){
        PetOwner newOwner = petOwnerMapper.convertToEntity(petOwnerDTO);

        // Set User Type as 'Pet Owner'
        newOwner.getUser().setUserTypeID(new UserType(1));

        // Attach the target veterinarian to the new pet owner if it exists.
        Veterinarian targetVeterinarian = new Veterinarian();
        targetVeterinarian.setVetID(petOwnerDTO.getVeterinarianID());
        newOwner.setVet(targetVeterinarian);

        try{
            petOwnerRepository.save(newOwner);
            return petOwnerMapper.convertToDto(newOwner);
        }
        catch (Exception e){
            return null;
        }
    }

    public PetOwnerDTO deletePetOwner(Integer id){
        Optional<PetOwner> target = petOwnerRepository.findById(id);
        if(target.isPresent()){
            petOwnerRepository.delete(target.get());
            PetOwnerDTO targetDto = petOwnerMapper.convertToDto(target.get());
            return targetDto;
        }
        return null;
    }
}

