package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.AttachmentDto;
import dumshenko.daniil.todolist.repository.entity.AttachmentEntity;
import dumshenko.daniil.todolist.domain.model.Attachment;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {

  public AttachmentDto toAttachmentDto(Attachment attachment) {
    return null;
  }

  public Attachment toDomainFromDto(AttachmentDto attachmentDto) {
    return null;
  }


  public Attachment toDomainFromEntity(AttachmentEntity attachmentEntity) {
    return null;
  }
}
