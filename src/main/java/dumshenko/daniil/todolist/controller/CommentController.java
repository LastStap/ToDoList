package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.exception.CommentNotFoundException;
import dumshenko.daniil.todolist.exception.TaskNotFoundException;
import dumshenko.daniil.todolist.service.CommentService;
import dumshenko.daniil.todolist.service.domain.Comment;
import dumshenko.daniil.todolist.util.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDTO) {

        Comment createdComment = commentService.createComment(commentDTO.getContent());
        CommentDto createdCommentDto = commentMapper.toCommentDto(createdComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentDTO);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments() {
        List<Comment> allCommentsList = commentService.getAllComments();

        List<CommentDto> commentDtoList = allCommentsList.stream()
                .map(commentMapper::toCommentDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable String commentId) throws CommentNotFoundException {

        Comment commentById = commentService.getCommentById(commentId);
        CommentDto commentDto = commentMapper.toCommentDto(commentById);

        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable String commentId, @RequestBody CommentDto commentDTO) throws CommentNotFoundException {
        Comment comment = commentMapper.toDomain(commentDTO);
        Comment updatedComment = commentService.updateComment(comment, commentId);
        CommentDto updatedCommentDto = commentMapper.toCommentDto(updatedComment);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCommentDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) throws CommentNotFoundException {

        commentService.deleteComment(commentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCommentNotFoundException(CommentNotFoundException e) {

        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
