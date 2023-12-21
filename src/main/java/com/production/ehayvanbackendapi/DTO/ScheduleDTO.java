package com.production.ehayvanbackendapi.DTO;

import java.util.Date;
import java.util.List;

public class ScheduleDTO {
    private Integer scheduleID;
        private List<MedicationDTO> medications;
        private Date beginningDate;
        private int doseFrequency;
        private int doseCount;
    public ScheduleDTO(){

    }
    public ScheduleDTO(Integer scheduleID, List<MedicationDTO> medications,
                       Date beginningDate, int doseFrequency, int doseCount) {
        this.scheduleID = scheduleID;
        this.medications = medications;
        this.beginningDate = beginningDate;
        this.doseFrequency = doseFrequency;
        this.doseCount = doseCount;
    }

    public Integer getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public int getDoseFrequency() {
        return doseFrequency;
    }

    public void setDoseFrequency(int doseFrequency) {
        this.doseFrequency = doseFrequency;
    }

    public int getDoseCount() {
        return doseCount;
    }

    public void setDoseCount(int doseCount) {
        this.doseCount = doseCount;
    }




}

