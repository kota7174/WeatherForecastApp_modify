import java.util.Random;

public class WeatherMethodlist {
    // 星座占い
    public static void printWeatherUranai() {
        System.out.println("今日の運勢");
        int list[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list[i][j] = -1;
            }
        }
        for (int i = 0; i < 3; i++) {
            Random rnd = new Random();
            int numcon = rnd.nextInt(12);
            int numitem = rnd.nextInt(50);
            int numcolor = rnd.nextInt(20);
            while (list[0][0] == numcon || list[1][0] == numcon) {
                numcon = ((numcon + numcon) / 4) % 12;
            }
            while (list[0][1] == numitem || list[1][1] == numitem) {
                numitem = ((numitem + numitem) / 4) % 50;
            }
            while (list[0][2] == numcolor || list[1][2] == numcolor) {
                numcolor = ((numcolor + numcolor) / 4) % 20;
            }
            System.out.println("第" + (i + 1) + "位は" + "「" + constellation[numcon] + "」\n"
                    + "　　ラッキーアイテムは" + "「" + luckyitem[numitem] + "」\n"
                    + "　　ラッキーカラーは" + "「" + luckycolor[numcolor] + "」");
            System.out.println();
            list[i][0] = numcon;
            list[i][1] = numitem;
            list[i][2] = numcolor;
        }
    }

    // 天気情報
    public static String printWeather(int code) {
        return switch (code) {
            case 0 -> "快晴";
            case 1, 2, 3 -> "曇り";
            case 45, 48 -> "霧";
            case 51, 53, 55 -> "霧雨";
            case 56, 57 -> "凍る霧雨";
            case 61, 63, 65 -> "雨";
            case 66, 67 -> "凍る雨";
            case 71, 73, 75 -> "雪";
            case 77 -> "雪の粒";
            case 80, 81, 82 -> "にわか雨";
            case 85, 86 -> "にわか雪";
            case 95 -> "雷雨";
            case 96, 99 -> "雷雨（ひょう）";
            default -> "不明";
        };
    }

    // 天気画像取得
    public static String getWeatherImagePath(String weather) {
        switch (weather) {
            case "快晴":
                return "image/sunny.png";
            case "曇り":
                return "image/cloudy.png";
            case "雨":
                return "image/rain.png";
            case "雪":
                return "image/snow.png";
            case "雷雨":
                return "image/thunder.png";
            default:
                return null;
        }
    }

    // 星座画像取得
    public static String getSeizaImageFile(String seiza) {
        String fileName;
        switch (seiza) {
            case "おひつじ":
                fileName = "ohituji";
                break;
            case "おうし":
                fileName = "ousi";
                break;
            case "ふたご":
                fileName = "hutago";
                break;
            case "かに":
                fileName = "kani";
                break;
            case "しし":
                fileName = "sisi";
                break;
            case "おとめ":
                fileName = "otome";
                break;
            case "てんびん":
                fileName = "tenbin";
                break;
            case "さそり":
                fileName = "sasori";
                break;
            case "いて":
                fileName = "ite";
                break;
            case "やぎ":
                fileName = "yagi";
                break;
            case "みずがめ":
                fileName = "mizugame";
                break;
            case "うお":
                fileName = "uo";
                break;
            default:
                return null;
        }
        String path = "image/星座/" + fileName + ".png";
        java.io.File file = new java.io.File(path);
        if (file.exists()) {
            return path;
        }
        return null;
    }

    // 都道府県画像取得
    public static String getPrefImagePathByData(WeatherData data) {
        String name = data.getName();
        String fileName;
        switch (name) {
            case "北海道":
                fileName = "hokkaido";
                break;
            case "青森":
                fileName = "aomori";
                break;
            case "岩手":
                fileName = "iwate";
                break;
            case "宮城":
                fileName = "miyagi";
                break;
            case "秋田":
                fileName = "akita";
                break;
            case "山形":
                fileName = "yamagata";
                break;
            case "福島":
                fileName = "fukushima";
                break;
            case "茨城":
                fileName = "ibaraki";
                break;
            case "栃木":
                fileName = "tochigi";
                break;
            case "群馬":
                fileName = "gumma";
                break;
            case "埼玉":
                fileName = "saitama";
                break;
            case "千葉":
                fileName = "chiba";
                break;
            case "東京":
                fileName = "tokyo";
                break;
            case "神奈川":
                fileName = "kanagawa";
                break;
            case "新潟":
                fileName = "niigata";
                break;
            case "富山":
                fileName = "toyama";
                break;
            case "石川":
                fileName = "ishikawa";
                break;
            case "福井":
                fileName = "fukui";
                break;
            case "山梨":
                fileName = "yamanashi";
                break;
            case "長野":
                fileName = "nagano";
                break;
            case "岐阜":
                fileName = "gifu";
                break;
            case "静岡":
                fileName = "shizuoka";
                break;
            case "愛知":
                fileName = "aichi";
                break;
            case "三重":
                fileName = "mie";
                break;
            case "滋賀":
                fileName = "shiga";
                break;
            case "京都":
                fileName = "kyoto";
                break;
            case "大阪":
                fileName = "osaka";
                break;
            case "兵庫":
                fileName = "hyogo";
                break;
            case "奈良":
                fileName = "nara";
                break;
            case "和歌山":
                fileName = "wakayama";
                break;
            case "鳥取":
                fileName = "tottori";
                break;
            case "島根":
                fileName = "shimane";
                break;
            case "岡山":
                fileName = "okayama";
                break;
            case "広島":
                fileName = "hiroshima";
                break;
            case "山口":
                fileName = "yamaguchi";
                break;
            case "徳島":
                fileName = "tokushima";
                break;
            case "香川":
                fileName = "kagawa";
                break;
            case "愛媛":
                fileName = "ehime";
                break;
            case "高知":
                fileName = "kochi";
                break;
            case "福岡":
                fileName = "fukuoka";
                break;
            case "佐賀":
                fileName = "saga";
                break;
            case "長崎":
                fileName = "nagasaki";
                break;
            case "熊本":
                fileName = "kumamoto";
                break;
            case "大分":
                fileName = "oita";
                break;
            case "宮崎":
                fileName = "miyazaki";
                break;
            case "鹿児島":
                fileName = "kagoshima";
                break;
            case "沖縄":
                fileName = "okinawa";
                break;
            default:
                fileName = name;
                break;
        }
        String path = "image/都道府県/" + fileName + ".png";
        java.io.File file = new java.io.File(path);
        if (file.exists())
            return path;
        // 旧ロジックの分岐も含めて画像がなければnullを返す
        // 例外的な県画像（jpg, webp）
        if ("岐阜".equals(name)) {
            String alt = "image/岐阜県.jpg";
            if (new java.io.File(alt).exists())
                return alt;
        } else if ("静岡".equals(name)) {
            String alt = "image/静岡県.webp";
            if (new java.io.File(alt).exists())
                return alt;
        }
        // デフォルト
        String png = "image/" + name + ".png";
        if (new java.io.File(png).exists())
            return png;
        return null;
    }
}
