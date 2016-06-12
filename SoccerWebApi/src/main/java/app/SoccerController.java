package app;

import httpserver.HttpServer;

import javax.ws.rs.Path;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

/**
 * Created by hmr on 11/06/2016.
 */
public class SoccerController {
    private static HttpServer httpserver;
    private static final int PORT = 8080;

    public SoccerController(){
        httpserver = new HttpServer(PORT)
                .addHandler("/soccerapp/leagues/*", this::showLeagues)
                .addHandler("/soccerapp/teams/*", this::showTeam)
                .addHandler("/soccerapp/players/*",this::showPlayer);
    }

    public void start() throws Exception {
        httpserver.run();
    }

    public String showLeagues(HttpServletRequest req){
        return "stub!!";  //handlebars here
    }

    public String showTeam(HttpServletRequest req){
        return "stub!!"; //handlebars here
    }

    public String showPlayer(HttpServletRequest req){
        return "stub!!"; //handlebars here
    }
}
