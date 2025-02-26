package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import dumshenko.daniil.todolist.domain.model.Subtask;

import java.util.*;
import java.util.stream.Collectors;

import dumshenko.daniil.todolist.mapper.SubtaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtasks")
public class SubtaskController {

  private final SubtaskMapper subtaskMapper;

  @Autowired
  public SubtaskController(SubtaskMapper subtaskMapper) {
    this.subtaskMapper = subtaskMapper;
  }

  @PostMapping
  public ResponseEntity<SubtaskDto> createSubtask(@RequestBody SubtaskDto subtaskDTO) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<SubtaskDto>> getSubtasks() {
    return null;
  }

  @GetMapping("/{subtaskId}")
  public ResponseEntity<SubtaskDto> getSubtask(@PathVariable String subtaskId) {
    return null;
  }

  @PutMapping("/{subtaskId}")
  public ResponseEntity<SubtaskDto> updateSubtask(@PathVariable String subtaskId, @RequestBody SubtaskDto subtaskDTO) {
    return null;
  }

  @DeleteMapping("/{subtaskId}")
  public ResponseEntity<Void> deleteSubtask(@PathVariable String subtaskId) {
    return null;
  }

  @ExceptionHandler(SubtaskNotFoundException.class)
  public ResponseEntity<ErrorDto> handleSubtaskNotFoundException(SubtaskNotFoundException e) {

    return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
  }
}
