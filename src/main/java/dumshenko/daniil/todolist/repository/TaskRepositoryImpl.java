package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.domain.model.Task;
import dumshenko.daniil.todolist.domain.repository.TaskRepository;
import dumshenko.daniil.todolist.mapper.TaskMapper;
import dumshenko.daniil.todolist.repository.entity.TaskEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

  private final TaskJpaRepository taskJpaRepository;
  private final TaskMapper taskMapper;

  @Autowired
  public TaskRepositoryImpl(TaskMapper taskMapper, TaskJpaRepository taskJpaRepository) {
    this.taskMapper = taskMapper;
    this.taskJpaRepository = taskJpaRepository;
  }

  @Override
  public Task save(Task task) {
    TaskEntity taskEntity = taskMapper.toEntity(task);

    TaskEntity savedTaskEntity = taskJpaRepository.save(taskEntity);

    return taskMapper.toDomain(savedTaskEntity);
  }

  @Override
  public List<Task> findAll() {
    return taskJpaRepository.findAll().stream().map(taskMapper::toDomain).toList();
  }

  @Override
  public Optional<Task> findById(UUID taskId) {
    return taskJpaRepository.findById(taskId).map(taskMapper::toDomain);
  }

  @Override
  public Optional<Task> findByTitle(String title) {
    return taskJpaRepository.findByTitle(title).map(taskMapper::toDomain);
  }

  @Override
  public void delete(Task task) {
    taskJpaRepository.deleteById(task.getId());
  }
}
