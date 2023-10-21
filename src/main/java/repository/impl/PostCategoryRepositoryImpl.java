package repository.impl;

import model.PostCategory;
import repository.PostCategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static db.DbConnection.getConnection;

public class PostCategoryRepositoryImpl implements PostCategoryRepository {
    private static final String INSERT_STATEMENT = "insert into post_categories(post_id,category_id,date_created,date_modified) values(?,?,?,?);";
    private static final String UPDATE_BODY_STATEMENT = "update post_categories set post_id = ? ,category_id = ? , date_modified = ? where id = ?";
    private static final String FIND_ALL_STATEMENT = "select * from post_categories";
    private static final String FIND_BY_ID_STATEMENT = FIND_ALL_STATEMENT + " where id = ?";
    private static final String DELETE_STATEMENT = "delete from post_categories where id = ?";

    private static PostCategory toPostCategory(ResultSet resultSet) throws SQLException {
        PostCategory postCategory = new PostCategory();
        postCategory.setId(resultSet.getInt("id"));
        postCategory.setPostId(resultSet.getInt("post_id"));
        postCategory.setCategoryId(resultSet.getInt("category_id"));
        postCategory.setDateCreated(LocalDate.parse(resultSet.getString("date_created")));
        postCategory.setDateModified(LocalDate.parse(resultSet.getString("date_modified")));
        return postCategory;
    }

    @Override
    public void save(PostCategory postCategory) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_STATEMENT);
            preparedStatement.setInt(1, postCategory.getPostId());
            preparedStatement.setInt(2, postCategory.getCategoryId());
            preparedStatement.setString(3,postCategory.getDateCreated().toString());
            preparedStatement.setString(4, postCategory.getDateModified().toString());
            int r = preparedStatement.executeUpdate();
            System.out.println("U shtuan " + r + " rekorde");
        } catch (SQLException e) {
            System.out.println("Nuk u cua deri ne fund veprimi");
        }
    }

    @Override
    public PostCategory findById(Integer id) {
        try {
            PreparedStatement findPreparedStatement = getConnection().prepareStatement(FIND_BY_ID_STATEMENT);
            findPreparedStatement.setInt(1,id);
            ResultSet resultSet = findPreparedStatement.executeQuery();

            if (resultSet.next()) {
                return toPostCategory(resultSet);
            }

        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
        return null;
    }

    @Override
    public void update(PostCategory postCategory) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_BODY_STATEMENT);
            preparedStatement.setInt(1, postCategory.getPostId());
            preparedStatement.setInt(2, postCategory.getCategoryId());
            preparedStatement.setString(3, postCategory.getDateModified().toString());
            preparedStatement.setInt(4, postCategory.getId());

            int r = preparedStatement.executeUpdate();
            System.out.println(r + " rreshta u afektuan nga ky veprim update");
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
    }

    @Override
    public List<PostCategory> findAll() {
        try {
            PreparedStatement findPreparedStatement = getConnection().prepareStatement(FIND_ALL_STATEMENT);
            ResultSet resultSet = findPreparedStatement.executeQuery();
            List<PostCategory> posts = new ArrayList<>();
            while (resultSet.next()) {
                posts.add(toPostCategory(resultSet));
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
