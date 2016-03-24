package ttu.idu0200.frontend;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import ttu.idu0200.model.LearningObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mart on 24.03.16.
 */

@WebServlet(
        value = {"/log"},
        loadOnStartup = 1
)
public class LoggingServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(LoggingServlet.class);


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        logger.info("LoggingServlet: doing a get");
        String content = null;
        try {
            content = readFile("/usr/share/tomcat8/bin/idu0020.log", Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            logger.error("LoggingServlet");
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(content);
        out.flush();
    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
