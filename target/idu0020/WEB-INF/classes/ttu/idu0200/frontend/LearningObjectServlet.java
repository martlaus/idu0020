package ttu.idu0200.frontend;

import org.apache.log4j.Logger;
import ttu.idu0200.db.LearningObjectDAO;
import ttu.idu0200.db.dbconnection;
import ttu.idu0200.model.LearningObject;
import ttu.idu0200.util.LearningObjectValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;


/**
 * Created by mart on 23.03.16.
 */
@WebServlet(
        value = {"/s"},
        loadOnStartup = 1
)
public class LearningObjectServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(LearningObjectServlet.class);

    LearningObjectDAO learningObjectDAO;

    public void init() {
        logger.info("LearningObjectServlet.init(): mind loodi");
    }

    public LearningObjectServlet() {
        Connection connection = dbconnection.getConnection();
        learningObjectDAO = new LearningObjectDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String param = request.getParameter("action");
        logger.info("doPost");


        if (param.equals("save")) {
            logger.info("doPost:save");

            LearningObject learningObject = new LearningObject();
            learningObject.setId(Integer.parseInt(request.getParameter("id")));
            learningObject.setTitle(request.getParameter("title"));
            learningObject.setDescription(request.getParameter("description"));
            learningObject.setType(request.getParameter("type"));

            LearningObjectValidator learningObjectValidator = new LearningObjectValidator();

            HashMap<String, String> errors = learningObjectValidator.validate(learningObject);
            if (errors.size() > 0) {
                logger.info("doPost: Got some form validation errors");

                request.setAttribute("learningObjectErrors", errors);
                forwardToLearningObjectById(request, resp, learningObject);
            } else {
                learningObjectDAO.update(learningObject);
                forwardToLearningObjectList(request, resp);
            }
        } else {
            error(request, resp);
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        logger.info("LearningObjectServlet: doing a get");

        String param = request.getParameter("id");

        if (param != null && param.length() > 0) {
            int id = 0;

            try {
                id = Integer.parseInt(param);
            } catch (Exception e) {
                error(request, response);
            }
            LearningObject learningObject = learningObjectDAO.findById(id);

            forwardToLearningObjectById(request, response, learningObject);
        } else {
            forwardToLearningObjectList(request, response);
        }
    }

    private void forwardToLearningObjectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("LearningObjectServlet: Forwarding to learning object list");
        List<LearningObject> learningObjects = learningObjectDAO.findAll();

        LearningObject[] los = learningObjects.toArray(new LearningObject[learningObjects.size()]);
        request.setAttribute("learningObjects", los);
        request.getRequestDispatcher("/learningObjects.jsp").forward(request, response);
    }

    private void forwardToLearningObjectById(HttpServletRequest request, HttpServletResponse response, LearningObject learningObject) throws ServletException, IOException {
        logger.info("LearningObjectServlet: Getting learning object by id");

        if (learningObject != null && learningObject.getId() > 0) {
            request.setAttribute("learningObject", learningObject);
            request.getRequestDispatcher("/learningObject.jsp").forward(request, response);

        } else {
            error(request, response);
        }
    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("redirecting to error page");

        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
