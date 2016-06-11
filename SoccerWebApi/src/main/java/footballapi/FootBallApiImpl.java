package footballapi;

import com.google.gson.reflect.TypeToken;
import footballapi.dto.SoccerSeasonDto;
import footballapi.dto.SoccerTeamDto;
import util.Http;

import java.util.List;

/**
 * Created by hmr on 11/06/2016.
 */
public class FootBallApiImpl extends FootBallApi {

    public static String soccerSeasonsURI() {
        return uriBaseGenerator("soccerseasons");
    }

    public static String soccerTeamsURI() {
        return uriBaseGenerator("teams");
    }

    public static String soccerSeasonURI(int id) {
        return uriGenerator(soccerSeasonsURI(),id);
    }
    public static String soccerTeamURI(int id) {
        return uriGenerator(soccerTeamsURI(),id);
    }

    @Override
    public List<SoccerSeasonDto> getSeasons() {
        return Http.getFromUri( soccerSeasonsURI(), str -> fromJson(str,new TypeToken<List<SoccerSeasonDto>>() {}.getType()));
    }

    @Override
    public SoccerSeasonDto getSeason(int soccerseasonid) {
        return Http.getFromUri( soccerSeasonURI(soccerseasonid), str -> fromJson(str,SoccerSeasonDto.class));
    }

    @Override
    public SoccerTeamDto getTeam(int teamid) {
        return Http.getFromUri( soccerTeamURI(teamid), str -> fromJson(str,SoccerTeamDto.class));
    }
}
