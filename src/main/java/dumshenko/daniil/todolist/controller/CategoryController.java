package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.domain.model.Category;
import dumshenko.daniil.todolist.domain.service.CategoryService;
import dumshenko.daniil.todolist.exception.CategoryNotFoundException;
import dumshenko.daniil.todolist.mapper.CategoryMapper;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryMapper categoryMapper;
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryMapper categoryMapper, CategoryService categoryService) {
    this.categoryService = categoryService;
    this.categoryMapper = categoryMapper;
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDTO) {
    Category category = categoryMapper.toDomain(categoryDTO);
    Category savedCategory = categoryService.createCategory(category);

    CategoryDto createdCategoryDto = categoryMapper.toDto(savedCategory);
    return ResponseEntity.ok(createdCategoryDto);
  }

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getCategories() {
    List<Category> categories = categoryService.getAllCategories();

    List<CategoryDto> categoryDtos = categories.stream().map(categoryMapper::toDto).toList();

    return ResponseEntity.ok(categoryDtos);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getCategory(@PathVariable UUID categoryId) {
    Category category = categoryService.getCategoryById(categoryId);

    CategoryDto categoryDto = categoryMapper.toDto(category);

    return ResponseEntity.ok(categoryDto);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateCategory(
      @PathVariable UUID categoryId, @RequestBody CategoryDto categoryDTO) {
    Category category = categoryMapper.toDomain(categoryDTO);

    Category updatedCategory = categoryService.updateCategory(categoryId, category);

    CategoryDto updatedCategoryDto = categoryMapper.toDto(updatedCategory);

    return ResponseEntity.ok(updatedCategoryDto);
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId) {

    categoryService.deleteCategory(categoryId);

    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<ErrorDto> handleCategoryNotFoundException(CategoryNotFoundException e) {
    ErrorDto errorDto = new ErrorDto(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }
}
