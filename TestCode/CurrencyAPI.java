import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyAPI {

    private static final String[] baseUrlsArr = {
            "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/",
            "https://raw.githubusercontent.com/fawazahmed0/currency-api/1/"
    };

    private static final String[] suffixArr = {".min.json", ".json"};

    public static void main(String[] args) {
        String curr1 = "USD";
        String curr2 = "EUR";

        double rate = getTodayRate(curr1, curr2);
        if (rate != -1) {
            System.out.println("Conversion rate from " + curr1 + " to " + curr2 + ": " + rate);
        } else {
            System.out.println("Rate not found!");
        }
    }

    private static double getRate(String curr1, String curr2, String date) {
        curr1 = curr1.toLowerCase();
        curr2 = curr2.toLowerCase();
        for (String baseUrl : baseUrlsArr) {
            for (String suff : suffixArr) {
                String urlString = baseUrl + date + "/currencies/" + curr1 + "/" + curr2 + suff;
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if (jsonObject.has(curr2)) {
                                return jsonObject.getDouble(curr2);
                            } else {
                                return -1; // Rate not found
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1; // Rate not found
    }

    private static double getTodayRate(String curr1, String curr2) {
        return getRate(curr1, curr2, "latest");
    }
}
