package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.exception.CommentNotFoundException;
import dumshenko.daniil.todolist.domain.model.Comment;

import java.util.*;

import dumshenko.daniil.todolist.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentMapper commentMapper;

  @Autowired
  public CommentController(CommentMapper commentMapper) {

    this.commentMapper = commentMapper;
  }

  @PostMapping
  public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDTO) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<CommentDto>> getComments() {
    return null;
  }

  @GetMapping("/{commentId}")
  public ResponseEntity<CommentDto> getComment(@PathVariable String commentId){
return null;
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommentDto> updateComment(@PathVariable String commentId, @RequestBody CommentDto commentDTO){
return null;
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> deleteComment(@PathVariable String commentId){
return null;
  }

  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<ErrorDto> handleCommentNotFoundException(CommentNotFoundException e) {

    return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
  }
}
