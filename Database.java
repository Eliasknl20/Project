import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    static String URL = "jdbc:h2:./online_exam";

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("org.h2.Driver");

            connection = DriverManager.getConnection(
                    URL,
                    "sa",
                    ""
            );

            System.out.println("H2 Database Connected Successfully!");

        } catch (Exception e) {

            System.out.println(
                    "Database Connection Failed!"
            );

            e.printStackTrace();
        }

        return connection;
    }

    public static void createTables() {

        try {

            Connection connection =
                    getConnection();

            Statement statement =
                    connection.createStatement();

            // Students table
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) UNIQUE," +
                "password VARCHAR(50)," +
                "name VARCHAR(100))"
            );

            // Questions table
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS questions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "question VARCHAR(500)," +
                "option1 VARCHAR(200)," +
                "option2 VARCHAR(200)," +
                "option3 VARCHAR(200)," +
                "option4 VARCHAR(200)," +
                "answer INT)"
            );

            // Results table
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS results (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50)," +
                "score INT," +
                "total INT)"
            );

            System.out.println(
                    "Tables Created Successfully!"
            );

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}