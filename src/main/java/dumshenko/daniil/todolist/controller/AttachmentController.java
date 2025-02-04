package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.AttachmentDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.exception.AttachmentNotFoundException;
import dumshenko.daniil.todolist.service.AttachmentService;
import dumshenko.daniil.todolist.service.TaskService;
import dumshenko.daniil.todolist.service.domain.Attachment;
import dumshenko.daniil.todolist.util.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;
    private final AttachmentMapper attachmentMapper;

    @Autowired
    public AttachmentController(AttachmentService attachmentService, AttachmentMapper attachmentMapper) {
        this.attachmentService = attachmentService;
        this.attachmentMapper = attachmentMapper;
    }

    @PostMapping
    public ResponseEntity<AttachmentDto> createAttachment(@RequestBody AttachmentDto attachmentDTO) {
        Attachment createdAttachment = attachmentService.createAttachment(attachmentDTO.getFileName(), attachmentDTO.getFilePath(), attachmentDTO.getFileType(), attachmentDTO.getFileSize());
        AttachmentDto createdAttachmentDto = attachmentMapper.toAttachmentDto(createdAttachment);

        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<AttachmentDto>> getAttachments() {
        List<Attachment> allAttachmentsList = attachmentService.getAllAttachments();

        List<AttachmentDto> attachmentDtoList = allAttachmentsList.stream()
                .map(attachmentMapper::toAttachmentDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(attachmentDtoList);
    }

    @GetMapping("/{attachmentId}")
    public ResponseEntity<AttachmentDto> getAttachment(@PathVariable String attachmentId) {

        Attachment attachmentBYId = attachmentService.getAttachmentById(attachmentId);
        AttachmentDto attachmentDto = attachmentMapper.toAttachmentDto(attachmentBYId);

        return ResponseEntity.status(HttpStatus.OK).body(attachmentDto);
    }

    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable String attachmentId) {

        attachmentService.deleteAttachment(attachmentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(AttachmentNotFoundException.class)
    public ResponseEntity<ErrorDto> handleAttachmentNotFoundException(AttachmentNotFoundException e) {

        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
