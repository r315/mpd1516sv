package app;

import domain.League;
import domain.Standing;
import footballapi.FootBallApi;
import footballapi.dto.TeamDto;
import footballapi.dto.TeamPlayersDto;
import domain.mapper.DtoToDomainMapper;
import view.DomainToView;
import httpserver.HttpServer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hmr on 11/06/2016.
 */
public class SoccerController implements AutoCloseable{

    private static HttpServer httpserver;
    private FootBallApi footBallApi;

    public SoccerController(HttpServer srv, FootBallApi fb){
        httpserver = srv
                .addHandler("/soccerapp/leagues/*", this::showLeagues)
                .addHandler("/teams/*", this::showTeam)
                .addHandler("/players/*",this::showPlayer);
        footBallApi = fb;
    }

    public void startServer() throws Exception {
        httpserver.run();
    }

    private String leagueList(){
        List<League> lg = DtoToDomainMapper.seasonsToLeagues(footBallApi.getSeasons());
        return DomainToView.domainToHtml("leagueList", "leagues", lg);
    }

    private String getLeague(int id){
        List<Standing> standings = DtoToDomainMapper.leagueTableToStandings(footBallApi.getLeagueTable(id));
        return DomainToView.domainToHtml("leaguestandings", "leaguestadings"+id, standings);
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

    @Override
    public void close() throws Exception {
        httpserver.stop();
    }
}
