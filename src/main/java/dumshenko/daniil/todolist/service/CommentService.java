package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.CommentAlreadyExistsException;
import dumshenko.daniil.todolist.exception.CommentNotFoundException;
import dumshenko.daniil.todolist.service.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(String content, String commentId) throws CommentAlreadyExistsException;

    List<Comment> getAllComments();

    Comment getCommentById(String commentId) throws CommentNotFoundException;

    Comment updateComment(Comment comment, String commentId) throws CommentNotFoundException;

    void deleteComment(String commentId) throws CommentNotFoundException;
}
