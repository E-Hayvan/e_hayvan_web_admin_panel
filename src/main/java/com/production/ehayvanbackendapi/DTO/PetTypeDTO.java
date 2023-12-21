package com.production.ehayvanbackendapi.DTO;

import java.util.List;

public class PetTypeDTO {
    private Integer petTypeID;
    private String type;
    private List<PetDTO> pets;
    public PetTypeDTO(){

    }
    public PetTypeDTO(Integer petTypeID, String type, List<PetDTO> pets) {
        this.petTypeID = petTypeID;
        this.type = type;
        this.pets = pets;
    }

    public Integer getPetTypeID() {
        return petTypeID;
    }

    public void setPetTypeID(Integer petTypeID) {
        this.petTypeID = petTypeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PetDTO> getPets() {
        return pets;
    }

    public void setPets(List<PetDTO> pets) {
        this.pets = pets;
    }
}

