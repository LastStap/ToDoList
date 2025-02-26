package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.repository.entity.CommentEntity;
import dumshenko.daniil.todolist.domain.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public CommentDto toCommentDto(Comment comment) {
    return null;
  }

  public Comment toDomainFromDto(CommentDto commentDto) {
    return null;
  }

  public Comment toDomainFromEntity(CommentEntity commentEntity) {
    return null;
  }
}
