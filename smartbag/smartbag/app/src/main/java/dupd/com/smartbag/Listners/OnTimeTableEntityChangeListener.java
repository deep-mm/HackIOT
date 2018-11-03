package dupd.com.smartbag.Listners;

import java.util.List;

import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Entities.TimeTableEntity;

public interface OnTimeTableEntityChangeListener {
    void OnDataChenged(List<TimeTableEntity> newTimeTableEntities);
}
