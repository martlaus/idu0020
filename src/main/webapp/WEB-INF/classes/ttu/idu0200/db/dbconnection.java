package ttu.idu0200.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mart on 23.03.16.
 */
public class dbconnection {

    public static Connection getConnection() {
        Logger logger = Logger.getLogger(LearningObjectDAO.class);

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return null;
        }

        logger.info("MySQL JDBC Driver Registered!");
        Connection connection;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/idu0020", "root", "root");

        } catch (SQLException e) {
            logger.error("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            logger.info("You made it, take control your database now!");
        } else {
            logger.error("Failed to make connection!");
        }

        return connection;
    }
}
