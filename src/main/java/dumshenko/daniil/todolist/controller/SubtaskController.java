package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.SubtaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/subtasks")
public class SubtaskController {

    private final Map<String, SubtaskDTO> subtasksMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<SubtaskDTO>> getSubtasks() {
        List<SubtaskDTO> subtaskDTOList = new ArrayList<>(subtasksMap.values());
        return ResponseEntity.status(HttpStatus.OK).body(subtaskDTOList);
    }

    @PostMapping
    public ResponseEntity<SubtaskDTO> createSubtask(@RequestBody SubtaskDTO subtaskDTO) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        subtaskDTO.setId(id);
        subtaskDTO.setTitle(subtaskDTO.getTitle());
        subtaskDTO.setStatus(subtaskDTO.getStatus());
        subtaskDTO.setCreatedAt(now.toString());

        if(subtaskDTO.getTaskId() != null) {
            subtaskDTO.setTaskId(null);
        }

        subtasksMap.put(id, subtaskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskDTO);
    }

    @GetMapping("/{subtaskId}")
    public ResponseEntity<SubtaskDTO> getSubtask(@PathVariable String subtaskId) {
        SubtaskDTO subtaskDTO = subtasksMap.get(subtaskId);
        if (subtaskDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(subtaskDTO);
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable String subtaskId) {
        if (subtasksMap.containsKey(subtaskId)) {
            subtasksMap.remove(subtaskId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{subtaskId}")
    public ResponseEntity<SubtaskDTO> updateSubtask(@PathVariable String subtaskId, @RequestBody SubtaskDTO subtaskDTO) {
        Instant now = Instant.now();

        SubtaskDTO currentSubtask = subtasksMap.get(subtaskId);

        if (currentSubtask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!subtaskDTO.getTitle().equals(currentSubtask.getTitle())) {
            currentSubtask.setTitle(subtaskDTO.getTitle());
        }
        if (!subtaskDTO.getStatus().equals(currentSubtask.getStatus())) {
            currentSubtask.setStatus(subtaskDTO.getStatus());
        }
        currentSubtask.setUpdatedAt(now.toString());
        subtasksMap.put(subtaskId, currentSubtask);
        return ResponseEntity.status(HttpStatus.OK).body(currentSubtask);
    }
}

