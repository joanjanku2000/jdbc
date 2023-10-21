package repository;

import model.Category;
import model.PostCategory;

import java.util.List;

public interface PostCategoryRepository {
    void save(PostCategory category);

    PostCategory findById(Integer id);

    void update(PostCategory category);

    List<PostCategory> findAll();

    void delete(Integer id);
}
