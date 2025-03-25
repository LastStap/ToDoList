package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.domain.model.Task;
import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.domain.service.TaskService;
import dumshenko.daniil.todolist.domain.service.UserService;
import dumshenko.daniil.todolist.exception.TaskNotFoundException;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.mapper.TaskMapper;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;
  private final TaskMapper taskMapper;
  private final UserService userService;

  @Autowired
  public TaskController(TaskMapper taskMapper, TaskService taskService, UserService userService) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDTO) {
    User user = userService.getUserById(taskDTO.getUserId());

    Task task = taskMapper.toDomain(taskDTO, user);
    Task createdTask = taskService.createTask(task);

    TaskDto createdTaskDto = taskMapper.toDto(createdTask);
    return ResponseEntity.ok(createdTaskDto);
  }

  @GetMapping
  public ResponseEntity<List<TaskDto>> getTasks() {
    List<Task> tasks = taskService.getAllTasks();

    List<TaskDto> taskDtos = tasks.stream().map(taskMapper::toDto).toList();

    return ResponseEntity.ok(taskDtos);
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<TaskDto> getTask(@PathVariable UUID taskId) {
    Task task = taskService.getTaskById(taskId);

    TaskDto taskDto = taskMapper.toDto(task);

    return ResponseEntity.ok(taskDto);
  }

  @PutMapping("/{taskId}")
  public ResponseEntity<TaskDto> updateTask(
      @PathVariable UUID taskId, @RequestBody TaskDto taskDTO) {

    Task taskToUpdateFrom = taskMapper.toDomain(taskDTO);

    Task updatedTask = taskService.updateTask(taskId, taskToUpdateFrom);

    TaskDto updatedTaskDto = taskMapper.toDto(updatedTask);

    return ResponseEntity.ok(updatedTaskDto);
  }

  @DeleteMapping("/{taskId}")
  public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {

    taskService.deleteTask(taskId);

    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException e) {
    ErrorDto errorDto = new ErrorDto(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {
    ErrorDto errorDto = new ErrorDto("User not found");

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }
}
