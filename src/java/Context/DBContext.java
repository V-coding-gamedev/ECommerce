package Context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public Connection getConnection() throws Exception {
       String url = "jdbc:sqlserver://LAPTOP-O2QRS99E\\SQLEXPRESS:1433;databaseName=ECommerce;encrypt=true;trustServerCertificate=true"; 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(url, userID, password);
        return connection;
    }

 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "LAPTOP-O2QRS99E\\SQLEXPRESS"; //127.0.0.1
    private final String dbName = "ECommerce";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "sa";
    protected Connection connection; 
}