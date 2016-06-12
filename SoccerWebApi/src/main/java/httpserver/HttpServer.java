package httpserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
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
        server.join();
    }

    public HttpServer addHandler(String path, Function<HttpServletRequest, String> handler) {
        container.addServletWithMapping(new ServletHolder(new HandlerServlet(handler)), path);
        return this;
    }

    class HandlerServlet extends HttpServlet {
        private final Function<HttpServletRequest, String> handler;
        private final Charset utf8;

        public HandlerServlet(Function<HttpServletRequest, String> handler) {
            this.handler = handler;
            utf8 = Charset.forName("utf-8");
        }

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException {

            resp.setContentType(String.format("text/plain; charset=%s", utf8.name()));

            String respBody = handler.apply(req);

            byte[] respBodyBytes = respBody.getBytes(utf8);
            resp.setStatus(200);
            resp.setContentLength(respBodyBytes.length);
            OutputStream os = resp.getOutputStream();
            os.write(respBodyBytes);
            os.close();
        }
    }
}
