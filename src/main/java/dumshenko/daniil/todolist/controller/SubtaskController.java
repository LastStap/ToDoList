package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.SubtaskDTO;
import dumshenko.daniil.todolist.service.SubtaskService;
import dumshenko.daniil.todolist.util.mapper.SubtaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/subtasks")
public class SubtaskController {

    private final SubtaskService subtaskService;
    private final SubtaskMapper subtaskMapper;

    @Autowired
    public SubtaskController(SubtaskService subtaskService, SubtaskMapper subtaskMapper) {
        this.subtaskService = subtaskService;
        this.subtaskMapper = subtaskMapper;
    }

    @PostMapping
    public ResponseEntity<SubtaskDTO> createSubtask(@RequestBody SubtaskDTO subtaskDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskDTO);
    }

    @GetMapping
    public ResponseEntity<List<SubtaskDTO>> getSubtasks() {
        List<SubtaskDTO> subtaskDTOList = new ArrayList<>(subtasksMap.values());
        return ResponseEntity.status(HttpStatus.OK).body(subtaskDTOList);
    }

    @GetMapping("/{subtaskId}")
    public ResponseEntity<SubtaskDTO> getSubtask(@PathVariable String subtaskId) {
        SubtaskDTO subtaskDTO = subtasksMap.get(subtaskId);
        if (subtaskDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(subtaskDTO);
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

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable String subtaskId) {
        if (subtasksMap.containsKey(subtaskId)) {
            subtasksMap.remove(subtaskId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

