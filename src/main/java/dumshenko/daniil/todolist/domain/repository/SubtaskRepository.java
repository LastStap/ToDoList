package dumshenko.daniil.todolist.domain.repository;

import dumshenko.daniil.todolist.domain.model.Subtask;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubtaskRepository {
    Subtask save(Subtask subtask);
    List<Subtask> findAll();
    Optional<Subtask> findById(UUID subtaskId);
    Optional<Subtask> findByTitle(String title);
    void delete(Subtask subtask);
}
