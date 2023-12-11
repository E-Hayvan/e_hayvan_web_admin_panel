package com.production.ehayvanbackendapi.Entities;

import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

@Entity
@Table(name="Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer AppointmentID;
    @ManyToOne
    @JoinColumn(name = "PetOwnerID", referencedColumnName = "PetOwnerID")
    private PetOwner PetOwnerID;
    @ManyToOne
    @JoinColumn(name = "VetID", referencedColumnName = "VetID")
    private Veterinarian VetID;
    @ManyToOne
    @JoinColumn(name = "PetID", referencedColumnName = "PetID")
    private Pet PetID;
    @Column(nullable = false)
    private Date AppointmentDate;
    public int getAppointmentID() {
        return AppointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        AppointmentID = appointmentID;
    }
    public PetOwner getPetOwnerID() {
        return PetOwnerID;
    }
    public void setPetOwnerID(PetOwner petOwnerID) {
        PetOwnerID = petOwnerID;
    }
    public Veterinarian getVetID() {
        return VetID;
    }
    public void setVetID(Veterinarian vetID) {
        VetID = vetID;
    }
    public Pet getPetID() {
        return PetID;
    }
    public void setPetID(Pet petID) {
        PetID = petID;
    }
    public Date getAppointmentDate() {
        return AppointmentDate;
    }
    public void setAppointmentDate(Date appointmentDate) {
        AppointmentDate = appointmentDate;
    }
}
