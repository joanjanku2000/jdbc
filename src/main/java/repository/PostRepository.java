package repository;

import model.Post;
import model.PostCategory;

import java.util.List;

public interface PostRepository {

    void save(Post category);

    Post findById(Integer id);

    void update(Post category);

    List<Post> findAll();

    void delete(Integer id);

}
