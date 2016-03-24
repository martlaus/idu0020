package ttu.idu0200.db;

import org.apache.log4j.Logger;
import ttu.idu0200.model.LearningObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 23.03.16.
 */
public class LearningObjectDAO {
    static Logger logger = Logger.getLogger(LearningObjectDAO.class);
    Connection db;

    public LearningObjectDAO(Connection connection) {
        try {
            this.db = connection;
        } catch (Exception var3) {
            logger.error("DAO constructor:" + var3.getMessage());
        }
    }

    public List<LearningObject> findAll() {
        List<LearningObject> learningObjects = new ArrayList<>();

        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM LearningObject");
            while (resultSet.next()) {
                LearningObject learningObject = new LearningObject();
                learningObject.setId(resultSet.getInt("id"));
                learningObject.setTitle(resultSet.getString("title"));
                learningObject.setDescription(resultSet.getString("description"));
                learningObject.setType(resultSet.getString("type"));

                learningObjects.add(learningObject);
            }
        } catch (SQLException e) {
            logger.error("DAO findAll:" + e.getMessage());
        }

        return learningObjects;
    }

    public LearningObject findById(int id) {
        LearningObject learningObject = new LearningObject();

        try {
            Statement statement = db.createStatement();
            String sql = "SELECT * FROM LearningObject WHERE  id=" + Integer.toString(id);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                learningObject.setId(resultSet.getInt("id"));
                learningObject.setTitle(resultSet.getString("title"));
                learningObject.setDescription(resultSet.getString("description"));
                learningObject.setType(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            logger.error("DAO findById:" + e.getMessage());
        }

        return learningObject;
    }

    public void update(LearningObject learningObject) {
        logger.info("LearningObjectDAO: update");

        Statement var2;

        try {
            var2 = this.db.createStatement();
            var2.executeUpdate("update LearningObject set type=\'" + learningObject.getType() + "\',title=\'" + learningObject.getTitle() + "\' ,description=\'" + learningObject.getDescription() + "\' where id=" + Integer.toString(learningObject.getId()));
        } catch (Exception var8) {
            logger.error("LearningObjectDAO: " + var8.getMessage());
        }

    }
}
