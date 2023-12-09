package database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="MedType")
public class MedType{
    @Id
    int MedTypeID;
    String MedType;

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
