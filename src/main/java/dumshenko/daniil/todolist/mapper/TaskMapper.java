package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.controller.dto.TaskPriorityDto;
import dumshenko.daniil.todolist.controller.dto.TaskStatusDto;
import dumshenko.daniil.todolist.domain.model.Task;
import dumshenko.daniil.todolist.domain.model.TaskPriority;
import dumshenko.daniil.todolist.domain.model.TaskStatus;
import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.repository.entity.CategoryEntity;
import dumshenko.daniil.todolist.repository.entity.TaskEntity;
import dumshenko.daniil.todolist.repository.entity.TaskPriorityEntity;

import java.util.List;
import java.util.UUID;

import dumshenko.daniil.todolist.repository.entity.TaskStatusEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final UserMapper userMapper;

    @Autowired
    public TaskMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                TaskStatusDto.valueOf(task.getStatus().name()),
                TaskPriorityDto.valueOf(task.getPriority().name()),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUser().getId());
    }

    public Task toDomain(TaskDto taskDto, User user) {
        return new Task(
                null,
                taskDto.getTitle(),
                taskDto.getDescription(),
                TaskStatus.valueOf(taskDto.getStatus().name()),
                TaskPriority.valueOf(taskDto.getPriority().name()),
                taskDto.getDueDate(),
                taskDto.getCreatedAt(),
                user);
    }

    public Task toDomain(TaskDto taskDto) {
        return new Task(
                null,
                taskDto.getTitle(),
                taskDto.getDescription(),
                TaskStatus.valueOf(taskDto.getStatus().name()),
                TaskPriority.valueOf(taskDto.getPriority().name()),
                taskDto.getDueDate(),
                taskDto.getCreatedAt(),
                null);
    }

    public Task toDomain(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                TaskStatus.valueOf(taskEntity.getStatus().name()),
                TaskPriority.valueOf(taskEntity.getPriority().name()),
                taskEntity.getDueDate(),
                taskEntity.getCreatedAt(),
                userMapper.toDomain(taskEntity.getUserEntity()));
    }

    public TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                TaskStatusEntity.valueOf(task.getStatus().name()),
                TaskPriorityEntity.valueOf(task.getPriority().name()),
                task.getDueDate(),
                task.getCreatedAt(),
                userMapper.toEntity(task.getUser())
        );
    }

}
