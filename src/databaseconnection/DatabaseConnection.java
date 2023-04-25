package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String driver ="com.mysql.cj.jdbc.Driver";
    private static String url="";
    private static String user="";
    private static String password="";
    private static Connection connection=null;

    public static Connection getConnection(String url, String user, String password) {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }catch(SQLException sqlException){
            throw new RuntimeException(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println("Driver Class Not found " +classNotFoundException);
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try{
            if(connection!=null)
                connection.close();
        } catch(SQLException sqlException){
            System.out.println("Couldn't close connection to the driver "+sqlException.getMessage());
        }
    }

}
