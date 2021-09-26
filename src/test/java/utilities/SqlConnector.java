package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SqlConnector {
    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);
    static String dbName = ReadConfigFiles.getPropertyValues("DbName");
    private static final String DbUrl = String.format("jdbc:postgresql://localhost:5432/%s",dbName);
    private static final String user = ReadConfigFiles.getPropertyValues("DBUser");
    private static final String Password = ReadConfigFiles.getPropertyValues("DBPassword");

    /***
     * Connect to the postgreSql database
     * @return a Connection object
     */
    private static Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DbUrl,user,Password);
            LOGGER.info("Connection to the postgreSql server successfully");
        }catch (SQLException e){
            LOGGER.error("Sql Connection exception: " + e.getMessage());
        }
        return conn;
    }

    /***
     * Reading data from the database
     * @param SQL is the method parameter for passing SQL Query
     * @return a result set object
     */
    public static ResultSet readData(String SQL){
        ResultSet rs = null;
        try {
            Connection conn = connect();
           Statement stmt =  conn.createStatement();
           rs = stmt.executeQuery(SQL);
        }catch (SQLException e){
            LOGGER.error("Sql Result set exception: " + e.getMessage());
        }
        return rs;
    }
}
