import databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.Properties;

public class Application {
    static Properties prop = new Properties();
    static Connection connection = null;
    static Statement statement = null;

    private final static String INSERT_QUERY = "INSERT INTO EmployeeDemographics (EmployeeID, FirstName, LastName, Age, Gender) " +
                                                "VALUES(2003, 'Zarina', 'Ali', 30, 'Female')";

    public static void main(String[] args) throws SQLException {

        try {

            prop.load(Application.class.getClassLoader().getResourceAsStream("config.properties"));
            connection = DatabaseConnection.getConnection(
                    prop.getProperty("db_url").toString(),
                    prop.getProperty("db_user"),
                    prop.getProperty("db_pwd"));

            statement = connection.createStatement();

            int num = statement.executeUpdate(INSERT_QUERY);

            System.out.println("No. of rows affected : "+num);

        }catch (Exception sqlException){
            System.out.println(sqlException);
        }
        finally {
            if(statement!=null)
                statement.close();
            DatabaseConnection.closeConnection(connection);
        }
    }
}