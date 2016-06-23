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


    public static void fileWriter(String file, String data){
        Path path = Paths.get(file + ".html");
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(path);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            log.error("fail writing to file: " + e.getMessage());
        }
    }

    /**
     *
     * @param templatename  name of file containing html page template
     * @param outputname        name of saved html page on file system
     * @param dom           domain object
     * @return              html string
     */

    public static String domainToHtml(String templatename, String outputname, Object dom){
        String templateStr = "";
        TemplateLoader loader = new ClassPathTemplateLoader();
        //loader.setPrefix("/resources");
        loader.setSuffix(".abs");
        Handlebars handlebars = new Handlebars(loader);
        try {
            templateStr = handlebars.compile(templatename).apply(dom);
            //fileWriter(outputname + ".html",templateStr);
        } catch (IOException e) {
            templateStr = "fail opening tmplate: " + e.getMessage();
            log.error(templateStr);
        }
        return templateStr;
    }
}
