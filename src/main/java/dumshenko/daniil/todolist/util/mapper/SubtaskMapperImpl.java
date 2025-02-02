package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDTO;
import dumshenko.daniil.todolist.service.domain.Subtask;
import org.springframework.stereotype.Component;

@Component
public class SubtaskMapperImpl implements SubtaskMapper {
    @Override
    public SubtaskDTO toSubtaskDto(Subtask subtask) {
        SubtaskDTO subtaskDTO = new SubtaskDTO();
        subtaskDTO.setId(subtask.getId());
        subtaskDTO.setTitle(subtask.getTitle());
        subtaskDTO.setStatus(subtask.getStatus());
        subtaskDTO.setCreatedAt(subtask.getCreatedAt());
        return subtaskDTO;
    }

    @Override
    public Subtask toDomain(SubtaskDTO subtaskDTO) {
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
}
