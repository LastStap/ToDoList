package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.Category;
import dumshenko.daniil.todolist.domain.repository.CategoryRepository;
import dumshenko.daniil.todolist.exception.CategoryNotFoundException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(UUID categoryId) {
    Optional<Category> category = categoryRepository.findById(categoryId);

    return category.orElseThrow(() -> new CategoryNotFoundException(categoryId));
  }

  public Category updateCategory(UUID categoryId, Category categoryToUpdateForm) {
    Category category = getCategoryById(categoryId);

    category.update(categoryToUpdateForm);

    return categoryRepository.save(category);
  }

  public void deleteCategory(UUID categoryId) {
    Category category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));

    categoryRepository.delete(category);
  }
}
