import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class WeatherResultPrinter {

    public static void printWeatherForecasts(List<WeatherData> datas, WeatherApiClient client)
            throws IOException, InterruptedException {
        for (WeatherData data : datas) {
            JSONObject json = client.getWeatherForecast(data, 3);

            JSONArray dates = json.getJSONObject("daily").getJSONArray("time");
            JSONArray weatherCodes = json.getJSONObject("daily").getJSONArray("weathercode");
            JSONArray tempsMax = json.getJSONObject("daily").getJSONArray("temperature_2m_max");
            JSONArray tempsMin = json.getJSONObject("daily").getJSONArray("temperature_2m_min");

            System.out.println("==== " + data.getName() + "の天気予報 ====");
            for (int d = 0; d < dates.length(); d++) {
                String date = dates.getString(d);
                int code = weatherCodes.getInt(d);
                double max = tempsMax.getDouble(d);
                double min = tempsMin.getDouble(d);
                String weather = WeatherMethodlist.printWeather(code);

                System.out.printf("%s: %s, 最低 %.1f°C, 最高 %.1f°C%n", date, weather, min, max);
            }
            System.out.println();
        }
    }

    public static String getWeatherForecastsHtml(List<WeatherData> datas, WeatherApiClient client)
            throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();

        // 画像を追加
        sb.append("<div style='text-align:center; margin-bottom:20px;'>")
                .append("<img src= 'image/map.png' alt='地方地図' style='width:100%; max-width:1200px; height:auto;'>")
                .append("</div>");

        sb.append("<h1 style='text-align:center; color:#3366cc; margin-bottom:30px;'>天気予報</h1>");

        for (WeatherData data : datas) {
            JSONObject json = client.getWeatherForecast(data, 3);

            JSONArray dates = json.getJSONObject("daily").getJSONArray("time");
            JSONArray weatherCodes = json.getJSONObject("daily").getJSONArray("weathercode");
            JSONArray tempsMax = json.getJSONObject("daily").getJSONArray("temperature_2m_max");
            JSONArray tempsMin = json.getJSONObject("daily").getJSONArray("temperature_2m_min");

            sb.append("<section style='margin-bottom:32px;'>");
            sb.append("<h2 style='color:#2a4d69;'>").append(data.getName()).append("の天気予報</h2>");
            sb.append("<table style='border-collapse:collapse; width:100%; margin-top:10px;'>");
            sb.append("<thead><tr style='background:#e3eafc;'><th style='border:1px solid #ccc; padding:8px;'>日付</th>")
                    .append("<th style='border:1px solid #ccc; padding:8px;'>天気</th>")
                    .append("<th style='border:1px solid #ccc; padding:8px;'>最低気温</th>")
                    .append("<th style='border:1px solid #ccc; padding:8px;'>最高気温</th></tr></thead><tbody>");
            for (int d = 0; d < dates.length(); d++) {
                String date = dates.getString(d);
                int code = weatherCodes.getInt(d);
                double max = tempsMax.getDouble(d);
                double min = tempsMin.getDouble(d);
                String weather = WeatherMethodlist.printWeather(code);

                sb.append("<tr>")
                        .append("<td style='border:1px solid #ccc; padding:8px;'>").append(date).append("</td>")
                        .append("<td style='border:1px solid #ccc; padding:8px;'>").append(weather).append("</td>")
                        .append("<td style='border:1px solid #ccc; padding:8px;'>").append(String.format("%.1f", min))
                        .append("°C</td>")
                        .append("<td style='border:1px solid #ccc; padding:8px;'>").append(String.format("%.1f", max))
                        .append("°C</td>")
                        .append("</tr>");
            }
            sb.append("</tbody></table>");
            sb.append("</section>");
        }

        return sb.toString();
    }
}
