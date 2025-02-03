package dumshenko.daniil.todolist.controller;


import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.TaskDto;
import dumshenko.daniil.todolist.exception.TaskNotFoundException;
import dumshenko.daniil.todolist.service.TaskService;
import dumshenko.daniil.todolist.service.domain.Task;
import dumshenko.daniil.todolist.util.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDTO) {
        Task createdTask = taskService.createTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getPriority(), taskDTO.getDueDate());
        TaskDto createdTaskDto = taskMapper.toTaskDto(createdTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDto);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> allTasksList = taskService.getAllTasks();

        List<TaskDto> taskDtoList = allTasksList.stream()
                .map(taskMapper::toTaskDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(taskDtoList);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable String taskId) throws TaskNotFoundException {

        Task taskById = taskService.getTaskById(taskId);
        TaskDto taskDto = taskMapper.toTaskDto(taskById);

        return ResponseEntity.status(HttpStatus.OK).body(taskDto);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable String taskId, @RequestBody TaskDto taskDTO) throws TaskNotFoundException {
        Task task = taskMapper.toDomain(taskDTO);
        Task updatedTask = taskService.updateTask(taskId, task);
        TaskDto updatedTaskDto = taskMapper.toTaskDto(updatedTask);

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
