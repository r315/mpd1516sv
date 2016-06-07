package domain;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by hmr on 05/06/2016.
 */
public class Standing {

    private CompletableFuture<Team> team;
    private int position;
    private int playerGames;
    private int points;
    private int goals;
    private int wins;
    private int draws;
    private int losses;

    public CompletableFuture<Team> getTeam(){
        return team;
    }

    public int getPosition(){
        return position;
    }

    public String getTeamName(){
        try {
            return team.get().getName();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getPlayedGames(){
        return playerGames;
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
}
