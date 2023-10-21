package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static Connection connection ;

    private DbConnection(){
    }

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_network?user=root&password=Panteraroze@2000");
            }
            return connection;
        } catch (Exception e) {
            System.out.println("Cannot connect to db");
            return null;
        }
    }
}
