package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.Subtask;
import dumshenko.daniil.todolist.domain.repository.SubtaskRepository;
import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import java.time.Instant;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubtaskService {

  private final SubtaskRepository subtaskRepository;

  @Autowired
  public SubtaskService(SubtaskRepository subtaskRepository) {
    this.subtaskRepository = subtaskRepository;
  }

  public Subtask createSubtask(Subtask subtask) {
    subtask.setCreatedAt(Instant.now());

    return subtaskRepository.save(subtask);
  }

  public List<Subtask> getAllSubtasks() {
    return subtaskRepository.findAll();
  }

  public Subtask getSubtaskById(UUID subtaskId) {
    Optional<Subtask> subtask = subtaskRepository.findById(subtaskId);

    return subtask.orElseThrow(() -> new SubtaskNotFoundException(subtaskId));
  }

  public Subtask updateSubtask(UUID subtaskId, Subtask subtaskToUpdateForm) {
    Subtask subtask = getSubtaskById(subtaskId);

    subtask.update(subtaskToUpdateForm);

    return subtaskRepository.save(subtask);
  }

  public void deleteSubtask(UUID subtaskId) {
    Subtask subtask =
        subtaskRepository
            .findById(subtaskId)
            .orElseThrow(() -> new SubtaskNotFoundException(subtaskId));

    subtaskRepository.delete(subtask);
  }
}
