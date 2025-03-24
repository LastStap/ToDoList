package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.domain.service.CommentService;
import dumshenko.daniil.todolist.exception.CommentNotFoundException;
import dumshenko.daniil.todolist.domain.model.Comment;

import java.util.*;
import java.util.stream.Collectors;

import dumshenko.daniil.todolist.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;
  private final CommentMapper commentMapper;

  @Autowired
  public CommentController(CommentMapper commentMapper, CommentService commentService) {
    this.commentService = commentService;
    this.commentMapper = commentMapper;
  }

  @PostMapping
  public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDTO) {
    Comment comment = commentMapper.toDomain(commentDTO);
    Comment createdComment = commentService.createComment(comment);

    CommentDto createdCommentDto = commentMapper.toDto(createdComment);
    return ResponseEntity.ok(createdCommentDto);
  }

  @GetMapping
  public ResponseEntity<List<CommentDto>> getComments() {
    List<Comment> comments = commentService.getAllComments();

    List<CommentDto> commentDtos = comments.stream().map(commentMapper::toDto).toList();

    return ResponseEntity.ok(commentDtos);
  }

  @GetMapping("/{commentId}")
  public ResponseEntity<CommentDto> getComment(@PathVariable UUID commentId){
    Comment comment = commentService.getCommentById(commentId);

    CommentDto commentDto = commentMapper.toDto(comment);

    return ResponseEntity.ok(commentDto);
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommentDto> updateComment(@PathVariable UUID commentId, @RequestBody CommentDto commentDTO){
    Comment comment = commentMapper.toDomain(commentDTO);

    Comment updatedComment = commentService.updateComment(comment, commentId);

    CommentDto updatedCommentDto = commentMapper.toDto(updatedComment);

    return ResponseEntity.ok(updatedCommentDto);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId){

    commentService.deleteComment(commentId);

    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<ErrorDto> handleCommentNotFoundException(CommentNotFoundException e) {
    ErrorDto errorDto = new ErrorDto(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }
}
