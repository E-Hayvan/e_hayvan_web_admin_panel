package com.production.ehayvanbackendapi.DTO;

public class MedicationDTO {
    private Integer medicationID;
    private String medicationName;
    private Integer medTypeID;
    private Integer scheduleID;
    private Integer petID;


    public MedicationDTO(Integer medicationID, String medicationName, Integer
            medTypeID, Integer scheduleID, Integer petID) {
        this.medicationID = medicationID;
        this.medicationName = medicationName;
        this.medTypeID = medTypeID;
        this.scheduleID = scheduleID;
        this.petID = petID;
    }
    public Integer getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(Integer medicationID) {
        this.medicationID = medicationID;
    }

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

    public Integer getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Integer getPetID() {
        return petID;
    }

    public void setPetID(Integer petID) {
        this.petID = petID;
    }







}
