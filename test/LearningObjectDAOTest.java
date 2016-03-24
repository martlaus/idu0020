import org.junit.Test;
import ttu.idu0200.db.LearningObjectDAO;
import ttu.idu0200.db.dbconnection;
import ttu.idu0200.model.LearningObject;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mart on 24.03.16.
 */
public class LearningObjectDAOTest {

    @Test
    public void findById(){
        Connection connection = dbconnection.getConnection();

        LearningObjectDAO learningObjectDAO = new LearningObjectDAO(connection);

        LearningObject learningObject = learningObjectDAO.findById(1);

        assertNotNull(learningObject);
        assertEquals(1, learningObject.getId());
        assertNotNull(learningObject.getTitle());
    }

    @Test
    public void findAll(){
        Connection connection = dbconnection.getConnection();

        LearningObjectDAO learningObjectDAO = new LearningObjectDAO(connection);

        List<LearningObject> learningObjects = learningObjectDAO.findAll();

        assertNotNull(learningObjects);
        assertTrue(learningObjects.size() > 0);
    }

    @Test
    public void update(){
        Connection connection = dbconnection.getConnection();

        LearningObjectDAO learningObjectDAO = new LearningObjectDAO(connection);

        List<LearningObject> learningObjects = learningObjectDAO.findAll();

        assertNotNull(learningObjects);
        assertTrue(learningObjects.size() > 0);
    }

}
