package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PetType")
public class PetType {
    @Id
    private int PetTypeID;
    private String Type;

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
}
