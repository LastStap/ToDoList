package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.domain.model.Subtask;
import dumshenko.daniil.todolist.domain.service.SubtaskService;
import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import dumshenko.daniil.todolist.mapper.SubtaskMapper;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtasks")
public class SubtaskController {

  private final SubtaskService subtaskService;
  private final SubtaskMapper subtaskMapper;

  @Autowired
  public SubtaskController(SubtaskMapper subtaskMapper, SubtaskService subtaskService) {
    this.subtaskService = subtaskService;
    this.subtaskMapper = subtaskMapper;
  }

  @PostMapping
  public ResponseEntity<SubtaskDto> createSubtask(@RequestBody SubtaskDto subtaskDTO) {
    Subtask subtask = subtaskMapper.toDomain(subtaskDTO);
    Subtask createdSubtask = subtaskService.createSubtask(subtask);

    SubtaskDto createdSubtaskDto = subtaskMapper.toDto(createdSubtask);
    return ResponseEntity.ok(createdSubtaskDto);
  }

  @GetMapping
  public ResponseEntity<List<SubtaskDto>> getSubtasks() {
    List<Subtask> subtasks = subtaskService.getAllSubtasks();

    List<SubtaskDto> subtaskDtos = subtasks.stream().map(subtaskMapper::toDto).toList();

    return ResponseEntity.ok(subtaskDtos);
  }

  @GetMapping("/{subtaskId}")
  public ResponseEntity<SubtaskDto> getSubtask(@PathVariable UUID subtaskId) {
    Subtask subtask = subtaskService.getSubtaskById(subtaskId);

    SubtaskDto subtaskDto = subtaskMapper.toDto(subtask);

    return ResponseEntity.ok(subtaskDto);
  }

  @PutMapping("/{subtaskId}")
  public ResponseEntity<SubtaskDto> updateSubtask(
      @PathVariable UUID subtaskId, @RequestBody SubtaskDto subtaskDTO) {
    Subtask subtask = subtaskMapper.toDomain(subtaskDTO);

    Subtask updatedSubtask = subtaskService.updateSubtask(subtaskId, subtask);

    SubtaskDto updatedSubtaskDto = subtaskMapper.toDto(updatedSubtask);

    return ResponseEntity.ok(updatedSubtaskDto);
  }

  @DeleteMapping("/{subtaskId}")
  public ResponseEntity<Void> deleteSubtask(@PathVariable UUID subtaskId) {

    subtaskService.deleteSubtask(subtaskId);

    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(SubtaskNotFoundException.class)
  public ResponseEntity<ErrorDto> handleSubtaskNotFoundException(SubtaskNotFoundException e) {
    ErrorDto errorDto = new ErrorDto(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }
}
