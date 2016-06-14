package util;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.League;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by hmr on 11/06/2016.
 */
public class DomainToView {

    /**
     *
     * @param templatename  name of file containing html page template
     * @param refuri        name of saved html page on file system
     * @param dom           domain object
     * @return              html string
     */

    public static String domainToHtml(String templatename, String refuri, Object dom){
        String templateStr;
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/resources");
        loader.setSuffix(".abs");
        Handlebars handlebars = new Handlebars(loader);
        try {
            Path path = Paths.get(refuri + ".html");
            templateStr = handlebars.compile(templatename).apply(dom);
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(templateStr);
            writer.close();
        } catch (IOException e) {
            templateStr = "";
        }
        return templateStr;
    }
}
