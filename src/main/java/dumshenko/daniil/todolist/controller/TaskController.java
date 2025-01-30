package dumshenko.daniil.todolist.controller;


import dumshenko.daniil.todolist.controller.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final Map<String, TaskDTO> tasksMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskDTO> tasks = new ArrayList<>(tasksMap.values());
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        taskDTO.setId(id);
        taskDTO.setTitle(taskDTO.getTitle());
        taskDTO.setDescription(taskDTO.getDescription());
        taskDTO.setStatus(taskDTO.getStatus());
        taskDTO.setPriority(taskDTO.getPriority());
        taskDTO.setDueDate(taskDTO.getDueDate());
        taskDTO.setCreatedAt(now.toString());

        if(taskDTO.getUserId() != null) {
            taskDTO.setUserId(null);
        }

        tasksMap.put(id, taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDTO);
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
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable String taskId) {
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
