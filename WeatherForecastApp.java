import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherForecastApp {
    public static void main(String[] args) {
        try {
            // WeatherApiClientを使用してデータを取得
            WeatherApiClient apiClient = new WeatherApiClient();
            String jsonData = apiClient.fetchWeatherData();

            // WeatherDataParserを使用してデータを解析
            WeatherDataParser parser = new WeatherDataParser();
            List<WeatherData> weatherDataList = parser.parseWeatherData(jsonData);

            // データを表示
            for (WeatherData data : weatherDataList) {
                LocalDateTime dateTime = LocalDateTime.parse(
                        data.getTime(),
                        DateTimeFormatter.ISO_DATE_TIME);
                System.out.println(
                        dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " " + data.getWeather());
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // fetchWeatherDataがスローするその他の例外を処理
            System.err.println("データ取得中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }
}