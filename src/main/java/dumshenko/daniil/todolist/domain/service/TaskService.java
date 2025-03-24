package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.Task;
import dumshenko.daniil.todolist.domain.repository.TaskRepository;
import dumshenko.daniil.todolist.exception.TaskNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(@Qualifier("taskRepositoryImpl") TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task createTask(Task task) {
    task.setCreatedAt(Instant.now());
    task.setDueDate(Instant.now().plus(Duration.ofDays(7)));

    return taskRepository.save(task);
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task getTaskById(UUID taskId) {
    Optional<Task> task = taskRepository.findById(taskId);

    return task.orElseThrow(() -> new TaskNotFoundException(taskId));
  }

  public Task updateTask(UUID taskId, Task taskToUpdateForm) {
    Task task = getTaskById(taskId);

    task.update(taskToUpdateForm);

    return taskRepository.save(task);
  }

  public void deleteTask(UUID taskId) {
    Task task =
        taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

    taskRepository.delete(task);
  }
}
