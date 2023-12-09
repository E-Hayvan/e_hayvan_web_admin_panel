package Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PetID;
    private String PetName;
    private int Age;
    @ManyToOne
    @JoinColumn(name = "PetTypeID", referencedColumnName = "PetTypeID")
    private PetType PetTypeID;
    private String Description;
    @OneToMany(mappedBy = "Appointments")
    private List<Appointment> Appointments;

    public int getPetID() {
        return PetID;
    }
    public void setPetID(int petID) {
        PetID = petID;
    }
    public String getPetName() {
        return PetName;
    }
    public void setPetName(String petName) {
        PetName = petName;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    public PetType getPetTypeID() {
        return PetTypeID;
    }
    public void setPetTypeID(PetType petTypeID) {
        PetTypeID = petTypeID;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
