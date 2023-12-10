package com.production.ehayvanbackendapi.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Veterinarian")
public class Veterinarian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int VetID;
    @OneToMany(mappedBy = "Vet")
    private List<PetOwner> PetOwners;
    @Column(nullable = false)
    private String Clinic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Customer User;
    @OneToMany(mappedBy = "VetID")
    private List<Appointment> Appointments;

    public String getClinic() {
        return Clinic;
    }
    public void setClinic(String clinic) {
        Clinic = clinic;
    }
    public int getVetID() {
        return VetID;
    }
    public void setVetID(int vetID) {
        VetID = vetID;
    }
    public List<PetOwner> getPetOwners() {
        return PetOwners;
    }
    public void setPetOwners(List<PetOwner> petOwners) {
        PetOwners = petOwners;
    }
}
