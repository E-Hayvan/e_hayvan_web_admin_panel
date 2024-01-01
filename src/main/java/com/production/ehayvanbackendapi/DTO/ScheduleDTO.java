package com.production.ehayvanbackendapi.DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ScheduleDTO {
    private Integer scheduleID;
    private LocalDateTime beginningDate;
    private Integer doseFrequency;
    private Integer doseCount;
    public ScheduleDTO(){

    }
    public ScheduleDTO(Integer scheduleID, List<MedicationDTO> medications,
                       LocalDateTime beginningDate, Integer doseFrequency, Integer doseCount) {
        this.scheduleID = scheduleID;
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

    public LocalDateTime getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDateTime beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Integer getDoseFrequency() {
        return doseFrequency;
    }

    public void setDoseFrequency(Integer doseFrequency) {
        this.doseFrequency = doseFrequency;
    }

    public Integer getDoseCount() {
        return doseCount;
    }

    public void setDoseCount(Integer doseCount) {
        this.doseCount = doseCount;
    }

}

