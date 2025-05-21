package tesut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherApp {

    // 地域コードと表示名のペア
    private static final String[][] AREAS = {
            { "130000", "東京" },
            { "270000", "大阪" },
            { "240000", "三重" }
    };

    public String fetchWeatherData(String areaCode) throws Exception {
        String apiUrl = "https://www.jma.go.jp/bosai/forecast/data/forecast/" + areaCode + ".json";

        URI uri = new URI(apiUrl);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder responseBody = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
            }
            return responseBody.toString();
        } else {
            throw new IOException("HTTP エラーコード: " + responseCode);
        }
    }

    public static void main(String[] args) {
        WeatherApp client = new WeatherApp();

        for (String[] area : AREAS) {
            String code = area[0];
            String name = area[1];

            System.out.println("▼ " + name + " の天気予報 ----------------------");

            try {
                String jsonData = client.fetchWeatherData(code);

                JSONArray rootArray = new JSONArray(jsonData);
                JSONObject firstItem = rootArray.getJSONObject(0);

                // 時刻情報（ISO8601形式）
                JSONArray timeDefines = firstItem
                        .getJSONArray("timeSeries")
                        .getJSONObject(0)
                        .getJSONArray("timeDefines");

                // 天気データ
                JSONArray areas = firstItem
                        .getJSONArray("timeSeries")
                        .getJSONObject(0)
                        .getJSONArray("areas");

                for (int i = 0; i < areas.length(); i++) {
                    JSONObject areaObj = areas.getJSONObject(i);
                    String areaName = areaObj.getJSONObject("area").getString("name");
                    JSONArray weathers = areaObj.getJSONArray("weathers");

                    System.out.println("エリア: " + areaName);
                    for (int j = 0; j < weathers.length(); j++) {
                        String time = timeDefines.getString(j);
                        ZonedDateTime zonedDateTime = ZonedDateTime.parse(time);
                        String formattedTime = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

                        System.out.println("[" + formattedTime + "] " + weathers.getString(j));
                    }
                    System.out.println();
                }

            } catch (Exception e) {
                System.err.println("エラー: " + e.getMessage());
            }

            System.out.println("------------------------------------------\n");
        }
    }
}