package app;

import domain.League;
import domain.Standing;
import footballapi.FootBallApiImpl;
import footballapi.dto.SeasonDto;
import footballapi.dto.TeamDto;
import footballapi.dto.TeamPlayersDto;
import domain.mapper.DtoToDomainMapper;
import view.DomainToView;
import httpserver.HttpServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hmr on 11/06/2016.
 */
public class SoccerController {
    private static HttpServer httpserver;
    private FootBallApiImpl footBallApi;

    private Map<Integer,League> leagues;

    public SoccerController(HttpServer srv){
        httpserver = srv
                .addHandler("/soccerapp/leagues/*", this::showLeagues)
                .addHandler("/teams/*", this::showTeam)
                .addHandler("/players/*",this::showPlayer);
        footBallApi = new FootBallApiImpl();
    }

    public void startServer() throws Exception {
        httpserver.run();
    }

    public void stopServer() throws Exception {
        httpserver.stop();
    }


    private String leagueList(){
        leagues = new HashMap<Integer,League>();
        List<League> lg = DtoToDomainMapper.seasonsToLeagues(footBallApi.getSeasons());
        for(League league : lg)
            leagues.put(league.getId(),league);
        return DomainToView.domainToHtml("leagueList", "leagues", leagues);
    }

    private String getLeague(int id){
        League league = leagues.get(id);
        if(league == null)
            league = DtoToDomainMapper.seasonDtoToLeague(footBallApi.getSeason(id));
        //if(league.getLeagueTable()==null)
            //completable future...
           //league. = DtoToDomainMapper.leagueTableToStandings(footBallApi.getLeagueTable(id));
        return DomainToView.domainToHtml("standingstable", "league"+id, league);
    }


    // path /soccerapp/leagues/*
    public String showLeagues(HttpServletRequest req){
        if(req.getPathInfo() == null) {
           return leagueList();
        }
        else{
            int id = 0;
            try {
                id = Integer.parseInt(req.getPathInfo().substring(1));
            }catch(NumberFormatException e){
                return leagueList();
            }
            return getLeague(id);
        }

    }

    //Path "/teams/*"
    public String showTeam(HttpServletRequest req){
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        TeamDto team = footBallApi.getTeam(id);
        return DomainToView.domainToHtml("team","team"+id, DtoToDomainMapper.teamDtoToTeam(team));
    }

    //Path "/players/*"
    public String showPlayer(HttpServletRequest req){
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        TeamPlayersDto teamplayers = footBallApi.getTeamPlayers(id);
        return DomainToView.domainToHtml("player","teamplayers"+id, DtoToDomainMapper.teamPlayersDtoToPlayers(teamplayers));
    }
}
