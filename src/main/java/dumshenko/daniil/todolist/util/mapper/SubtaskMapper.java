package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.service.domain.Subtask;

public interface SubtaskMapper {
    SubtaskDto toSubtaskDto(Subtask subtask);
    Subtask toDomain(SubtaskDto subtaskDTO);
}
