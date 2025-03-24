package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.domain.model.Subtask;
import dumshenko.daniil.todolist.domain.repository.SubtaskRepository;
import dumshenko.daniil.todolist.mapper.SubtaskMapper;
import dumshenko.daniil.todolist.repository.entity.SubtaskEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubtaskRepositoryImpl implements SubtaskRepository {

  private final SubtaskJpaRepository subtaskJpaRepository;
  private final SubtaskMapper subtaskMapper;

  @Autowired
  public SubtaskRepositoryImpl(
      SubtaskMapper subtaskMapper, SubtaskJpaRepository subtaskJpaRepository) {
    this.subtaskJpaRepository = subtaskJpaRepository;
    this.subtaskMapper = subtaskMapper;
  }

  @Override
  public Subtask save(Subtask subtask) {
    SubtaskEntity subtaskEntity = subtaskMapper.toEntity(subtask);

    SubtaskEntity savedSubtaskEntity = subtaskJpaRepository.save(subtaskEntity);

    return subtaskMapper.toDomain(savedSubtaskEntity);
  }

  @Override
  public List<Subtask> findAll() {
    return subtaskJpaRepository.findAll().stream().map(subtaskMapper::toDomain).toList();
  }

  @Override
  public Optional<Subtask> findById(UUID subtaskId) {
    return subtaskJpaRepository.findById(subtaskId).map(subtaskMapper::toDomain);
  }

  @Override
  public Optional<Subtask> findByTitle(String title) {
    return subtaskJpaRepository.findByTitle(title).map(subtaskMapper::toDomain);
  }

  @Override
  public void delete(Subtask subtask) {
    subtaskJpaRepository.deleteById(subtask.getId());
  }
}
