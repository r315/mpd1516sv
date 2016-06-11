package domain;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by hmr on 05/06/2016.
 */
public class Team {

    private String name;
    private String code;
    private String shortName;
    private String value;
    private URL url;
    private CompletableFuture<List<Player>> players;

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public String getShortName(){
        return shortName;
    }

    public String getSquadMarketValue(){
        return value;
    }

    public String getCrestUrl(){
        return url.toString();
    }

    public CompletableFuture<List<Player>> getPlayers(){
        return players;
    }
}
