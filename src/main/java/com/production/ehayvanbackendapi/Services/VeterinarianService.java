package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.VeterinarianDTO;
import com.production.ehayvanbackendapi.Entities.Veterinarian;
import com.production.ehayvanbackendapi.Mappers.VeterinarianMapper;
import com.production.ehayvanbackendapi.Repositories.VeterinarianRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarianService {
    private final VeterinarianRepository veterinarianRepository;
    private final VeterinarianMapper veterinarianMapper;

    @Autowired
    public VeterinarianService(VeterinarianRepository veterinarianRepository, VeterinarianMapper veterinarianMapper) {
        this.veterinarianRepository = veterinarianRepository;
        this.veterinarianMapper = veterinarianMapper;
    }

    public VeterinarianDTO getVeterinarianById(Integer id) {
        Veterinarian veterinarian = veterinarianRepository.findById(id).orElse(null);
        return veterinarian != null ? veterinarianMapper.convertToDto(veterinarian) : null;
    }

//    public VeterinarianDTO saveVeterinarian(VeterinarianDTO veterinarianDTO) {
//        Veterinarian veterinarian = veterinarianMapper.convertToEntity(veterinarianDTO);
//        Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);
//        return veterinarianMapper.convertToDto(savedVeterinarian);
//    }

    // Other service methods for updating, deleting veterinarians, etc.
}

