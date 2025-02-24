package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.exception.TaskNotFoundException;


import java.util.*;

import dumshenko.daniil.todolist.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskMapper taskMapper;

  @Autowired
  public TaskController(TaskMapper taskMapper) {
    this.taskMapper = taskMapper;
  }

  @PostMapping
  public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDTO) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<TaskDto>> getTasks() {
    return null;
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<TaskDto> getTask(@PathVariable String taskId) throws TaskNotFoundException {
    return null;
  }

  @PutMapping("/{taskId}")
  public ResponseEntity<TaskDto> updateTask(@PathVariable String taskId, @RequestBody TaskDto taskDTO) throws TaskNotFoundException {
    return null;
  }

  @DeleteMapping("/{taskId}")
  public ResponseEntity<Void> deleteTask(@PathVariable String taskId) throws TaskNotFoundException {
    return null;
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException e) {

    return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
  }
}
