package repository;

import model.Post;
import model.User;

import java.util.List;

public interface UserRepository {
    void save(User category);

    User findById(Integer id);

    void update(User category);

    List<User> findAll();

    void delete(Integer id);
}
