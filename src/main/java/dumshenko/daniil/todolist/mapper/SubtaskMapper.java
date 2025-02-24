package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.repository.entity.SubtaskEntity;
import dumshenko.daniil.todolist.domain.model.Subtask;
import org.springframework.stereotype.Component;

@Component
public class SubtaskMapper {

  public SubtaskDto toSubtaskDto(Subtask subtask) {
    return null;
  }


  public Subtask toDomainFromDto(SubtaskDto subtaskDTO) {
    return null;
  }


  public Subtask toDomainFromEntity(SubtaskEntity subtaskEntity) {
    return null;
  }
}
