package com.production.ehayvanbackendapi.DTO;

public class PetDTO {
    private Integer petID;
    private String petName;
    private int age;
    private Integer petTypeID;

    public PetDTO(Integer petID, String petName, int age, Integer petTypeID,
                  String description, Integer petOwnerID) {
        this.petID = petID;
        this.petName = petName;
        this.age = age;
        this.petTypeID = petTypeID;
        this.description = description;
        this.petOwnerID = petOwnerID;
    }

    private String description;

    public Integer getPetID() {
        return petID;
    }

    public void setPetID(Integer petID) {
        this.petID = petID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
        return petOwnerID;
    }

    public void setPetOwnerID(Integer petOwnerID) {
        this.petOwnerID = petOwnerID;
    }

    private Integer petOwnerID;


}
