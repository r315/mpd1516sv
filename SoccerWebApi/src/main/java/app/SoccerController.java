package app;

import domain.Standing;
import domain.Team;
import footballapi.FootBallApiImpl;
import footballapi.dto.SeasonDto;
import footballapi.dto.TeamDto;
import footballapi.dto.TeamPlayersDto;
import util.DtoToDomainMapper;
import util.DomainToView;
import httpserver.HttpServer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hmr on 11/06/2016.
 */
public class SoccerController {
    private static HttpServer httpserver;
    private static final int PORT = 8080;

    public SoccerController(){
        httpserver = new HttpServer(PORT)
                .addHandler("/soccerapp/leagues/*", this::showLeagues)
                .addHandler("/teams/*", this::showTeam)
                .addHandler("/players/*",this::showPlayer);
    }

    public void start() throws Exception {
        httpserver.run();
    }

    public void stop() throws Exception {
        httpserver.stop();
    }

    // path /soccerapp/leagues/*
    public String showLeagues(HttpServletRequest req){
        //todo composable supplier
        if(req.getPathInfo() == null) {
            List<SeasonDto> ss = new FootBallApiImpl().getSeasons();
            return DomainToView.domainToHtml("leagues", "leagues", DtoToDomainMapper.seasonsToLeagues(ss));
        }
        else{
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            List<Standing> ss = DtoToDomainMapper.leagueTableToStandings(
                    new FootBallApiImpl().getLeagueTable(id));
            return DomainToView.domainToHtml("standingstable", "league"+id, ss);
        }

    }

    //Path "/teams/*"
    public String showTeam(HttpServletRequest req){
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        TeamDto team = new FootBallApiImpl().getTeam(id);
        return DomainToView.domainToHtml("team","team"+id, DtoToDomainMapper.teamDtoToTeam(team));
    }

    //Path "/players/*"
    public String showPlayer(HttpServletRequest req){
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        TeamPlayersDto teamplayers = new FootBallApiImpl().getTeamPlayers(id);
        return DomainToView.domainToHtml("player","teamplayers"+id, DtoToDomainMapper.teamPlayersDtoToPlayers(teamplayers));
    }



}
