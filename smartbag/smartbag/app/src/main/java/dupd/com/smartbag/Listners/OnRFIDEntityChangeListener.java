package dupd.com.smartbag.Listners;

import java.util.List;

import dupd.com.smartbag.Entities.RFIDEntity;

public interface OnRFIDEntityChangeListener {
    void OnDataChenged(List<RFIDEntity> newRFIDEntity);
}
