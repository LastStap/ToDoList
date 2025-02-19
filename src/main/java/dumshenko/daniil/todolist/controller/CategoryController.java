package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.exception.CategoryNotFoundException;
import dumshenko.daniil.todolist.service.CategoryService;
import dumshenko.daniil.todolist.service.domain.Category;
import dumshenko.daniil.todolist.util.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDTO) {
        Category createdCategory = categoryService.createCategory(categoryDTO.getName(), categoryDTO.getDescription());
        CategoryDto createdCategoryDto = categoryMapper.toCategoryDto(createdCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<Category> allCategoriesList = categoryService.getAllCategories();

        List<CategoryDto> categoryDtoList = allCategoriesList.stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoList);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId) throws CategoryNotFoundException {

        Category categoryById = categoryService.getCategoryById(categoryId);
        CategoryDto categoryDto = categoryMapper.toCategoryDto(categoryById);

        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDTO) throws CategoryNotFoundException {
        Category category = categoryMapper.toDomainFromDto(categoryDTO);
        Category updatedCategory = categoryService.updateCategory(categoryId, category);
        CategoryDto updatedCategoryDto = categoryMapper.toCategoryDto(updatedCategory);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) throws CategoryNotFoundException {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCategoryNotFoundException(CategoryNotFoundException e) {

        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
