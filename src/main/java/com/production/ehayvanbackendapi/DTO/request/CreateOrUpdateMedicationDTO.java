package com.production.ehayvanbackendapi.DTO.request;

public class CreateOrUpdateMedicationDTO {
    private String medicationName;
    private Integer medTypeID;
    private CreateOrUpdateScheduleDTO scheduleID;
    private Integer petID;

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Integer getMedTypeID() {
        return medTypeID;
    }

    public void setMedTypeID(Integer medTypeID) {
        this.medTypeID = medTypeID;
    }

    public Integer getPetID() {
        return petID;
    }

    public void setPetID(Integer petID) {
        this.petID = petID;
    }

    public CreateOrUpdateScheduleDTO getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(CreateOrUpdateScheduleDTO scheduleID) {
        this.scheduleID = scheduleID;
    }
}
