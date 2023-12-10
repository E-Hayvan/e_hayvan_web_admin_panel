package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.MedType;
import com.production.ehayvanbackendapi.Repositories.MedTypeRepository;
import com.production.ehayvanbackendapi.Repositories.MedicationRepository;

import java.util.List;

public class MedTypeService {
    private final MedTypeRepository medTypeRepository;

    public MedTypeService(MedTypeRepository medTypeRepository) {
        this.medTypeRepository = medTypeRepository;
    }
    public List<MedType> getAllMedTypes(){
        return medTypeRepository.findAll();
    }
}
