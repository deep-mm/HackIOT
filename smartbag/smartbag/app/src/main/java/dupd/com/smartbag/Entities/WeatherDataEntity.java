package dupd.com.smartbag.Entities;

public class WeatherDataEntity {

    private String Temperature;
    private String Humidity;

    public WeatherDataEntity(String temperature, String humidity) {
        Temperature = temperature;
        Humidity = humidity;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }
}
