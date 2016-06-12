package domain;

import footballapi.mapper.DtoToDomainMapper;
import footballapi.FootBallApiImpl;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;


/**
 * Created by hmr on 11/06/2016.
 */
public class MapperTest {

    @Test
    public void shouldBeAbbleToGetListOfLeagues(){
        //TODO avoid connection
        List<League> leagues = DtoToDomainMapper.seasonsToLeagues(
                new FootBallApiImpl().getSeasons()
        );
        assertThat(leagues,notNullValue());
        assertFalse(leagues.isEmpty());
    }
}
