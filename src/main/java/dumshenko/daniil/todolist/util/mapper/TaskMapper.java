package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.service.domain.Task;

public interface TaskMapper {
    TaskDto toTaskDto(Task task);
    Task toDomain(TaskDto taskDTO);
}
