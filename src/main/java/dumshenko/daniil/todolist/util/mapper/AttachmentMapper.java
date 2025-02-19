package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.AttachmentDto;
import dumshenko.daniil.todolist.repository.entity.AttachmentEntity;
import dumshenko.daniil.todolist.service.domain.Attachment;

public interface AttachmentMapper {
    AttachmentDto toAttachmentDto(Attachment attachment);
    Attachment toDomainFromDto(AttachmentDto attachmentDto);
    Attachment toDomainFromEntity(AttachmentEntity attachmentEntity);
}
