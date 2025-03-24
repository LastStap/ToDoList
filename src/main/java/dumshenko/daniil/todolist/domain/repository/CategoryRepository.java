package dumshenko.daniil.todolist.domain.repository;

import dumshenko.daniil.todolist.domain.model.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Category save(Category category);
    List<Category> findAll();
    Optional<Category> findById(UUID categoryId);
    Optional<Category> findByTitle(String title);
    void delete(Category category);
}
