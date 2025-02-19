package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.SubtaskDto;
import dumshenko.daniil.todolist.repository.entity.SubtaskEntity;
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

    @Override
    public Subtask toDomainFromEntity(SubtaskEntity subtaskEntity) {
        Subtask subtask = new Subtask();

        subtask.setId(subtaskEntity.getId().toString());
        subtask.setTitle(subtaskEntity.getTitle());
        subtask.setStatus(subtaskEntity.getStatus());
        subtask.setCreatedAt(subtaskEntity.getCreatedAt());
        return subtask;
    }
}
