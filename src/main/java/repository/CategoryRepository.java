package repository;

import model.Category;

import java.util.List;

public interface CategoryRepository {

    void save(Category category);

    Category findById(Integer id);

    void update(Category category);

    List<Category> findAll();

    void delete(Integer id);

}
