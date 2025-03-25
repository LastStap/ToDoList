package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.domain.model.Comment;
import dumshenko.daniil.todolist.repository.entity.CommentEntity;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public CommentDto toDto(Comment comment) {
    return null;
//    return new CommentDto(
//        comment.getId(),
//        comment.getContent(),
//        comment.getCreatedAt(),
//        comment.getTaskId(),
//        comment.getUserId());
  }

  public Comment toDomain(CommentDto commentDto) {
    return null;
//    return new Comment(
//        null,
//        commentDto.getContent(),
//        commentDto.getCreatedAt(),
//        commentDto.getTaskId(),
//        commentDto.getUserId());
  }

  public Comment toDomain(UUID commentId, CommentDto commentDto) {
    return null;
//    return new Comment(
//        commentDto.getId(),
//        commentDto.getContent(),
//        commentDto.getCreatedAt(),
//        commentDto.getTaskId(),
//        commentDto.getUserId());
  }

  public Comment toDomain(CommentEntity commentEntity) {
    return null;
//    return new Comment(
//        commentEntity.getId(),
//        commentEntity.getContent(),
//        commentEntity.getCreatedAt(),
//        commentEntity.getTask().getId(),
//        commentEntity.getUser().getId());
  }

//  public CommentEntity toEntity(Comment comment) {
//    return new CommentEntity(
//            comment.getId(),
//            comment.getContent(),
//            comment.getCreatedAt(),
//            comment.getTaskId(),
//            comment.getUserId()
//    );
//  }
}
