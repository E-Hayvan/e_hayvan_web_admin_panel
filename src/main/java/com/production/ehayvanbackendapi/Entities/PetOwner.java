package com.production.ehayvanbackendapi.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PetOwner")
public class PetOwner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PetOwnerID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User User;
    @OneToMany(mappedBy = "PetOwnerID")
    private List<Pet> Pets;
    @ManyToOne
    @JoinColumn(name = "VetID", referencedColumnName = "VetID")
    private Veterinarian Vet;
    @OneToMany(mappedBy = "PetOwnerID")
    private List<Appointment> Appointments;

    public int getPetOwnerID() {
        return PetOwnerID;
    }
    public void setPetOwnerID(int petOwnerID) {
        PetOwnerID = petOwnerID;
    }
    public com.production.ehayvanbackendapi.Entities.User getUser() {
        return User;
    }
    public void setUser(com.production.ehayvanbackendapi.Entities.User user) {
        User = user;
    }
    public List<Pet> getPets() {
        return Pets;
    }
    public void setPets(List<Pet> pets) {
        Pets = pets;
    }
    public Veterinarian getVet() {
        return Vet;
    }
    public void setVet(Veterinarian vet) {
        Vet = vet;
    }
    public List<Appointment> getAppointments() {
        return Appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        Appointments = appointments;
    }
}
