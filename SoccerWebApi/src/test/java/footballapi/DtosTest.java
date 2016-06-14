package footballapi;

import footballapi.dto.LeagueTableDto;
import footballapi.dto.SeasonDto;
import footballapi.dto.TeamDto;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by hmr on 08/06/2016.
 */
public class DtosTest {


    @Test
    public void shouldBeAbbleToGetSoccerSeasonDto(){
        int id = 394;
        SeasonDto ss = new FootBallApiImpl().getSeason(id);
        assertThat(ss,notNullValue());
        assertThat(id, is(equalTo(ss.id)));
    }

    @Test
    public void shouldBeAbbleToGetTeamDto(){
        int id = 61;
        TeamDto ss = new FootBallApiImpl().getTeam(id);
        assertThat(ss,notNullValue());
        assertThat("CFC", is(equalTo(ss.code)));
    }

    @Test
    public void shouldBeAbbleToGetLeagueTableDto(){
        int id = 394;
        LeagueTableDto lt = new FootBallApiImpl().getLeagueTable(id);
        assertThat(lt,notNullValue());
        assertThat("1. Bundesliga 2015/16", is(equalTo(lt.leagueCaption)));
    }
}
