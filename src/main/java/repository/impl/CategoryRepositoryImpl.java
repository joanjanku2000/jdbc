package repository.impl;

import model.Category;
import repository.CategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static db.DbConnection.getConnection;

public class CategoryRepositoryImpl implements CategoryRepository {
    private static final String INSERT_STATEMENT = "insert into categories(name,date_created,date_modified) values(?,?,?);";
    private static final String UPDATE_STATEMENT = "update categories set name = ? , date_modified = ? where id = ?";
    private static final String FIND_ALL_STATEMENT = "select * from categories";
    private static final String FIND_BY_ID_STATEMENT = FIND_ALL_STATEMENT + " where id = ?";
    private static final String DELETE_STATEMENT = "delete from categories where id = ?";

    private static Category toCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setDateCreated(LocalDate.parse(resultSet.getString("date_created")));
        category.setDateModified(LocalDate.parse(resultSet.getString("date_modified")));
        return category;
    }

    @Override
    public void save(Category category) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_STATEMENT);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDateCreated().toString());
            preparedStatement.setString(3, category.getDateModified().toString());
            int r = preparedStatement.executeUpdate();
            System.out.println("U shtuan " + r + " rekorde");
        } catch (SQLException e) {
            System.out.println("Nuk u cua deri ne fund veprimi");
        }
    }

    @Override
    public Category findById(Integer id) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return toCategory(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }

        return null;
    }

    @Override
    public void update(Category category) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDateModified().toString());
            preparedStatement.setInt(3, category.getId());
            int r = preparedStatement.executeUpdate();
            System.out.println(r + " rreshta u afektuan nga ky veprim update");
        } catch (SQLException e) {
            System.out.println("Smth went wrong");
        }
    }

    @Override
    public List<Category> findAll() {
        try {
            PreparedStatement findPreparedStatement = getConnection().prepareStatement(FIND_ALL_STATEMENT);
            ResultSet resultSet = findPreparedStatement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = toCategory(resultSet);
                categories.add(category);
            }
            return categories;
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
