package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.domain.model.Subtask;
import dumshenko.daniil.todolist.repository.entity.SubtaskEntity;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SubtaskMapper {

  public SubtaskDto toDto(Subtask subtask) {
    return new SubtaskDto(
        subtask.getId(),
        subtask.getTitle(),
        subtask.getStatus(),
        subtask.getCreatedAt(),
        subtask.getTaskId());
  }

  public Subtask toDomain(SubtaskDto subtaskDto) {
    return new Subtask(
        null,
        subtaskDto.getTitle(),
        subtaskDto.getStatus(),
        subtaskDto.getCreatedAt(),
        subtaskDto.getTaskId());
  }

  public Subtask toDomain(UUID subtaskId, SubtaskDto subtaskDto) {
    return new Subtask(
        subtaskDto.getId(),
        subtaskDto.getTitle(),
        subtaskDto.getStatus(),
        subtaskDto.getCreatedAt(),
        subtaskDto.getTaskId());
  }

  public Subtask toDomain(SubtaskEntity subtaskEntity) {
    return new Subtask(
        subtaskEntity.getId(),
        subtaskEntity.getTitle(),
        subtaskEntity.getStatus(),
        subtaskEntity.getCreatedAt(),
        subtaskEntity.getTaskEntity().getId());
  }

  public SubtaskEntity toEntity(Subtask subtask) {
    return new SubtaskEntity(
        subtask.getId(),
        subtask.getTitle(),
        subtask.getStatus(),
        subtask.getCreatedAt(),
        subtask.getTaskId());
  }
}
