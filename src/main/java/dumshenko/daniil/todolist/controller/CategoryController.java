package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.exception.CategoryNotFoundException;
import dumshenko.daniil.todolist.domain.model.Category;

import java.util.*;
import java.util.stream.Collectors;

import dumshenko.daniil.todolist.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryMapper categoryMapper;

  @Autowired
  public CategoryController(CategoryMapper categoryMapper) {

    this.categoryMapper = categoryMapper;
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDTO) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getCategories() {
    return null;
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId){
return null;
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDTO) {
    return null;
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
    return null;
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<ErrorDto> handleCategoryNotFoundException(CategoryNotFoundException e) {

    return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
  }
}
