package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.service.domain.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskDto toTaskDto(Task task) {
        TaskDto taskDTO = new TaskDto();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setCreatedAt(task.getCreatedAt());
        return taskDTO;
    }

    @Override
    public Task toDomain(TaskDto taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setDueDate(taskDTO.getDueDate());
        task.setCreatedAt(taskDTO.getCreatedAt());
        return task;
    }
}
