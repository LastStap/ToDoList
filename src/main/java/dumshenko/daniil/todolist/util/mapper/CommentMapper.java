package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.CommentDto;
import dumshenko.daniil.todolist.service.domain.Comment;

public interface CommentMapper {
    CommentDto toCommentDto(Comment comment);
    Comment toDomain(CommentDto commentDto);
}
