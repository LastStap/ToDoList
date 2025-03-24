package dumshenko.daniil.todolist.domain.repository;

import dumshenko.daniil.todolist.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(UUID taskId);
    Optional<Task> findByTitle(String title);
    void delete(Task task);
}
