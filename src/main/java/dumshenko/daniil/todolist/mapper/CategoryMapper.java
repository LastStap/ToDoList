package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.repository.entity.CategoryEntity;
import dumshenko.daniil.todolist.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public CategoryDto toCategoryDto(Category category) {
return null;
  }


  public Category toDomainFromDto(CategoryDto categoryDto) {
    return null;
  }


  public Category toDomainFromEntity(CategoryEntity categoryEntity) {
    return null;
  }
}
