package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.domain.model.Category;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import dumshenko.daniil.todolist.repository.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public CategoryDto toDto(Category category) {
    return new CategoryDto(
        category.getId(), category.getName(), category.getDescription(), category.getUserId());
  }

  public Category toDomain(CategoryDto categoryDto) {
    return new Category(
        null, categoryDto.getName(), categoryDto.getDescription(), categoryDto.getUserId());
  }

  public Category toDomain(UUID categoryId, CategoryDto categoryDto) {
    return new Category(
        categoryDto.getId(),
        categoryDto.getName(),
        categoryDto.getDescription(),
        categoryDto.getUserId());
  }

  public Category toDomain(CategoryEntity categoryEntity) {
    return new Category(
            categoryEntity.getId(),
            categoryEntity.getName(),
            categoryEntity.getDescription(),
            categoryEntity.getUserEntity().getId()
    );
  }

  public CategoryEntity toEntity(Category category) {
    return new CategoryEntity(
            category.getId(),
            category.getName(),
            category.getDescription(),
            category.getUserId()
    );
  }
}
