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
    return null;
  }

  public Task toDomainFromEntity(TaskEntity taskEntity) {
    return null;
  }
}
