package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.AttachmentDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.exception.AttachmentNotFoundException;
import dumshenko.daniil.todolist.domain.model.Attachment;

import java.util.*;

import dumshenko.daniil.todolist.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

  private final AttachmentMapper attachmentMapper;

  @Autowired
  public AttachmentController(AttachmentMapper attachmentMapper) {
    this.attachmentMapper = attachmentMapper;
  }

  @PostMapping
  public ResponseEntity<AttachmentDto> createAttachment(@RequestBody AttachmentDto attachmentDTO) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<AttachmentDto>> getAttachments() {
    return null;
  }

  @GetMapping("/{attachmentId}")
  public ResponseEntity<AttachmentDto> getAttachment(@PathVariable String attachmentId) {
    return null;
  }

  @DeleteMapping("/{attachmentId}")
  public ResponseEntity<Void> deleteAttachment(@PathVariable String attachmentId) {
    return null;
  }

  @ExceptionHandler(AttachmentNotFoundException.class)
  public ResponseEntity<ErrorDto> handleAttachmentNotFoundException(AttachmentNotFoundException e) {
    return null;
  }
}
