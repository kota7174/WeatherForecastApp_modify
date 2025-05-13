import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataParser {
    public List<WeatherData> parseWeatherData(String jsonData) {
        List<WeatherData> weatherDataList = new ArrayList<>();

        JSONArray rootArray = new JSONArray(jsonData);
        JSONObject timeStringObject = rootArray.getJSONObject(0)
                .getJSONArray("timeSeries").getJSONObject(0);

        JSONArray timeDefinesArray = timeStringObject.getJSONArray("timeDefines");
        JSONArray weathersArray = timeStringObject.getJSONArray("areas")
                .getJSONObject(0).getJSONArray("weathers");

        for (int i = 0; i < timeDefinesArray.length(); i++) {
            String time = timeDefinesArray.getString(i);
            String weather = weathersArray.getString(i);
            weatherDataList.add(new WeatherData(time, weather));
        }

        return weatherDataList;
    }
}