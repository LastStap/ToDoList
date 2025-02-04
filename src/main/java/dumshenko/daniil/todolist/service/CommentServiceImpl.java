package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.exception.CommentAlreadyExistsException;
import dumshenko.daniil.todolist.exception.CommentNotFoundException;
import dumshenko.daniil.todolist.service.domain.Comment;
import dumshenko.daniil.todolist.service.domain.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    private final Map<String, Comment> commentMap = new HashMap<>();

    @Override
    public Comment createComment(String content, String commentId) throws CommentAlreadyExistsException {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        if (commentMap.containsKey(id)) {
            throw new CommentAlreadyExistsException("Comment with commentId: " + commentId + " already exist.");
        }

        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent(content);
        comment.setCreatedAt(now.toString());

        if (comment.getTaskId() != null) {
            comment.setTaskId(null);
        }

        if (comment.getUserId() != null) {
            comment.setUserId(null);
        }
        commentMap.put(id, comment);

        return comment;
    }

    @Override
    public List<Comment> getAllComments() {
        return new ArrayList<>(commentMap.values());
    }

    @Override
    public Comment getCommentById(String commentId) throws CommentNotFoundException {
        Comment comment = commentMap.get(commentId);

        if (comment == null) {
            throw new CommentNotFoundException("Comment with commentId: " + commentId + " doesn't exist.");
        }
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment, String commentId) throws CommentNotFoundException {
        Instant now = Instant.now();
        Comment currentComment = commentMap.get(commentId);

        if (currentComment == null) {
            throw new CommentNotFoundException("Comment with commentId: " + commentId + " doesn't exist.");
        }

        if (!comment.getContent().equals(currentComment.getContent())) {
            currentComment.setContent(comment.getContent());
        }
        currentComment.setUpdatedAt(now.toString());
        return commentMap.put(commentId, currentComment);
    }

    @Override
    public void deleteComment(String commentId) throws CommentNotFoundException {
        if (commentMap.containsKey(commentId)) {
            commentMap.remove(commentId);
        } else {
            throw new CommentNotFoundException("Comment with commentId: " + commentId + " doesn't exist.");
        }
    }
}
