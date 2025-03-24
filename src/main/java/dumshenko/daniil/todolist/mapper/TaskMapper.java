package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.controller.dto.TaskPriorityDto;
import dumshenko.daniil.todolist.controller.dto.TaskStatusDto;
import dumshenko.daniil.todolist.domain.model.Task;
import dumshenko.daniil.todolist.domain.model.TaskPriority;
import dumshenko.daniil.todolist.domain.model.TaskStatus;
import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.domain.repository.UserRepository;
import dumshenko.daniil.todolist.repository.entity.TaskEntity;
import dumshenko.daniil.todolist.repository.entity.UserEntity;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  private TaskStatusDto convertStatusToDto(TaskStatus status) {
    return status != null ? TaskStatusDto.valueOf(status.name()) : null;
  }

  private TaskPriorityDto convertPriorityToDto(TaskPriority priority) {
    return priority != null ? TaskPriorityDto.valueOf(priority.name()) : null;
  }

  private TaskStatus convertStatusToDomain(TaskStatusDto statusDto) {
    return statusDto != null ? TaskStatus.valueOf(statusDto.name()) : null;
  }

  private TaskPriority convertPriorityToDomain(TaskPriorityDto priorityDto) {
    return priorityDto != null ? TaskPriority.valueOf(priorityDto.name()) : null;
  }

  private TaskStatus convertStatusToDomain(
      dumshenko.daniil.todolist.repository.entity.TaskStatus statusEntity) {
    return statusEntity != null ? TaskStatus.valueOf(statusEntity.name()) : null;
  }

  private TaskPriority convertPriorityToDomain(
      dumshenko.daniil.todolist.repository.entity.TaskPriority priorityEntity) {
    return priorityEntity != null ? TaskPriority.valueOf(priorityEntity.name()) : null;
  }

  private dumshenko.daniil.todolist.repository.entity.TaskStatus convertStatusToEntity(
      TaskStatus status) {
    return status != null
        ? dumshenko.daniil.todolist.repository.entity.TaskStatus.valueOf(status.name())
        : null;
  }

  private dumshenko.daniil.todolist.repository.entity.TaskPriority convertPriorityToEntity(
      TaskPriority priority) {
    return priority != null
        ? dumshenko.daniil.todolist.repository.entity.TaskPriority.valueOf(priority.name())
        : null;
  }

  public TaskDto toDto(Task task) {
    return new TaskDto(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        convertStatusToDto(task.getStatus()),
        convertPriorityToDto(task.getPriority()),
        task.getDueDate(),
        task.getCreatedAt(),
        task.getUserId());
  }

  public Task toDomain(TaskDto taskDto) {
    return new Task(
        null,
        taskDto.getTitle(),
        taskDto.getDescription(),
        convertStatusToDomain(taskDto.getStatus()),
        convertPriorityToDomain(taskDto.getPriority()),
        taskDto.getDueDate(),
        taskDto.getCreatedAt(),
        taskDto.getUserId());
  }

  public Task toDomain(TaskDto taskDto, UUID taskId) {
    return new Task(
        taskDto.getId(),
        taskDto.getTitle(),
        taskDto.getDescription(),
        convertStatusToDomain(taskDto.getStatus()),
        convertPriorityToDomain(taskDto.getPriority()),
        taskDto.getDueDate(),
        taskDto.getCreatedAt(),
        taskDto.getUserId());
  }

  public Task toDomain(TaskEntity taskEntity) {
    return new Task(
        taskEntity.getId(),
        taskEntity.getTitle(),
        taskEntity.getDescription(),
        convertStatusToDomain(taskEntity.getStatus()),
        convertPriorityToDomain(taskEntity.getPriority()),
        taskEntity.getDueDate(),
        taskEntity.getCreatedAt(),
        taskEntity.getUserEntity().getId());
  }

  public TaskEntity toEntity(Task task, UserRepository userRepository) {
    User user = userRepository
            .findById(task.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    UserEntity userEntity = new UserEntity();
    userEntity.setId(user.getId());

    return new TaskEntity(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            convertStatusToEntity(task.getStatus()),
            convertPriorityToEntity(task.getPriority()),
            task.getDueDate(),
            task.getCreatedAt(),
            userEntity
    );
  }

}
