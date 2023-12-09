package Entities;

import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.List;

@Entity
@Table(name="Schedule")

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ScheduleID;
    @OneToMany(mappedBy = "ScheduleID")
    private List<Medication> medications;
    @Column(nullable = false)
    private DateTimeLiteralExpression.DateTime beginningDate;
    @Column(nullable = false)
    private int doseFrequency;
    @Column(nullable = false)
    private int doseCount;

    public int getScheduleID() {
        return ScheduleID;
    }
    public void setScheduleID(int scheduleID) {
        ScheduleID = scheduleID;
    }
    public DateTimeLiteralExpression.DateTime getBeginningDate() {
        return beginningDate;
    }
    public void setBeginningDate(DateTimeLiteralExpression.DateTime beginningDate) {
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
    public List<Medication> getMedications() {
        return medications;
    }
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
