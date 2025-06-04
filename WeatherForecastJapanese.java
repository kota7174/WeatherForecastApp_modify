import java.util.List;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class WeatherForecastJapanese {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<WeatherData> datas = List.of(
                new WeatherData("岐阜", 35.4233, 136.7606),
                new WeatherData("静岡", 34.9756, 138.3828),
                new WeatherData("愛知", 35.1815, 136.9066),
                new WeatherData("三重", 34.7303, 136.5086));

        WeatherApiClient client = new WeatherApiClient();
        String html = WeatherResultPrinter.getWeatherForecastsHtml(datas, client)
                + WeatherMethodlist.getWeatherUranaiHtml();

        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("天気予報");
            JEditorPane editorPane = new JEditorPane();
            editorPane.setContentType("text/html");
            editorPane.setEditable(false);
            editorPane
                    .setText("<html><body style='font-family:sans-serif; background:#f7fafd; margin:0; padding:24px;'>"
                            + html + "</body></html>");
            frame.getContentPane().add(new JScrollPane(editorPane));
            frame.setSize(700, 800);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}