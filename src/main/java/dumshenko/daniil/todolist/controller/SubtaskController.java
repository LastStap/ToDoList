package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.SubtaskDTO;
import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.service.SubtaskService;
import dumshenko.daniil.todolist.service.domain.Subtask;
import dumshenko.daniil.todolist.util.mapper.SubtaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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

        Subtask createdSubtask = subtaskService.createSubtask(subtaskDTO.getTitle(), subtaskDTO.getStatus());
        SubtaskDTO createdSubtaskDto = subtaskMapper.toSubtaskDto(createdSubtask);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubtaskDto);
    }

    @GetMapping
    public ResponseEntity<List<SubtaskDTO>> getSubtasks() {
        List<Subtask> allSubtasksList = subtaskService.getAllSubtasks();

        List<SubtaskDTO> subtaskDTOList = allSubtasksList.stream()
                .map(subtaskMapper::toSubtaskDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(subtaskDTOList);
    }

    @GetMapping("/{subtaskId}")
    public ResponseEntity<SubtaskDTO> getSubtask(@PathVariable String subtaskId) {
        Subtask subtaskById = subtaskService.getSubtaskById(subtaskId);
        SubtaskDTO subtaskDto = subtaskMapper.toSubtaskDto(subtaskById);

        return ResponseEntity.status(HttpStatus.OK).body(subtaskDto);
    }

    @PutMapping("/{subtaskId}")
    public ResponseEntity<SubtaskDTO> updateSubtask(@PathVariable String subtaskId, @RequestBody SubtaskDTO subtaskDTO) {

        Subtask subtask = subtaskMapper.toDomain(subtaskDTO);
        Subtask updatedSubtask = subtaskService.updateSubtask(subtaskId, subtask);
        SubtaskDTO updatedSubtaskDto = subtaskMapper.toSubtaskDto(updatedSubtask);

        return ResponseEntity.status(HttpStatus.OK).body(updatedSubtaskDto);
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable String subtaskId) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(SubtaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleSubtaskNotFoundException(SubtaskNotFoundException e) {

        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}

