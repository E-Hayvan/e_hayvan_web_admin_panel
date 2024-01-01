package com.production.ehayvanbackendapi.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class CreateOrUpdateScheduleDTO {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime beginningDate;
    private Integer doseFrequency;
    private Integer doseCount;

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
