package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import dumshenko.daniil.todolist.repository.entity.CategoryEntity;
import dumshenko.daniil.todolist.service.domain.Category;

public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);
    Category toDomainFromDto(CategoryDto categoryDto);
    Category toDomainFromEntity(CategoryEntity categoryEntity);
}
