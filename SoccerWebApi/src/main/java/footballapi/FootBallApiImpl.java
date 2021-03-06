package footballapi;

import com.google.gson.reflect.TypeToken;
import footballapi.dto.LeagueTableDto;
import footballapi.dto.SeasonDto;
import footballapi.dto.TeamDto;
import footballapi.dto.TeamPlayersDto;
import httpserver.HttpGet;

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
    private String leagueTableURI(int id) { return uriGenerator(uriGenerator(soccerSeasonsURI(),id),"leagueTable"); }
    private String teamPlayersURI(int id) { return uriGenerator(uriGenerator(soccerTeamsURI(),id),"players"); }

    public static String soccerSeasonURI(int id) {
        return uriGenerator(soccerSeasonsURI(),id);
    }
    public static String soccerTeamURI(int id) {
        return uriGenerator(soccerTeamsURI(),id);
    }

    @Override
    public List<SeasonDto> getSeasons() {
        return HttpGet.getSync(soccerSeasonsURI(), str -> fromJson(str, new TypeToken<List<SeasonDto>>() {
        }.getType()));
    }

    @Override
    public SeasonDto getSeason(int soccerseasonid) {
        return HttpGet.getSync(soccerSeasonURI(soccerseasonid), str -> fromJson(str, SeasonDto.class));
    }

    @Override
    public TeamDto getTeam(int teamid) {
        return HttpGet.getSync(soccerTeamURI(teamid), str -> fromJson(str, TeamDto.class));
    }

    @Override
    public TeamPlayersDto getTeamPlayers(int teamid) {
        return HttpGet.getSync(teamPlayersURI(teamid), str -> fromJson(str, TeamPlayersDto.class));
    }

    @Override
    public LeagueTableDto getLeagueTable(int soccerseasonid) {
        return HttpGet.getSync(leagueTableURI(soccerseasonid), str -> fromJson(str, LeagueTableDto.class));
    }


}
