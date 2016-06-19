package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

/**
 * Created by hmr on 11/06/2016.
 */
public class HttpGet {

    public static final String API_KEY = "e6be69e445de45ff88f5218c60251f27";

    public static <T> T  getFromUri(String urlStr, Function<String, T> converter) {
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("X-Auth-Token", API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return converter.apply(reader.lines().collect(joining()));
        } catch (IOException e) {
            return null;
        }

    }
}
