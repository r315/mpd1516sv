package footballapi;

import com.google.gson.Gson;
import footballapi.dto.SoccerSeasonDto;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.FootBallApi.soccerSeason;

/**
 * Created by hmr on 08/06/2016.
 */
public class DtosTest {



    private static <T> T  getFromUri(String urlStr, Function<String, T> converter) {
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return converter.apply(reader.lines().collect(joining()));

        } catch (IOException e) {
            return null;
        }

    }

    private <T> T fromJson(String json, Type type){
        Gson gson = new Gson();
        return gson.fromJson(json,type);
    }

    @Test
    public void shouldBeAbbleToGetSoocerSeasonDto(){
        int id = 394;
        SoccerSeasonDto ss = getFromUri( soccerSeason(id), str -> fromJson(str,SoccerSeasonDto.class));
        assertThat(ss,notNullValue());
        assertThat(id, is(equalTo(ss.getId())));
    }


}
