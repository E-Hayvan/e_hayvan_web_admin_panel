package com.production.ehayvanbackendapi.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CreateOrUpdateAppointmentDTO {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
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
