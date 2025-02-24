package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.repository.entity.CommentEntity;
import dumshenko.daniil.todolist.domain.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public CommentDto toCommentDto(Comment comment) {
    CommentDto commentDto = new CommentDto();

    commentDto.setId(comment.getId());
    commentDto.setContent(comment.getContent());
    commentDto.setCreatedAt(comment.getCreatedAt());
    return commentDto;
  }

  public Comment toDomainFromDto(CommentDto commentDto) {
    Comment comment = new Comment();

    comment.setId(commentDto.getId());
    comment.setContent(commentDto.getContent());
    comment.setCreatedAt(commentDto.getCreatedAt());
    return comment;
  }

  public Comment toDomainFromEntity(CommentEntity commentEntity) {
    Comment comment = new Comment();

    comment.setId(commentEntity.getId().toString());
    comment.setContent(commentEntity.getContent());
    comment.setCreatedAt(commentEntity.getCreatedAt());
    return comment;
  }
}
