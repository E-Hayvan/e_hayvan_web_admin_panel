package Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Medication")

public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicationID;
    private String medicationName;
    @ManyToOne
    @JoinColumn(name="MedTypeID",referencedColumnName = "MedTypeID")
    private MedType MedTypeID;

    @ManyToOne
    @JoinColumn(name="ScheduleID",referencedColumnName = "ScheduleID")
    private Schedule ScheduleID;

    private int PetID;

    public int getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public MedType getMedTypeID() {
        return MedTypeID;
    }

    public void setMedTypeID(MedType medTypeID) {
        MedTypeID = medTypeID;
    }

    public Schedule getScheduleID() {
        return ScheduleID;
    }

    public void setScheduleID(Schedule scheduleID) {
        ScheduleID = scheduleID;
    }

    public int getPetID() {
        return PetID;
    }

    public void setPetID(int petID) {
        PetID = petID;
    }
}
