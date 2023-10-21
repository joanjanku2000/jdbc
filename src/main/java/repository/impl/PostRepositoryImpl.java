package repository.impl;

import model.Post;
import repository.PostRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static db.DbConnection.getConnection;

public class PostRepositoryImpl implements PostRepository {

    private static final String INSERT_STATEMENT = "insert into posts(title,body,user_id,date_created,date_modified) values(?,?,?,?,?);";
    private static final String UPDATE_BODY_STATEMENT = "update posts set body = ? , date_modified = ? where id = ?";
    private static final String FIND_ALL_STATEMENT = "select * from posts";
    private static final String FIND_BY_ID_STATEMENT = FIND_ALL_STATEMENT + " where id = ?";
    private static final String DELETE_STATEMENT = "delete from posts where id = ?";

    private static Post toPost(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getInt("id"));
        post.setBody(resultSet.getString("body"));
        post.setTitle(resultSet.getString("title"));
        post.setDateCreated(LocalDate.parse(resultSet.getString("date_created")));
        post.setDateModified(LocalDate.parse(resultSet.getString("date_modified")));
        return post;

    }

    @Override
    public void save(Post post) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_STATEMENT);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getBody());
            preparedStatement.setInt(3, post.getUserId());
            preparedStatement.setString(4, post.getDateCreated().toString());
            preparedStatement.setString(5, post.getDateModified().toString());
            int r = preparedStatement.executeUpdate();
            System.out.println("U shtuan " + r + " rekorde");
        } catch (SQLException e) {
            System.out.println("Nuk u cua deri ne fund veprimi");
        }
    }

    @Override
    public Post findById(Integer id) {
        try {
            PreparedStatement findPreparedStatement = getConnection().prepareStatement(FIND_BY_ID_STATEMENT);
            findPreparedStatement.setInt(1, id);
            ResultSet resultSet = findPreparedStatement.executeQuery();
            if (resultSet.next()) {
                return toPost(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
        return null;
    }

    @Override
    public void update(Post post) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_BODY_STATEMENT);
            preparedStatement.setString(1, post.getBody());
            preparedStatement.setString(2, post.getDateModified().toString());
            preparedStatement.setInt(3, post.getId());
            int r = preparedStatement.executeUpdate();
            System.out.println(r + " rreshta u afektuan nga ky veprim update");
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
    }

    @Override
    public List<Post> findAll() {
        try {
            PreparedStatement findPreparedStatement = getConnection().prepareStatement(FIND_ALL_STATEMENT);
            ResultSet resultSet = findPreparedStatement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                posts.add(toPost(resultSet));
            }
            return posts;
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
