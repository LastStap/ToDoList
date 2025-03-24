package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.Comment;
import dumshenko.daniil.todolist.domain.repository.CommentRepository;
import dumshenko.daniil.todolist.exception.CommentNotFoundException;
import java.time.Instant;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private final CommentRepository commentRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public Comment createComment(Comment comment) {
    comment.setCreatedAt(Instant.now());

    return commentRepository.save(comment);
  }

  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }

  public Comment getCommentById(UUID commentId) {
    Optional<Comment> comment = commentRepository.findById(commentId);

    return comment.orElseThrow(() -> new CommentNotFoundException(commentId));
  }

  public Comment updateComment(Comment commentToUpdateForm, UUID commentId) {
    Comment comment = getCommentById(commentId);

    comment.update(commentToUpdateForm);

    return commentRepository.save(comment);
  }

  public void deleteComment(UUID commentId) {
    Comment comment =
        commentRepository
            .findById(commentId)
            .orElseThrow(() -> new CommentNotFoundException(commentId));

    commentRepository.delete(comment);
  }
}
