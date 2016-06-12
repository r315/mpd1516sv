package htmltemplate;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.League;
import org.junit.Test;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hmr on 12/06/2016.
 */
public class LeagueViewTest {

    @Test
    public void shouldExecuteAnHandlebarsTemplateFromFile() throws Exception {
        TemplateLoader loader = new ClassPathTemplateLoader();
        //loader.setPrefix("/templates");
        loader.setSuffix(".abs");

        Handlebars handlebars = new Handlebars(loader);

        Template template = handlebars.compile("leagues");

        List<League> league = Arrays.asList(
                new League("BL1", 394, "1. Bundesliga 2015/16", "2015"),
                new League("BL3", 403, "3. Bundesliga 2015/16", "2015"),
                new League("EL1", 425, "League One 2015/16", "2015"),
                new League("SD", 400, "Segunda Division 2015/16", "2015")
        );

        String templateStr = template.apply(league);
        System.out.println(templateStr);

        Path path = Paths.get("output.html");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(templateStr);
        }
    }
}
