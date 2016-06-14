package domain;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by hmr on 05/06/2016.
 */
public class Team {

    private String name;
    private String code;
    private String shortName;

    private String squadmarketvalue;
    private String url;
    private CompletableFuture<List<Player>> players;

    public Team(String name, String code, String shortName, String squadMarketValue, String url) {
        this.name = name;
        this.code = code;
        this.shortName = shortName;
        this.squadmarketvalue = squadMarketValue;
        this.url = url;
    }

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
        return squadmarketvalue;
    }

    public String getCrestUrl(){
        return url;
    }

    public CompletableFuture<List<Player>> getPlayers(){
        return players;
    }

}
