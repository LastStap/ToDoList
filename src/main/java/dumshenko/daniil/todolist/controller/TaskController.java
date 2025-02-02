package dumshenko.daniil.todolist.controller;


import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.TaskDTO;
import dumshenko.daniil.todolist.exception.TaskNotFoundException;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.service.TaskService;
import dumshenko.daniil.todolist.service.domain.Task;
import dumshenko.daniil.todolist.util.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getPriority(), taskDTO.getDueDate());
        TaskDTO createdTaskDto = taskMapper.toTaskDto(createdTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDto);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<Task> allTasksList = taskService.getAllTasks();

        List<TaskDTO> taskDTOList = allTasksList.stream()
                .map(taskMapper::toTaskDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(taskDTOList);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable String taskId) throws TaskNotFoundException {

        Task taskById = taskService.getTaskById(taskId);
        TaskDTO taskDto = taskMapper.toTaskDto(taskById);

        return ResponseEntity.status(HttpStatus.OK).body(taskDto);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String taskId, @RequestBody TaskDTO taskDTO) throws TaskNotFoundException {
        Task task = taskMapper.toDomain(taskDTO);
        Task updatedTask = taskService.updateTask(taskId, task);
        TaskDTO updatedTaskDto = taskMapper.toTaskDto(updatedTask);

        return ResponseEntity.status(HttpStatus.OK).body(updatedTaskDto);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) throws TaskNotFoundException {

        taskService.deleteTask(taskId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException e) {

        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
