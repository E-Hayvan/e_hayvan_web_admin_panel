package com.production.ehayvanbackendapi.DTO;

import java.util.List;

public class PetOwnerDTO {
    private Integer petOwnerID;
    private CustomerDTO user;
    private List<PetDTO> pets;
    private VeterinarianDTO vet;
    private List<AppointmentDTO> appointments;
    public PetOwnerDTO(){

    }
    public PetOwnerDTO(Integer petOwnerID, CustomerDTO user, List<PetDTO> pets,
                       VeterinarianDTO vet, List<AppointmentDTO> appointments) {
        this.petOwnerID = petOwnerID;
        this.user = user;
        this.pets = pets;
        this.vet = vet;
        this.appointments = appointments;
    }


    public Integer getPetOwnerID() {
        return petOwnerID;
    }

    public void setPetOwnerID(Integer petOwnerID) {
        this.petOwnerID = petOwnerID;
    }

    public CustomerDTO getUser() {
        return user;
    }

    public void setUser(CustomerDTO user) {
        this.user = user;
    }

    public List<PetDTO> getPets() {
        return pets;
    }

    public void setPets(List<PetDTO> pets) {
        this.pets = pets;
    }

    public VeterinarianDTO getVet() {
        return vet;
    }

    public void setVet(VeterinarianDTO vet) {
        this.vet = vet;
    }

    public List<AppointmentDTO> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDTO> appointments) {
        this.appointments = appointments;
    }



}

