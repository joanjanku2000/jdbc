import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/library?user=" + Password.USER_NAME + "&" + "password=" + Password.PASSWORD);
        String authorName = "A.J. McAllister";
        String authorNameToInsert = "Author test";

//        PreparedStatement preparedStatementInsert = connection.prepareStatement(
//                "insert into author(full_name) values (?)");
//
//        preparedStatementInsert.setString(1,authorNameToInsert);
//
//        preparedStatementInsert.executeUpdate();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from author where full_name = ?");
        preparedStatement.setString(1,authorName);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            Integer id = resultSet.getInt(2);

            System.out.println("Id = " + id + " Name = " + name);
        }
    }
}
