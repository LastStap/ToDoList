package dumshenko.daniil.todolist.controller;


import dumshenko.daniil.todolist.controller.dto.CreateTaskDTO;
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
    public ResponseEntity<TaskDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(createTaskDTO.getTitle());
        taskDTO.setDescription(createTaskDTO.getDescription());
        taskDTO.setStatus(createTaskDTO.getStatus());
        taskDTO.setPriority(createTaskDTO.getPriority());
        taskDTO.setDueDate(createTaskDTO.getDueDate());
        taskDTO.setCreatedAt(now.toString());

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
