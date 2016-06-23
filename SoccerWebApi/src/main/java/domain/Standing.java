package domain;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by hmr on 05/06/2016.
 */
public class Standing {

    private CompletableFuture<Team> team;
    private int position;
    private String teamName;
    private int playedGames;
    private int points;
    private int goals;

    private int goalsAgainst;
    private int wins;
    private int draws;
    private int losses;

    public Standing(int pos, String tname, int pg, int points, int goals, int gagainst, int wins, int draws, int losses) {
        this.position = pos;
        this.teamName = tname;
        this.playedGames = pg;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = gagainst;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }


    public CompletableFuture<Team> getTeam(){
        return team;
    }

    public int getPosition(){
        return position;
    }

    public String getTeamName(){ return teamName; }

    public int getPlayedGames(){
        return playedGames;
    }

    public int getPoints(){
        return points;
    }

    public int getGoals(){
        return goals;
    }

    public int getWins(){
        return wins;
    }

    public int getDraws(){
        return draws;
    }

    public int getLosses(){
        return losses;
    }

    public int getGoalsAgainst() { return goalsAgainst; }
}
