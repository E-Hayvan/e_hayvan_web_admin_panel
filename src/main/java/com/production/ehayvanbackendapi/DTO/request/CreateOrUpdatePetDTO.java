package com.production.ehayvanbackendapi.DTO.request;

public class CreateOrUpdatePetDTO {
    private String petName;
    private Integer age;
    private Integer petTypeID;
    private String description;
    private Integer petOwnerID;

    public CreateOrUpdatePetDTO(String petName, Integer age, Integer petTypeID, String description, Integer petOwnerID) {
        this.petName = petName;
        this.age = age;
        this.petTypeID = petTypeID;
        this.description = description;
        this.petOwnerID = petOwnerID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPetTypeID() {
        return petTypeID;
    }

    public void setPetTypeID(Integer petTypeID) {
        this.petTypeID = petTypeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPetOwnerID() {
        return petOwnerID == null ? null : petOwnerID;
    }

    public void setPetOwnerID(Integer petOwnerID) {
        this.petOwnerID = petOwnerID;
    }
}
