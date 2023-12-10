package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.Veterinarian;
import com.production.ehayvanbackendapi.Repositories.VeterinarianRepository;

import java.util.List;

public class VeterinarianService {
    private final VeterinarianRepository veterinarianRepository;

    public VeterinarianService(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    public List<Veterinarian> getAllVeterinarians(){
        return veterinarianRepository.findAll();
    }
}
