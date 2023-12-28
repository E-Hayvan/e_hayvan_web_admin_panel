package com.production.ehayvanbackendapi.DTO.request;

import java.util.Date;

public class CreateOrUpdateAppointmentDTO {
    private Date appointmentDate;
    private Integer petID;
    private Integer vetID;
    private Integer petOwnerID;

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getPetID() {
        return petID;
    }

    public void setPetID(Integer petID) {
        this.petID = petID;
    }

    public Integer getVetID() {
        return vetID;
    }

    public void setVetID(Integer vetID) {
        this.vetID = vetID;
    }

    public Integer getPetOwnerID() {
        return petOwnerID;
    }

    public void setPetOwnerID(Integer petOwnerID) {
        this.petOwnerID = petOwnerID;
    }
}
