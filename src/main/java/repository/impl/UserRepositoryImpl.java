package repository.impl;

import model.User;
import repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static db.DbConnection.getConnection;

public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT_STATEMENT = "insert into users(username,email,password,date_created,date_modified) values(?,?,?,?,?);";
    private static final String UPDATE_USERNAME_STATEMENT = "update users set username = ? , date_modified = ? where id = ?";
    private static final String FIND_ALL_STATEMENT = "select * from users";
    private static final String FIND_BY_ID_STATEMENT = FIND_ALL_STATEMENT + " where id = ?";
    private static final String DELETE_STATEMENT = "delete from users where id = ?";

    private static User toUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setDateModified(LocalDate.parse(resultSet.getString("date_modifed")));
        user.setDateCreated(LocalDate.parse(resultSet.getString("date_created")));
        return user;
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_STATEMENT);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getDateCreated().toString());
            preparedStatement.setString(5, user.getDateModified().toString());
            int r = preparedStatement.executeUpdate();
            System.out.println("U shtuan " + r + " rekorde");
        } catch (SQLException e) {
            System.out.println("Nuk u cua deri ne fund veprimi");
        }
    }

    @Override
    public User findById(Integer id) {
        try {
            PreparedStatement findPreparedStatement = getConnection().prepareStatement(FIND_BY_ID_STATEMENT);
            findPreparedStatement.setInt(1, id);
            ResultSet resultSet = findPreparedStatement.executeQuery();
            if (resultSet.next()) {
                return toUser(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }

        return null;
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_USERNAME_STATEMENT);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getDateModified().toString());
            preparedStatement.setInt(3, user.getId());
            int r = preparedStatement.executeUpdate();
            System.out.println(r + " rreshta u afektuan nga ky veprim update");
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
    }

    @Override
    public List<User> findAll() {
        try {
            PreparedStatement findPreparedStatement = getConnection().prepareStatement(FIND_ALL_STATEMENT);
            ResultSet resultSet = findPreparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = toUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_STATEMENT);
            preparedStatement.setInt(1, id);
            int r = preparedStatement.executeUpdate();
            System.out.println(r + " rreshta u afektuan nga ky veprim delete");
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
    }
}
