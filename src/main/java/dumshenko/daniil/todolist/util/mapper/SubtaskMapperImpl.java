package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.service.domain.Subtask;
import org.springframework.stereotype.Component;

@Component
public class SubtaskMapperImpl implements SubtaskMapper {
    @Override
    public SubtaskDto toSubtaskDto(Subtask subtask) {
        SubtaskDto subtaskDTO = new SubtaskDto();
        subtaskDTO.setId(subtask.getId());
        subtaskDTO.setTitle(subtask.getTitle());
        subtaskDTO.setStatus(subtask.getStatus());
        subtaskDTO.setCreatedAt(subtask.getCreatedAt());
        return subtaskDTO;
    }

    @Override
    public Subtask toDomain(SubtaskDto subtaskDTO) {
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
