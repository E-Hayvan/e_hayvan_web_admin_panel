package database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

@Entity
@Table(name="Schedule")

public class Schedule {
    @Id
    int ScheduleID;
    DateTimeLiteralExpression.DateTime beginningDate;
    int doseFrequency;
    int doseCount;
    

}
