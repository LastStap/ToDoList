package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.domain.model.Category;
import dumshenko.daniil.todolist.domain.repository.CategoryRepository;
import dumshenko.daniil.todolist.mapper.CategoryMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dumshenko.daniil.todolist.repository.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

  private final CategoryMapper categoryMapper;
  private final CategoryJpaRepository categoryJpaRepository;

  @Autowired
  public CategoryRepositoryImpl(CategoryMapper categoryMapper, CategoryJpaRepository categoryJpaRepository) {
    this.categoryMapper = categoryMapper;
    this.categoryJpaRepository = categoryJpaRepository;
  }

  @Override
  public Category save(Category category) {
    return null;
//    CategoryEntity categoryEntity = categoryMapper.toEntity(category);
//
//    CategoryEntity savedCategoryEntity = categoryJpaRepository.save(categoryEntity);
//
//    return categoryMapper.toDomain(savedCategoryEntity);
  }

  @Override
  public List<Category> findAll() {
    return categoryJpaRepository.findAll().stream().map(categoryMapper::toDomain).toList();
  }

  @Override
  public Optional<Category> findById(UUID categoryId) {
    return categoryJpaRepository.findById(categoryId).map(categoryMapper::toDomain);
  }

  @Override
  public void delete(Category category) {
    categoryJpaRepository.deleteById(category.getId());
  }
}
