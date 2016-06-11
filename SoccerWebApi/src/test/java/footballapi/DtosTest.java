package footballapi;

import footballapi.dto.SoccerSeasonDto;
import footballapi.dto.SoccerTeamDto;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by hmr on 08/06/2016.
 */
public class DtosTest {


    @Test
    public void shouldBeAbbleToGetSoocerSeasonDto(){
        int id = 394;
        SoccerSeasonDto ss = new FootBallApiImpl().getSeason(id);
        assertThat(ss,notNullValue());
        assertThat(id, is(equalTo(ss.id)));
    }

    @Test
    public void shouldBeAbbleToGetTeamDto(){
        int id = 61;
        SoccerTeamDto ss = new FootBallApiImpl().getTeam(id);
        assertThat(ss,notNullValue());
        assertThat("CFC", is(equalTo(ss.code)));
    }
}
