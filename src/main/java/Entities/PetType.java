package Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PetType")
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PetTypeID;
    @Column(nullable = false)
    private String Type;
    @OneToMany(mappedBy = "Pet")
    private List<Pet> pets;

    public int getPetTypeID() {
        return PetTypeID;
    }
    public void setPetTypeID(int petTypeID) {
        PetTypeID = petTypeID;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
