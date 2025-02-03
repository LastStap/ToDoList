package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.exception.CategoryNotFoundException;
import dumshenko.daniil.todolist.service.domain.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final Map<String, Category> categoriesMap = new HashMap<>();

    @Override
    public Category createCategory(String name, String description) {
        String id = UUID.randomUUID().toString();

        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        if(category.getUserId() != null) {
            category.setUserId(null);
        }

        categoriesMap.put(id, category);
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return new ArrayList<>(categoriesMap.values());
    }

    @Override
    public Category getCategoryById(String categoryId) throws CategoryNotFoundException {
        Category category = categoriesMap.get(categoryId);

        if (category == null) {
            throw new CategoryNotFoundException("Category with categoryId: " + categoryId + " doesn't exist.");
        }
        return category;
    }

    @Override
    public Category updateCategory(String categoryId, Category category) throws CategoryNotFoundException {
        Category currentCategory = categoriesMap.get(categoryId);

        if(currentCategory == null) {
            throw new CategoryNotFoundException("Category with categoryId: " + categoryId + " doesn't exist.");
        }
        if(!category.getName().equals(currentCategory.getName())) {
            currentCategory.setName(category.getName());
        }
        if(!category.getDescription().equals(currentCategory.getDescription())) {
            currentCategory.setDescription(category.getDescription());
        }
        return categoriesMap.put(categoryId, currentCategory);
    }

    @Override
    public void deleteCategory(String categoryId) throws CategoryNotFoundException {
        if(categoriesMap.containsKey(categoryId)) {
            categoriesMap.remove(categoryId);
        } else {
            throw new CategoryNotFoundException("Category with categoryId: " + categoryId + " doesn't exist.");
        }
    }
}
