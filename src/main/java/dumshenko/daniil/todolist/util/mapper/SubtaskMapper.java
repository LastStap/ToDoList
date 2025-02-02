package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDTO;
import dumshenko.daniil.todolist.service.domain.Subtask;

public interface SubtaskMapper {
    SubtaskDTO toSubtaskDto(Subtask subtask);
    Subtask toDomain(SubtaskDTO subtaskDTO);
}
