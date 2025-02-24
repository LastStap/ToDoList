package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.controller.dto.TaskPriorityDto;
import dumshenko.daniil.todolist.controller.dto.TaskStatusDto;
import dumshenko.daniil.todolist.repository.entity.TaskEntity;
import dumshenko.daniil.todolist.domain.model.Task;
import dumshenko.daniil.todolist.domain.TaskPriority;
import dumshenko.daniil.todolist.domain.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  public TaskDto toTaskDto(Task task) {
    return null;
  }

  public Task toDomainFromDto(TaskDto taskDTO) {
    TaskStatus taskStatus;
    TaskPriority taskPriority;

    switch (taskDTO.getStatus()) {
      case PENDING -> taskStatus = TaskStatus.PENDING;
      case COMPLETED -> taskStatus = TaskStatus.COMPLETED;
      case IN_PROGRESS -> taskStatus = TaskStatus.IN_PROGRESS;
      default ->
          throw new RuntimeException("Cannot map " + taskDTO.getStatus().name() + " to TaskStatus");
    }

    switch (taskDTO.getPriority()) {
      case HIGH -> taskPriority = TaskPriority.HIGH;
      case MEDIUM -> taskPriority = TaskPriority.MEDIUM;
      case LOW -> taskPriority = TaskPriority.LOW;
      default ->
          throw new RuntimeException(
              "Cannot map " + taskDTO.getPriority().name() + " to TaskPriority");
    }

    if (taskDTO == null) {
      return null;
    }
    Task task = new Task();
    task.setId(taskDTO.getId());
    task.setTitle(taskDTO.getTitle());
    task.setDescription(taskDTO.getDescription());
    TaskStatus.valueOf(taskDTO.getStatus().name());
    TaskPriority.valueOf(taskDTO.getPriority().name());
    task.setDueDate(taskDTO.getDueDate());
    task.setCreatedAt(taskDTO.getCreatedAt());
    return task;
  }

  public Task toDomainFromEntity(TaskEntity taskEntity) {
    Task task = new Task();

    task.setId(taskEntity.getId());
    task.setTitle(taskEntity.getTitle());
    task.setDescription(taskEntity.getDescription());
    TaskStatus.valueOf(taskEntity.getStatus().name());
    TaskPriority.valueOf(taskEntity.getPriority().name());
    task.setDueDate(taskEntity.getDueDate());
    task.setCreatedAt(taskEntity.getCreatedAt());
    return task;
  }
}
