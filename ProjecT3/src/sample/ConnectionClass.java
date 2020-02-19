package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    public static Connection connection;

    public Connection getConnection(String username, String password){
        String url = "jdbc:mysql://localhost:3306/sorossa_athletes";
        //String userName = "root"String pw = "D@o09712130";
        System.out.println("Loading driver...");

        System.out.println("Trying to connect to database ...");
        try {
            connection = DriverManager.getConnection (url,username, password);
        } catch (SQLException e) {              e.printStackTrace();
        }
        System.out.println("Connected to the database");
        return connection;
    }

    public static void closeConnection() throws SQLException{
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }catch(Exception e){             throw e;
        }
    }
}
