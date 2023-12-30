package com.production.ehayvanbackendapi.Entities;

import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Schedule")

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ScheduleID;
    @OneToMany(mappedBy = "ScheduleID")
    private List<Medication> medications;
    @Column(nullable = false)
    private Date beginningDate;
    @Column(nullable = false)
    private Integer doseFrequency;
    @Column(nullable = false)
    private Integer doseCount;

    public int getScheduleID() {
        return ScheduleID;
    }
    public void setScheduleID(Integer scheduleID) {
        ScheduleID = scheduleID;
    }
    public Date getBeginningDate() {
        return beginningDate;
    }
    public void setBeginningDate(Date beginningDate) {
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
