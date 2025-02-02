package dumshenko.daniil.todolist.controller;


import dumshenko.daniil.todolist.controller.dto.TaskDTO;
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
    public ResponseEntity<TaskDTO> getTask(@PathVariable String taskId) {
        TaskDTO taskDTO = tasksMap.get(taskId);
        if (taskDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        if(tasksMap.containsKey(taskId)) {
            tasksMap.remove(taskId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String taskId, @RequestBody TaskDTO taskDTO) {
        Instant now = Instant.now();

        TaskDTO currentTask = tasksMap.get(taskId);

        if (currentTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!taskDTO.getTitle().equals(currentTask.getTitle())) {
            currentTask.setTitle(taskDTO.getTitle());
        }
        if (!taskDTO.getDescription().equals(currentTask.getDescription())) {
            currentTask.setDescription(taskDTO.getDescription());
        }
        if (!taskDTO.getStatus().equals(currentTask.getStatus())){
            currentTask.setStatus(taskDTO.getStatus());
        }
        if (!taskDTO.getPriority().equals(currentTask.getPriority())) {
            currentTask.setPriority(taskDTO.getPriority());
        }
        if (!taskDTO.getDueDate().equals(currentTask.getDueDate())) {
            currentTask.setDueDate(taskDTO.getDueDate());
        }
        currentTask.setUpdatedAt(now.toString());
        tasksMap.put(taskId, currentTask);
        return ResponseEntity.status(HttpStatus.OK).body(currentTask);
    }

}
