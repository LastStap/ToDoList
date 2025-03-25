package dumshenko.daniil.todolist.domain.repository;

import dumshenko.daniil.todolist.domain.model.Comment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findAll();
    Optional<Comment> findById(UUID commentId);
    void delete(Comment comment);
}
