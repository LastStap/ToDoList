package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.CategoryNotFoundException;
import dumshenko.daniil.todolist.service.domain.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(String name, String description);

    List<Category> getAllCategories();

    Category getCategoryById(String categoryId) throws CategoryNotFoundException;

    Category updateCategory(String categoryId, Category category) throws CategoryNotFoundException;

    void deleteCategory(String categoryId) throws CategoryNotFoundException;

}
