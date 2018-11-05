package dupd.com.smartbag.Entities;

public class WeatherDataEntity {

    private Double Temperature;
    private Double Humidity;

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public void setHumidity(Double humidity) {
        Humidity = humidity;
    }

    public WeatherDataEntity(Double temperature, Double humidity) {
        Temperature = temperature;
        Humidity = humidity;
    }

    public WeatherDataEntity() {
    }
}
