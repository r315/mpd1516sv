package httpserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

/**
 * Created by hmr on 11/06/2016.
 */
public class HttpServer {
    private final Server server;
    private final ServletHandler container;

    public HttpServer(int port){
        server = new Server(port);
        container = new ServletHandler(); // Servlets container
        server.setHandler(container);
    }

    public void run() throws Exception {
        server.start();
        //server.join();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public HttpServer addHandler(String path, Function<HttpServletRequest, String> handler) {
        container.addServletWithMapping(new ServletHolder(new SoccerServlet(handler)), path);
        return this;
    }
}
