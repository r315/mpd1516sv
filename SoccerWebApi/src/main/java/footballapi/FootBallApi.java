package footballapi;

import com.google.gson.Gson;
import footballapi.dto.LeagueTableDto;
import footballapi.dto.SeasonDto;
import footballapi.dto.TeamDto;
import footballapi.dto.TeamPlayersDto;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hmr on 11/06/2016.
 */
public abstract class FootBallApi {
    protected static final String BASE_URI = "http://api.football-data.org/v1";

    protected static String uriGenerator(String path, String add) {
        return path + "/" + add;
    }

    protected static String uriBaseGenerator(String path) {
        return uriGenerator(BASE_URI,path);
    }

    protected static String uriGenerator(String path, int id) {
        return uriGenerator(path, Integer.toString(id));
    }

    protected <T> T fromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public abstract List<SeasonDto> getSeasons();
    public abstract SeasonDto getSeason(int soccerseasonid);
    public abstract TeamDto getTeam(int teamid);
    public abstract TeamPlayersDto getTeamPlayers(int teamid);
    public abstract LeagueTableDto getLeagueTable(int soccerseasonid);
}
