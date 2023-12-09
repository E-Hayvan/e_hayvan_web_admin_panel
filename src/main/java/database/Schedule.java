package database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Schedule")

public class Schedule {
    @Id
    int ScheduleID;
    

}
