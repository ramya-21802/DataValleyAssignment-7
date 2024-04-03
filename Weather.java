import java.util.*;

class Weather {
    double temp;
    double humid;

    public Weather(double temp, double humid) {
        this.temp = temp;
        this.humid = humid;
    }

    public double getTemp() {
        return temp;
    }

    public double getHumid() {
        return humid;
    }
}

public class weather {

    public static Map<String, Integer> countDays(List<Weather> weatherData) {
        Map<String, Integer> countByTemperature = new HashMap<>();
        for (Weather data : weatherData) {
            String temperatureRange = getTemperatureRange(data.temp);
            countByTemperature.put(temperatureRange, countByTemperature.getOrDefault(temperatureRange, 0) + 1);
        }
        return countByTemperature;
    }

    public static Map<String, Double> calculateAverageHumidityByTemperatureRange(List<Weather> weatherDataList) {
        Map<String, Double> avgHumidityByTemperatureRange = new HashMap<>();
        Map<String, Integer> countByTemperatureRange = new HashMap<>();
        for (Weather data : weatherDataList) {
            String temperatureRange = getTemperatureRange(data.temp);
            double currentHumidity = data.getHumid();
            double totalHumidity = avgHumidityByTemperatureRange.getOrDefault(temperatureRange, 0.0) * countByTemperatureRange.getOrDefault(temperatureRange, 0);
            totalHumidity += currentHumidity;
            countByTemperatureRange.put(temperatureRange, countByTemperatureRange.getOrDefault(temperatureRange, 0) + 1);
            avgHumidityByTemperatureRange.put(temperatureRange, totalHumidity / countByTemperatureRange.get(temperatureRange));
        }
        return avgHumidityByTemperatureRange;
    }

    public static String getTemperatureRange(double temperature) {
        if (temperature < 0) {
            return "<0°C";
        } else if (temperature < 10) {
            return "0-10°C";
        } else if (temperature < 20) {
            return "10-20°C";
        } else {
            return ">20°C";
        }
    }

    public static void main(String[] args) {
        // Sample weather data
        List<Weather> weatherDataList = new ArrayList<>();
        weatherDataList.add(new Weather(-5, 80));
        weatherDataList.add(new Weather(5, 70));
        weatherDataList.add(new Weather(15, 65));
        weatherDataList.add(new Weather(25, 75));
        weatherDataList.add(new Weather(18, 60));

        // Count days by temperature range
        Map<String, Integer> daysByTemperatureRange = countDays(weatherDataList);

        // Calculate average humidity by temperature range
        Map<String, Double> avgHumidityByTemperatureRange = calculateAverageHumidityByTemperatureRange(weatherDataList);

        // Display results
        System.out.println("Days with temperatures within each range:");
        for (Map.Entry<String, Integer> entry : daysByTemperatureRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " days");
        }

        System.out.println("\nAverage humidity for each temperature range:");
        for (Map.Entry<String, Double> entry : avgHumidityByTemperatureRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }
    }
}
