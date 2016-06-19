package view;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by hmr on 11/06/2016.
 */
public class DomainToView {

    private static final Logger log = LoggerFactory.getLogger(DomainToView.class);

    /**
     *
     * @param templatename  name of file containing html page template
     * @param outputname        name of saved html page on file system
     * @param dom           domain object
     * @return              html string
     */

    public static String domainToHtml(String templatename, String outputname, Object dom){
        String templateStr;
        TemplateLoader loader = new ClassPathTemplateLoader();
        //loader.setPrefix("/resources");
        loader.setSuffix(".abs");
        Handlebars handlebars = new Handlebars(loader);
        try {
            Path path = Paths.get(outputname + ".html");
            templateStr = handlebars.compile(templatename).apply(dom);
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(templateStr);
            writer.close();
        } catch (IOException e) {
            templateStr = "fail to load resource: " + e.getMessage();
            log.error(templateStr);
        }
        return templateStr;
    }
}
