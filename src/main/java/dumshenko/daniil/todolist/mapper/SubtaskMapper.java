package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.repository.entity.SubtaskEntity;
import dumshenko.daniil.todolist.domain.model.Subtask;
import org.springframework.stereotype.Component;

@Component
public class SubtaskMapper {

  public SubtaskDto toSubtaskDto(Subtask subtask) {
    SubtaskDto subtaskDTO = new SubtaskDto();
    subtaskDTO.setId(subtask.getId());
    subtaskDTO.setTitle(subtask.getTitle());
    subtaskDTO.setStatus(subtask.getStatus());
    subtaskDTO.setCreatedAt(subtask.getCreatedAt());
    return subtaskDTO;
  }


  public Subtask toDomainFromDto(SubtaskDto subtaskDTO) {
    if (subtaskDTO == null) {
      return null;
    }
    Subtask subtask = new Subtask();
    subtask.setId(subtaskDTO.getId());
    subtask.setTitle(subtaskDTO.getTitle());
    subtask.setStatus(subtaskDTO.getStatus());
    subtask.setCreatedAt(subtaskDTO.getCreatedAt());
    return subtask;
  }


  public Subtask toDomainFromEntity(SubtaskEntity subtaskEntity) {
    Subtask subtask = new Subtask();

    subtask.setId(subtaskEntity.getId().toString());
    subtask.setTitle(subtaskEntity.getTitle());
    subtask.setStatus(subtaskEntity.getStatus());
    subtask.setCreatedAt(subtaskEntity.getCreatedAt());
    return subtask;
  }
}
