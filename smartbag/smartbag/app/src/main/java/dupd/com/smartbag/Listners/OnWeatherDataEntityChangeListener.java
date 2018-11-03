package dupd.com.smartbag.Listners;

import java.util.List;

import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Entities.WeatherDataEntity;

public interface OnWeatherDataEntityChangeListener {
    void OnDataChenged(List<WeatherDataEntity> newWeatherDataEntities);
}
