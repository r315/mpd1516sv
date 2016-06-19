package footballapi.dto;

import domain.Standing;

import java.util.List;

/**
 * Created by hmr on 13/06/2016.
 */

//http://api.football-data.org/v1/soccerseasons/{id}/leagueTable

public class LeagueTableDto {
    public String leagueCaption;
    public int matchday;
    public List<StandingDto> standing; //TODO check soccerseasons/405/leagueTable

    public class StandingDto{
        public int position;
        public String teamName;
        public String crestURI;
        public int playedGames;
        public int points;
        public int goals;
        public int goalsAgainst;
        public int goalDifference;
        public int wins;
        public int draws;
        public int losses;
        public SingleStanding home;
        public SingleStanding away;

        private class SingleStanding {
            public int goals;
            public int goalsAgainst;
            public int wins;
            public int draws;
            public int losses;
        }
    }
}
