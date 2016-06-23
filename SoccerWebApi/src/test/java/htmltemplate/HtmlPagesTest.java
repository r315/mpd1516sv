package htmltemplate;

import domain.League;
import domain.Standing;
import view.DomainToView;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hmr on 12/06/2016.
 */
public class HtmlPagesTest {

    @Test
    public void shouldGetHtmlFromLeagueList() throws Exception {

        List<League> league = Arrays.asList(
                new League(394,"BL1", "1. Bundesliga 2015/16", "2015"),
                new League(403,"BL3", "3. Bundesliga 2015/16", "2015"),
                new League(425,"EL1", "League One 2015/16", "2015"),
                new League(400,"SD","Segunda Division 2015/16", "2015")
        );
        new DomainToView().domainToHtml("leagues","TestLeagues",league);
    }

    @Test
    public void shouldGetHtmlFromStandingList() throws Exception {

        List<Standing> standings = Arrays.asList(
                    new Standing(1, "FC Bayern München", 34, 88,17,63,28,4,2),
                new Standing(2, "Borussia Dortmund", 34, 78,82,34,24,6,4)
        );
        DomainToView.domainToHtml("standingstable","TestStandings",standings);
    }
}
