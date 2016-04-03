package weathergw.dal;

import weathergw.domain.WeatherInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.WRITE;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * Created by lfalcao on 11/03/16.
 */
public class WeatherInfoServiceSupplier extends WeatherInfoCsvSupplier implements Supplier<Collection<WeatherInfo>> {


    public WeatherInfoServiceSupplier(String url) {
        super(url);
    }

    protected List<String> readFile() {
        try {
            URL url = new URL(location);           
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));              
          
            int start = location.indexOf("&q=");
            String t = location.substring(start+3);
            String filename = t.split("&")[0]+"-data.csv";
            
            List<String> lines = reader.lines().collect(toList());            
            Path path = Paths.get(ClassLoader.getSystemResource(filename).toURI());          
            Files.write(path, lines, new OpenOption[] { WRITE, CREATE_NEW });            
            return lines;          
        } catch (IOException | URISyntaxException e) {
            // TODO Should log to some log mechanism
            e.printStackTrace();
            return null;
        }
    }
}
