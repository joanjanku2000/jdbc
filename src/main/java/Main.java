import model.Category;
import model.Post;
import model.PostCategory;
import model.User;
import repository.CategoryRepository;
import repository.PostCategoryRepository;
import repository.PostRepository;
import repository.UserRepository;
import repository.impl.CategoryRepositoryImpl;
import repository.impl.PostCategoryRepositoryImpl;
import repository.impl.PostRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        PostRepository postRepository = new PostRepositoryImpl();
        PostCategoryRepository postCategoryRepository = new PostCategoryRepositoryImpl();

        Category category = new Category();
        category.setName("Test5");
        category.setDateCreated(LocalDate.now());
        category.setDateModified(LocalDate.now());
        categoryRepository.save(category);

        User user = new User();
        user.setEmail("joan@gmail.com");
        user.setPassword("joan@gmail.com");
        user.setUsername("jjanku");
        user.setDateCreated(LocalDate.now());
        user.setDateModified(LocalDate.now());

        userRepository.save(user);

        Post post = new Post();
        post.setTitle("hello world");
        post.setBody("Hello world 3");
        post.setUserId(5);
        post.setDateCreated(LocalDate.now());
        post.setDateModified(LocalDate.now());

        postRepository.save(post);

        PostCategory postCategory = new PostCategory();
        postCategory.setPostId(1);
        postCategory.setCategoryId(1);
        postCategory.setDateCreated(LocalDate.now());
        postCategory.setDateModified(LocalDate.now());

        postCategoryRepository.save(postCategory);





        System.out.println(categoryRepository.findAll());

    }
}
