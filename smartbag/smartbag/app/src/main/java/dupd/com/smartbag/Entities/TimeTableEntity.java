package dupd.com.smartbag.Entities;

import java.util.ArrayList;
import java.util.List;

public class TimeTableEntity {

    String day;
    List<RFIDEntity> rfidEntities;

    public TimeTableEntity(String day, List<RFIDEntity> rfidEntities) {
        this.day = day;
        this.rfidEntities = rfidEntities;
    }

    public TimeTableEntity() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<RFIDEntity> getRfidEntities() {
        return rfidEntities;
    }

    public void setRfidEntities(ArrayList<RFIDEntity> rfidEntities) {
        this.rfidEntities = rfidEntities;
    }
}
