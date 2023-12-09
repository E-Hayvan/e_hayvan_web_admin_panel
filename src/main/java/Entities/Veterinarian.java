package Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Veterinarian")
public class Veterinarian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int VetID;
    @OneToMany(mappedBy = "PetOwner")
    private List<PetOwner> PetOwners;
    private String Clinic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User User;
    @OneToMany(mappedBy = "Appointment")
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
