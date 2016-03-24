package ttu.idu0200.frontend;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ttu.idu0200.db.LearningObjectDAO;
import ttu.idu0200.db.dbconnection;
import ttu.idu0200.model.LearningObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by mart on 24.03.16.
 */

@WebServlet(
        value = {"/learningObjectService"},
        loadOnStartup = 1
)
public class LearningObjectService extends HttpServlet {
    static Logger logger = Logger.getLogger(LearningObjectService.class);


    LearningObjectDAO learningObjectDAO;

    public void init() {
        logger.info("LearningObjectService.init(): mind loodi");
    }

    public LearningObjectService() {
        Connection connection = dbconnection.getConnection();
        learningObjectDAO = new LearningObjectDAO(connection);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        logger.info("LearningObjectService. doing a get");

        String param = request.getParameter("id");

        if (param != null && param.length() > 0) {
            int id = 0;

            try {
                id = Integer.parseInt(param);
            } catch (Exception e) {
                error(request, response);
            }

            LearningObject learningObject = learningObjectDAO.findById(id);

            Gson gson = new Gson();
            String jsonObject = gson.toJson(learningObject);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();

        }

    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("redirecting to error page");

        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
