package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.exception.CategoryNotFoundException;
import dumshenko.daniil.todolist.domain.model.Category;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryService{



  public Category createCategory(String name, String description) {
    return null;
  }


  public List<Category> getAllCategories() {
    return null;
  }


  public Category getCategoryById(String categoryId) {
    return null;
  }


  public Category updateCategory(String categoryId, Category category) {
    return null;
  }


  public void deleteCategory(String categoryId) throws CategoryNotFoundException {

  }
}
