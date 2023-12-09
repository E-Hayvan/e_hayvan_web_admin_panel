package Entities;

import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

@Entity
@Table(name="Appointment")
public class Appointment {
    @Id
    private int AppointmentID;
    @ManyToOne
    @JoinColumn(name="PetOwnerID",referencedColumnName = "PetOwnerID")
    private PetOwner PetOwnerID;

    @ManyToOne
    @JoinColumn(name="VetID",referencedColumnName = "VetID")
    private Veterinarian VetID;
    private DateTimeLiteralExpression.DateTime AppointmentDate;
    private int PetID;
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

    public DateTimeLiteralExpression.DateTime getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(DateTimeLiteralExpression.DateTime appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public int getPetID() {
        return PetID;
    }

    public void setPetID(int petID) {
        PetID = petID;
    }




}
