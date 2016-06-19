package httpserver;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.function.Function;

public class SoccerServlet extends HttpServlet{

        private final Function<HttpServletRequest, String> handler;
        private final Charset utf;

        public SoccerServlet(Function<HttpServletRequest, String> handler) {
            this.handler = handler;
            utf = Charset.forName("UTF-8");
        }

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException {

            resp.setContentType(String.format("text/html; charset=%s", utf.name()));

            String respBody = handler.apply(req);

            byte[] respBodyBytes = respBody.getBytes(utf);
            resp.setStatus(200);
            resp.setContentLength(respBodyBytes.length);
            OutputStream os = resp.getOutputStream();
            os.write(respBodyBytes);
            os.close();
        }
}
