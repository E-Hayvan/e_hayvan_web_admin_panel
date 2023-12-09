package Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="MedType")
public class MedType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int MedTypeID;
    String MedType;
    @OneToMany(mappedBy = "Medication")
    List<Medication> medications;

    public int getMedTypeID() {
        return MedTypeID;
    }

    public void setMedTypeID(int medTypeID) {
        MedTypeID = medTypeID;
    }

    public String getMedType() {
        return MedType;
    }

    public void setMedType(String medType) {
        MedType = medType;
    }



}
