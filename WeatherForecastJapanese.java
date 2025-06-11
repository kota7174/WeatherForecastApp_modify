import java.util.List;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class WeatherForecastJapanese {
    // 47都道府県のリスト
    private static final List<WeatherData> PREFS = List.of(
            new WeatherData("北海道", 43.0642, 141.3469), new WeatherData("青森", 40.8244, 140.74),
            new WeatherData("岩手", 39.7036, 141.1527), new WeatherData("宮城", 38.2688, 140.8721),
            new WeatherData("秋田", 39.7186, 140.1024), new WeatherData("山形", 38.2404, 140.3633),
            new WeatherData("福島", 37.7503, 140.4675), new WeatherData("茨城", 36.3418, 140.4468),
            new WeatherData("栃木", 36.5657, 139.8836), new WeatherData("群馬", 36.3912, 139.0609),
            new WeatherData("埼玉", 35.8569, 139.6489), new WeatherData("千葉", 35.6046, 140.1233),
            new WeatherData("東京", 35.6895, 139.6917), new WeatherData("神奈川", 35.4478, 139.6425),
            new WeatherData("新潟", 37.9026, 139.0232), new WeatherData("富山", 36.6953, 137.2113),
            new WeatherData("石川", 36.5947, 136.6256), new WeatherData("福井", 36.0652, 136.2216),
            new WeatherData("山梨", 35.6642, 138.5684), new WeatherData("長野", 36.6513, 138.181),
            new WeatherData("岐阜", 35.4233, 136.7606), new WeatherData("静岡", 34.9756, 138.3828),
            new WeatherData("愛知", 35.1815, 136.9066), new WeatherData("三重", 34.7303, 136.5086),
            new WeatherData("滋賀", 35.0045, 135.8686), new WeatherData("京都", 35.0214, 135.7556),
            new WeatherData("大阪", 34.6863, 135.52), new WeatherData("兵庫", 34.6913, 135.183),
            new WeatherData("奈良", 34.6851, 135.8048), new WeatherData("和歌山", 34.226, 135.1675),
            new WeatherData("鳥取", 35.5039, 134.2377), new WeatherData("島根", 35.4723, 133.0505),
            new WeatherData("岡山", 34.6618, 133.9344), new WeatherData("広島", 34.3963, 132.4596),
            new WeatherData("山口", 34.1859, 131.4714), new WeatherData("徳島", 34.0658, 134.5593),
            new WeatherData("香川", 34.3401, 134.0434), new WeatherData("愛媛", 33.8417, 132.7661),
            new WeatherData("高知", 33.5597, 133.5311), new WeatherData("福岡", 33.5902, 130.4017),
            new WeatherData("佐賀", 33.2494, 130.2988), new WeatherData("長崎", 32.7448, 129.8737),
            new WeatherData("熊本", 32.7898, 130.7417), new WeatherData("大分", 33.2382, 131.6126),
            new WeatherData("宮崎", 31.9111, 131.4239), new WeatherData("鹿児島", 31.5602, 130.5581),
            new WeatherData("沖縄", 26.2124, 127.6809));

    public static void main(String[] args) throws IOException, InterruptedException {
        // メインウィンドウの作成・全画面化
        JFrame frame = new JFrame("日本の天気予報");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 全画面
        frame.setUndecorated(false); // タイトルバーあり
        frame.setLocationRelativeTo(null);

        // メインパネル（天気やホーム画面を表示する領域）
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 250, 255));
        frame.add(new JScrollPane(panel));

        // 上部パネル（ホームボタン＋都道府県選択）
        JButton homeBtn = new JButton("ホームに戻る");
        homeBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 36));
        homeBtn.setBackground(new Color(120, 180, 255));
        homeBtn.setForeground(Color.WHITE);
        homeBtn.setFocusPainted(false);
        homeBtn.setBorder(BorderFactory.createEmptyBorder(16, 36, 16, 36));
        homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel globalSearchLabel = new JLabel("都道府県天気予報: ");
        globalSearchLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
        globalSearchLabel.setForeground(new Color(0, 80, 180));
        String[] globalPrefNames = PREFS.stream().map(WeatherData::getName).toArray(String[]::new);
        JComboBox<String> globalPrefCombo = new JComboBox<>(globalPrefNames);
        globalPrefCombo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        JButton globalSearchBtn = new JButton("表示");
        globalSearchBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        globalSearchBtn.setBackground(new Color(120, 180, 255));
        globalSearchBtn.setForeground(Color.WHITE);
        globalSearchBtn.setFocusPainted(false);
        globalSearchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel globalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        globalPanel.setBackground(new Color(200, 220, 255));
        globalPanel.add(homeBtn);
        globalPanel.add(Box.createHorizontalStrut(24));
        globalPanel.add(globalSearchLabel);
        globalPanel.add(globalPrefCombo);
        globalPanel.add(globalSearchBtn);

        // ホーム画面表示用Runnable
        Runnable showHome = () -> {
            panel.removeAll();

            // 外側パネル（中央寄せ）
            JPanel outerPanel = new JPanel();
            outerPanel.setOpaque(false);
            outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
            outerPanel.add(Box.createVerticalGlue());

            // 中央寄せパネル
            JPanel centerPanel = new JPanel();
            centerPanel.setOpaque(false);
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
            centerPanel.add(Box.createHorizontalGlue());

            // ホーム画面のカード（枠＋背景色）
            JPanel homeCard = createHomeCard();
            centerPanel.add(homeCard);
            centerPanel.add(Box.createHorizontalGlue());
            outerPanel.add(centerPanel);
            outerPanel.add(Box.createVerticalGlue());
            panel.add(outerPanel);
            panel.revalidate();
            panel.repaint();

            // 上部パネルがなければ追加
            if (globalPanel.getParent() != frame) {
                frame.add(globalPanel, BorderLayout.NORTH);
                frame.revalidate();
            }
        };
        showHome.run();

        // 天気予報表示（都道府県選択時）
        globalSearchBtn.addActionListener(e -> {
            String selected = (String) globalPrefCombo.getSelectedItem();
            WeatherData found = PREFS.stream().filter(d -> d.getName().equals(selected)).findFirst().orElse(null);
            if (found != null) {
                showWeatherWithFade(panel, found, globalPanel, frame);
            }
        });

        // ホームボタン（フェードインアニメーションでホーム画面へ）
        homeBtn.addActionListener(evt -> showHomeWithFade(panel, showHome));

        frame.setVisible(true);

        // --- マッチョ画像を右下に表示 ---
        String machoImg = "image/マッチョ/アナウンサー.png";
        if (new java.io.File(machoImg).exists()) {
            ImageIcon machoIcon = new ImageIcon(machoImg);
            int imgW = 400, imgH = machoIcon.getIconHeight() * imgW / machoIcon.getIconWidth();
            Image scaledMacho = machoIcon.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
            JLabel machoLabel = new JLabel(new ImageIcon(scaledMacho));
            machoLabel.setSize(imgW, imgH);
            machoLabel.setOpaque(false);
            machoLabel.setVisible(true);
            // レイヤードペインに追加
            JLayeredPane layeredPane = frame.getLayeredPane();
            layeredPane.add(machoLabel, JLayeredPane.PALETTE_LAYER);
            // 右下に配置するメソッド
            Runnable updateMachoPosition = () -> {
                int x = frame.getWidth() - imgW - 100;
                int y = frame.getHeight() - imgH - 20;
                machoLabel.setLocation(Math.max(x, 0), Math.max(y, 0));
            };
            frame.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent e) {updateMachoPosition.run();}
                public void componentMoved(java.awt.event.ComponentEvent e) {updateMachoPosition.run();}
                public void componentShown(java.awt.event.ComponentEvent e) {updateMachoPosition.run();}
            });
            updateMachoPosition.run();
        }
    }

    // --- ホーム画面カード生成 ---
    private static JPanel createHomeCard() {
        JPanel homeCard = new JPanel();
        homeCard.setLayout(new BoxLayout(homeCard, BoxLayout.Y_AXIS));
        homeCard.setBackground(new Color(230, 245, 255, 220));
        homeCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 180, 255), 3, true),
                BorderFactory.createEmptyBorder(50, 70, 50, 70)));
        homeCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // タイトル
        JLabel titleLabel = new JLabel("今日のトレーニングコンディションは晴れのちパワーアップ！");
        titleLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 38));
        titleLabel.setForeground(new Color(255, 50, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeCard.add(titleLabel);
        homeCard.add(Box.createVerticalStrut(18));

        // 日本地図画像（存在する場合のみ）
        String japanMap = "image/cd1f65b4-88c3-4750-b086-913bc4a34136.jpg";
        if (new java.io.File(japanMap).exists()) {
            ImageIcon icon = new ImageIcon(japanMap);
            int imgW = 180, imgH = icon.getIconHeight() * imgW / icon.getIconWidth();
            Image scaled = icon.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            homeCard.add(imgLabel);
            homeCard.add(Box.createVerticalStrut(10));
        }

        // 占いパネル
        JPanel uranaiPanel = new JPanel();
        uranaiPanel.setLayout(new BoxLayout(uranaiPanel, BoxLayout.Y_AXIS));
        uranaiPanel.setOpaque(false);
        try {
            String[] lines = WeatherMethodlist.printUranai().split("\n");
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                if (line.startsWith("第") && line.contains("は「") && line.contains("座")) {
                    String seiza = line.substring(line.indexOf("「") + 1, line.indexOf("座"));
                    String imageFile = WeatherMethodlist.getSeizaImageFile(seiza);
                    JPanel row = WeatherData.uranaiUtil.createUranaiRow(line, imageFile);
                    uranaiPanel.add(row);
                    if (i + 2 < lines.length) {
                        uranaiPanel.add(WeatherData.uranaiUtil.createUranaiSubRow(lines[i + 1], lines[i + 2]));
                        i += 2;
                    }
                } else {
                    addLabel(uranaiPanel, line, 22, false, new Color(120, 80, 180));
                }
            }
        } catch (Exception ex) {
            addLabel(uranaiPanel, "占い結果の取得に失敗しました", 22, false, Color.RED);
        }
        homeCard.add(uranaiPanel);
        homeCard.add(Box.createVerticalStrut(40));
        return homeCard;
    }

    // --- 天気予報画面表示（フェードイン） ---
    private static void showWeatherWithFade(JPanel panel, WeatherData found, JPanel globalPanel, JFrame frame) {
        // フェード用パネル
        JPanel glass = new JPanel();
        glass.setOpaque(true);
        glass.setBackground(new Color(245, 250, 255, 0));
        glass.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.setLayout(null);
        panel.add(glass, 0);
        panel.revalidate();
        panel.repaint();

        Timer timer = new Timer(15, null);
        timer.addActionListener(ev -> {
            int alpha = glass.getBackground().getAlpha();
            if (alpha < 220) {
                glass.setBackground(new Color(245, 250, 255, Math.min(alpha + 30, 255)));
                glass.repaint();
            } else {
                timer.stop();
                panel.removeAll();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                // 天気予報画面のレイアウト
                JPanel outerPanel = new JPanel();
                outerPanel.setOpaque(false);
                outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
                outerPanel.add(Box.createVerticalGlue());

                JPanel centerPanel = new JPanel();
                centerPanel.setOpaque(false);
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
                centerPanel.add(Box.createHorizontalGlue());

                JPanel weatherPanel = new JPanel();
                weatherPanel.setOpaque(false);
                weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
                weatherPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JPanel weatherCard = createWeatherCard(found);
                weatherPanel.add(weatherCard);

                centerPanel.add(weatherPanel);
                centerPanel.add(Box.createHorizontalGlue());
                outerPanel.add(centerPanel);
                outerPanel.add(Box.createVerticalGlue());
                panel.add(outerPanel);
                panel.revalidate();
                panel.repaint();

                // 天気予報画面では上部パネルを消す
                if (globalPanel.getParent() == frame) {
                    frame.remove(globalPanel);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
        timer.start();
    }

    // --- 天気カード生成 ---
    private static JPanel createWeatherCard(WeatherData found) {
        JPanel weatherCard = new JPanel();
        weatherCard.setLayout(new BoxLayout(weatherCard, BoxLayout.Y_AXIS));
        weatherCard.setBackground(new Color(230, 245, 255, 220));
        weatherCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 180, 255), 3, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        weatherCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // タイトル
        JLabel title = new JLabel(found.getName() + "の天気予報 ");
        title.setFont(new Font("Yu Gothic UI", Font.BOLD, 38));
        title.setForeground(new Color(0, 90, 180));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        weatherCard.add(title);
        weatherCard.add(Box.createVerticalStrut(18));

        // 都道府県画像
        String prefImg = WeatherMethodlist.getPrefImagePathByData(found);
        if (prefImg != null) {
            ImageIcon icon = new ImageIcon(prefImg);
            int imgW = 180, imgH = icon.getIconHeight() * imgW / icon.getIconWidth();
            Image scaled = icon.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            weatherCard.add(imgLabel);
            weatherCard.add(Box.createVerticalStrut(10));
        }

        try {
            WeatherApiClient client = new WeatherApiClient();
            var dailyObj = client.getWeatherForecast(found, 3).getJSONObject("daily");
            var dates = dailyObj.getJSONArray("time");
            var weatherCodes = dailyObj.getJSONArray("weathercode");
            var tempsMax = dailyObj.getJSONArray("temperature_2m_max");
            var tempsMin = dailyObj.getJSONArray("temperature_2m_min");
            var precipitation = dailyObj.optJSONArray("precipitation_sum");
            var uvIndex = dailyObj.optJSONArray("uv_index_max");
            for (int d = 0; d < dates.length(); d++) {
                String date = java.time.LocalDate.parse(dates.getString(d)).toString();
                int code = weatherCodes.getInt(d);
                double max = tempsMax.getDouble(d), min = tempsMin.getDouble(d);
                String weather = WeatherMethodlist.printWeather(code);
                String precipStr = (precipitation != null && d < precipitation.length())
                        ? String.format(" %.1fmm", precipitation.getDouble(d))
                        : "";
                String uvStr = (uvIndex != null && d < uvIndex.length())
                        ? String.format("☀️ UV指数 %.1f", uvIndex.getDouble(d))
                        : "";

                JPanel row = new JPanel();
                row.setOpaque(false);
                row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
                row.setAlignmentX(Component.CENTER_ALIGNMENT);

                // 日付
                JLabel dateLabel = new JLabel(date);
                dateLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 26));
                dateLabel.setForeground(new Color(80, 80, 120));
                row.add(dateLabel);
                row.add(Box.createHorizontalStrut(18));

                // 天気名
                JLabel weatherLabel = new JLabel(weather);
                weatherLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 26));
                weatherLabel.setForeground(new Color(0, 120, 220));
                row.add(weatherLabel);
                row.add(Box.createHorizontalStrut(18));

                // 気温
                JLabel tempLabel = new JLabel(String.format("  最低 %.1f℃ / 最高 %.1f℃", min, max));
                tempLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
                tempLabel.setForeground(new Color(30, 30, 30));
                row.add(tempLabel);

                // 降水量
                if (!precipStr.isEmpty()) {
                    row.add(Box.createHorizontalStrut(14));
                    JLabel pLabel = new JLabel("降水量" + precipStr.replace("mm", "mm（1日合計）"));
                    pLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
                    pLabel.setForeground(new Color(0, 120, 180));
                    row.add(pLabel);
                }

                // UV
                if (!uvStr.isEmpty()) {
                    row.add(Box.createHorizontalStrut(14));
                    JLabel uvLabel = new JLabel(uvStr + "（最大値）");
                    uvLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
                    uvLabel.setForeground(new Color(180, 120, 0));
                    row.add(uvLabel);
                }

                weatherCard.add(Box.createVerticalStrut(8));
                weatherCard.add(row);
            }
        } catch (Exception ex) {
            JLabel errorLabel = new JLabel("天気情報の取得に失敗しました: " + ex.getMessage());
            errorLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 26));
            errorLabel.setForeground(Color.RED);
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            weatherCard.add(errorLabel);
        }

        weatherCard.add(Box.createVerticalStrut(16));
        JLabel advice = new JLabel("良い一日をお過ごしください！");
        advice.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
        advice.setForeground(new Color(80, 160, 80));
        advice.setAlignmentX(Component.CENTER_ALIGNMENT);
        weatherCard.add(advice);

        return weatherCard;
    }

    // --- ホーム画面フェードイン ---
    private static void showHomeWithFade(JPanel panel, Runnable showHome) {
        JPanel glass = new JPanel();
        glass.setOpaque(true);
        glass.setBackground(new Color(245, 250, 255, 0));
        glass.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.setLayout(null);
        panel.add(glass, 0);
        panel.revalidate();
        panel.repaint();

        Timer timer = new Timer(15, null);
        timer.addActionListener(e -> {
            int alpha = glass.getBackground().getAlpha();
            if (alpha < 220) {
                glass.setBackground(new Color(245, 250, 255, Math.min(alpha + 30, 255)));
                glass.repaint();
            } else {
                timer.stop();
                panel.removeAll();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                showHome.run();
                panel.revalidate();
                panel.repaint();
            }
        });
        timer.start();
    }

    // --- ラベル追加用メソッド ---
    static void addLabel(JPanel panel, String text, int fontSize, boolean bold) {
        addLabel(panel, text, fontSize, bold, new java.awt.Color(30, 30, 30), false);
    }

    static void addLabel(JPanel panel, String text, int fontSize, boolean bold, java.awt.Color color) {
        addLabel(panel, text, fontSize, bold, color, false);
    }

    static void addLabel(JPanel panel, String text, int fontSize, boolean bold, java.awt.Color color, boolean italic) {
        JLabel label = new JLabel(text);
        label.setFont(new java.awt.Font("Yu Gothic UI",
                italic ? java.awt.Font.ITALIC : (bold ? java.awt.Font.BOLD : java.awt.Font.PLAIN), fontSize + 10));
        label.setForeground(color);
        label.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(label);
    }
}
