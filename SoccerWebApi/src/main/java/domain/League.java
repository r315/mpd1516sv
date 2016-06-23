package domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by hmr on 05/06/2016.
 */
public class League {
    private int id;
    private String caption;
    private String league;
    private String year;

    private CompletableFuture<List<Standing>> standings;
    private int currentMatchday;
    private int numberOfMatchdays;
    private List<Team> teams;
    private int numberOfGames;
    private Date lastUpdated;

    public League(int id, String caption, String league, String year) {
        this.caption = caption;
        this.year = year;
        this.id = id;
        this.league = league;
    }


    public CompletableFuture<List<Standing>> getLeagueTable(){
        return standings;
    }

    public int getId(){
        return id;
    }

    public String getLeague(){return league;}

    public String getCaption(){
        return caption;
    }

    public String getYear(){
        return year;
    }

    public int getCurrentMatchday(){
        return currentMatchday;
    }

    public int getNumberOfMatchdays(){
        return numberOfMatchdays;
    }

    public int getNumberOfTeams(){
        return teams.size();
    }

    public int getNumberOfGames(){
        return numberOfGames;
    }

    public Date getLastUpdated(){
        return lastUpdated;
    }
}
