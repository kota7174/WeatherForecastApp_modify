import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class WeatherApiClient {
    private static final String API_URL = "https://www.jma.go.jp/bosai/forecast/data/forecast/240000.json";

    public String fetchWeatherData() throws Exception {
        URI uri = new URI(API_URL);
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
            throw new IOException("Failed to fetch data: HTTP response code " + responseCode);
        }
    }
}
