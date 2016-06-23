package footballapi;

import footballapi.dto.LeagueTableDto;
import footballapi.dto.SeasonDto;
import footballapi.dto.TeamDto;
import footballapi.dto.TeamPlayersDto;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by hmr on 08/06/2016.
 */
public class FootballApiTest {
    FootBallApi footBallApi = new FootBallApiImpl();

    @Test
    public void shouldBeAbbleToGet_SeasonDto(){
        int id = 394;
        SeasonDto ss = footBallApi.getSeason(id);
        assertThat(ss,notNullValue());
        assertThat(id, is(equalTo(ss.id)));
    }

    @Test
    public void shouldBeAbbleToGet_TeamDto(){
        int id = 61;
        TeamDto ss = footBallApi.getTeam(id);
        assertThat(ss,notNullValue());
        assertThat("CFC", is(equalTo(ss.code)));
    }

    @Test
    public void shouldBeAbbleToGet_LeagueTableDto(){
        int id = 394;
        LeagueTableDto lt = footBallApi.getLeagueTable(id);
        assertThat(lt,notNullValue());
        assertThat("1. Bundesliga 2015/16", is(equalTo(lt.leagueCaption)));
    }

    @Test
    public void shouldBeAbbleToGet_TeamPlayersDto(){
        int id = 61;
        TeamPlayersDto tpd = footBallApi.getTeamPlayers(id);
        assertThat(tpd,notNullValue());
        assertThat(25, is(equalTo(tpd.count)));
    }
}
