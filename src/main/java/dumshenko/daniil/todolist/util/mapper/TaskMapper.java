package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.TaskDTO;
import dumshenko.daniil.todolist.service.domain.Task;

public interface TaskMapper {
    TaskDTO toTaskDto(Task task);
    Task toDomain(TaskDTO taskDTO);
}
