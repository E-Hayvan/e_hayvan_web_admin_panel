package com.production.ehayvanbackendapi.Entities;

import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Schedule")

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ScheduleID;
    @OneToMany(mappedBy = "ScheduleID", cascade = CascadeType.ALL)
    private List<Medication> medications;
    @Column(nullable = false)
    private LocalDateTime beginningDate;
    @Column(nullable = false)
    private Integer doseFrequency;
    @Column(nullable = false)
    private Integer doseCount;

    public Integer getScheduleID() {
        return ScheduleID;
    }
    public void setScheduleID(Integer scheduleID) {
        ScheduleID = scheduleID;
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
    public List<Medication> getMedications() {
        return medications;
    }
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
