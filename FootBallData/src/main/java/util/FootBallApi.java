package util;

/**
 * Created by hmr on 08/06/2016.
 */

//todo use Functions??
public class  FootBallApi{
    private static final String BASE_URI = "http://api.football-data.org/v1";

    private static String uriGenerator(String path, String add) {
        return path + "/" + add;
    }

    private static String uriGenerator(String path, int id) {
        return uriGenerator(uriGenerator(BASE_URI, path), Integer.toString(id));
    }

    public static String soccerSeasons() {
        return uriGenerator(BASE_URI,"soccerseasons");
    }

    public static String soccerSeason(int id) {
        return uriGenerator("soccerseasons", id);
    }

    public static String leagueTable(int leagueid) {
        return uriGenerator(soccerSeason(leagueid), "leagueTable");
    }
}