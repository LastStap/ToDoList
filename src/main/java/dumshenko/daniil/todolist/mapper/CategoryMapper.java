package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.repository.entity.CategoryEntity;
import dumshenko.daniil.todolist.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public CategoryDto toCategoryDto(Category category) {
    CategoryDto categoryDto = new CategoryDto();

    categoryDto.setId(category.getId());
    categoryDto.setName(category.getName());
    categoryDto.setDescription(category.getDescription());
    return categoryDto;
  }


  public Category toDomainFromDto(CategoryDto categoryDto) {
    if (categoryDto == null) {
      return null;
    }

    Category category = new Category();
    category.setId(categoryDto.getId());
    category.setName(categoryDto.getName());
    category.setDescription(categoryDto.getDescription());
    return category;
  }


  public Category toDomainFromEntity(CategoryEntity categoryEntity) {
    Category category = new Category();

    category.setId(categoryEntity.getId().toString());
    category.setName(categoryEntity.getName());
    category.setDescription(categoryEntity.getDescription());
    return category;
  }
}
