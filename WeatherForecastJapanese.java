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
        // --- Swingウィンドウ初期化 ---
        JFrame frame = new JFrame("日本の天気予報");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700); // サイズ拡大
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 250, 255));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setBackground(new Color(245, 250, 255));
        frame.add(scrollPane);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.setBackground(new Color(200, 220, 255));
        // --- どの画面からでも天気予報を表示できる都道府県選択 ---
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

        JPanel globalPanel = new JPanel();
        globalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        globalPanel.setBackground(new Color(200, 220, 255));
        // --- 間隔を少し開けるためにスペースを追加 ---
        globalPanel.add(homeBtn);
        globalPanel.add(Box.createHorizontalStrut(24));
        globalPanel.add(globalSearchLabel);
        globalPanel.add(globalPrefCombo);
        globalPanel.add(globalSearchBtn);
        // frame.add(globalPanel, BorderLayout.NORTH); // ← ここをコメントアウト

        // --- 下部ボタンパネルは削除または不要 ---
        // frame.add(buttonPanel, BorderLayout.SOUTH);

        globalSearchBtn.addActionListener(e -> {
            // ホーム画面（showHome）が表示されている場合は何もしない
            if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JPanel) {
                JPanel firstPanel = (JPanel) panel.getComponent(0);
                if (firstPanel.getComponentCount() > 0 && firstPanel.getComponent(0) instanceof JPanel) {
                    JPanel leftPanel = (JPanel) firstPanel.getComponent(0);
                    if (leftPanel.getComponentCount() > 2 && leftPanel.getComponent(2) instanceof JPanel) {
                        JPanel searchPanel = (JPanel) leftPanel.getComponent(2);
                        if (searchPanel.getComponentCount() > 1 && searchPanel.getComponent(1) instanceof JComboBox) {
                            JComboBox<?> combo = (JComboBox<?>) searchPanel.getComponent(1);
                            if (combo == globalPrefCombo) {
                                // ホーム画面の都道府県天気予報は無効化
                                return;
                            }
                        }
                    }
                }
            }
            String selected = (String) globalPrefCombo.getSelectedItem();
            WeatherData found = PREFS.stream().filter(d -> d.getName().equals(selected)).findFirst().orElse(null);
            if (found != null) {
                String imagePath = WeatherMethodlist.getPrefImagePathByData(found);
                // --- アニメーション付き天気表示（中央寄せ） ---
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

                        // 都道府県選択UI（再帰的に同じものを追加）
                        // --- 「他の都道府県を選択」を削除 ---
                        // JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        // selectPanel.setOpaque(false);
                        // JLabel selectLabel = new JLabel("他の都道府県を選択: ");
                        // selectLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
                        // selectLabel.setForeground(new Color(0, 80, 180));
                        // JComboBox<String> prefCombo2 = new JComboBox<>(globalPrefNames);
                        // prefCombo2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
                        // prefCombo2.setSelectedItem(found.getName());
                        // JButton showBtn = new JButton("表示");
                        // showBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
                        // showBtn.setBackground(new Color(120, 180, 255));
                        // showBtn.setForeground(Color.WHITE);
                        // showBtn.setFocusPainted(false);
                        // showBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        // selectPanel.add(selectLabel);
                        // selectPanel.add(prefCombo2);
                        // selectPanel.add(showBtn);
                        // weatherPanel.add(selectPanel);

                        // showBtn.addActionListener(ev2 -> {
                        //     globalPrefCombo.setSelectedItem(prefCombo2.getSelectedItem());
                        //     globalSearchBtn.doClick();
                        // });

                        // --- 天気予報画面のデザインを強化 ---
                        JPanel weatherCard = new JPanel();
                        weatherCard.setLayout(new BoxLayout(weatherCard, BoxLayout.Y_AXIS));
                        weatherCard.setBackground(new Color(230, 245, 255, 220));
                        weatherCard.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(120, 180, 255), 3, true),
                            BorderFactory.createEmptyBorder(30, 40, 30, 40)
                        ));
                        weatherCard.setAlignmentX(Component.CENTER_ALIGNMENT);

                        // タイトル
                        JLabel title = new JLabel( found.getName() + "の天気予報 ");
                        title.setFont(new Font("Yu Gothic UI", Font.BOLD, 38));
                        title.setForeground(new Color(0, 90, 180));
                        title.setAlignmentX(Component.CENTER_ALIGNMENT);
                        weatherCard.add(title);
                        weatherCard.add(Box.createVerticalStrut(18));

                        // 都道府県画像
                        String prefImg = WeatherMethodlist.getPrefImagePathByData(found);
                        if (prefImg != null) {
                            ImageIcon icon = new ImageIcon(prefImg);
                            int imgW = 180, imgH = icon.getIconHeight() * imgW / icon.getIconWidth(); // ← サイズを大きく
                            Image scaled = icon.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
                            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
                            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            weatherCard.add(imgLabel);
                            weatherCard.add(Box.createVerticalStrut(10));
                        }

                        // 天気データ
                        try {
                            WeatherApiClient client = new WeatherApiClient();
                            org.json.JSONObject json = client.getWeatherForecast(found, 3);
                            org.json.JSONObject dailyObj = json.getJSONObject("daily");
                            org.json.JSONArray dates = dailyObj.getJSONArray("time");
                            org.json.JSONArray weatherCodes = dailyObj.getJSONArray("weathercode");
                            org.json.JSONArray tempsMax = dailyObj.getJSONArray("temperature_2m_max");
                            org.json.JSONArray tempsMin = dailyObj.getJSONArray("temperature_2m_min");
                            org.json.JSONArray precipitation = dailyObj.optJSONArray("precipitation_sum");
                            org.json.JSONArray uvIndex = dailyObj.optJSONArray("uv_index_max");
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
                                String weatherImagePath = WeatherMethodlist.getWeatherImagePath(weather);
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

                                // 天気アイコン
                                if (weatherImagePath != null) {
                                    ImageIcon wicon = new ImageIcon(weatherImagePath);
                                    int w = 48, h = wicon.getIconHeight() * w / wicon.getIconWidth();
                                    Image wimg = wicon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                                    JLabel wlabel = new JLabel(new ImageIcon(wimg));
                                    row.add(wlabel);
                                    row.add(Box.createHorizontalStrut(8));
                                }

                                // 天気名
                                JLabel weatherLabel = new JLabel(weather);
                                weatherLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 26));
                                weatherLabel.setForeground(new Color(0, 120, 220));
                                row.add(weatherLabel);
                                row.add(Box.createHorizontalStrut(18));

                                // 気温
                                JLabel tempLabel = new JLabel(String.format(" %.1f℃ / %.1f℃", min, max));
                                tempLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
                                tempLabel.setForeground(new Color(30, 30, 30));
                                row.add(tempLabel);

                                // 降水量
                                if (!precipStr.isEmpty()) {
                                    row.add(Box.createHorizontalStrut(14));
                                    JLabel pLabel = new JLabel(precipStr);
                                    pLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
                                    pLabel.setForeground(new Color(0, 120, 180));
                                    row.add(pLabel);
                                }

                                // UV
                                if (!uvStr.isEmpty()) {
                                    row.add(Box.createHorizontalStrut(14));
                                    JLabel uvLabel = new JLabel(uvStr);
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

                        weatherPanel.add(weatherCard);
                        // --- デザインここまで ---

                        centerPanel.add(weatherPanel);
                        centerPanel.add(Box.createHorizontalGlue());
                        outerPanel.add(centerPanel);
                        outerPanel.add(Box.createVerticalGlue());
                        panel.add(outerPanel);
                        panel.revalidate();
                        panel.repaint();
                    }
                });
                timer.start();
            }
        });

        // --- ホーム画面表示メソッド ---
        Runnable showHome = () -> {
            panel.removeAll();
            // 日本地図画像を右側に表示するパネル
            JPanel homeContentPanel = new JPanel();
            homeContentPanel.setLayout(new BorderLayout());
            homeContentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            // 左側: タイトルや占いなど
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setOpaque(false);
            leftPanel.add(Box.createVerticalStrut(10));
            JLabel titleLabel = new JLabel("今日のトレーニングコンディションは晴れのちパワーアップ！");
            titleLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 48));
            titleLabel.setForeground(new Color(0, 120, 220));
            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            leftPanel.add(titleLabel);
            leftPanel.add(Box.createVerticalStrut(20));

            // --- ホーム画面で都道府県天気予報パネルを上部に追加 ---
            if (globalPanel.getParent() != frame) {
                frame.add(globalPanel, BorderLayout.NORTH);
                frame.revalidate();
            }

            // --- 占い ---
            String uranaiResult;
            try {
                uranaiResult = WeatherMethodlist.printUranai();
            } catch (Exception ex) {
                uranaiResult = "占い結果の取得に失敗しました";
            }
            JPanel uranaiPanel = new JPanel();
            uranaiPanel.setLayout(new BoxLayout(uranaiPanel, BoxLayout.Y_AXIS));
            uranaiPanel.setOpaque(false);
            String[] lines = uranaiResult.split("\n");
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
            leftPanel.add(uranaiPanel);
            leftPanel.add(Box.createVerticalStrut(40));

            // --- 占いの右に筋肉画像を配置 ---
            JPanel rightPanel = new JPanel();
            rightPanel.setOpaque(false);
            rightPanel.setLayout(new BorderLayout());
            WeatherData.imageUtil.addImage(rightPanel, "image/cd1f65b4-88c3-4750-b086-913bc4a34136.jpg", 340);

            JPanel musclePanel = new JPanel();
            musclePanel.setOpaque(false);
            musclePanel.setLayout(null); // ← レイアウトをnullにして自由配置
            // 画像を拡大し、左上に配置
            ImageIcon muscleIcon = new ImageIcon("image/muscle_photo.png");
            int width = 340; // 拡大サイズ
            int height = muscleIcon.getIconHeight() * width / muscleIcon.getIconWidth();
            Image scaled = muscleIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JLabel muscleLabel = new JLabel(new ImageIcon(scaled));
            muscleLabel.setBounds(0, 0, width, height); // 左上に配置
            musclePanel.setPreferredSize(new Dimension(width, height + 20));
            musclePanel.add(muscleLabel);
            rightPanel.add(musclePanel, BorderLayout.NORTH);
            // --- ここまで追加 ---

            homeContentPanel.add(leftPanel, BorderLayout.CENTER);
            homeContentPanel.add(rightPanel, BorderLayout.EAST);
            panel.add(homeContentPanel);
            panel.revalidate();
            panel.repaint();
        };
        showHome.run();

        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // --- アニメーション付きホーム画面遷移 ---
                JPanel glass = new JPanel();
                glass.setOpaque(true);
                glass.setBackground(new Color(245, 250, 255, 0));
                glass.setBounds(0, 0, panel.getWidth(), panel.getHeight());
                panel.setLayout(null);
                panel.add(glass, 0);
                panel.revalidate();
                panel.repaint();

                Timer timer = new Timer(30, null);
                timer.addActionListener(e -> {
                    int alpha = glass.getBackground().getAlpha();
                    if (alpha < 220) {
                        glass.setBackground(new Color(245, 250, 255, Math.min(alpha + 15, 255)));
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
        });

        // --- 県ごと画像ボタン削除済み ---
        frame.setVisible(true);

        // --- ユーティリティクラスをstaticにしてmain内で直接newせず使えるようにする ---
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
