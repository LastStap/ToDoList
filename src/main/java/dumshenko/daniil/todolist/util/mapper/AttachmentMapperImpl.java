package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.AttachmentDto;
import dumshenko.daniil.todolist.service.domain.Attachment;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapperImpl implements AttachmentMapper {
    @Override
    public AttachmentDto toAttachmentDto(Attachment attachment) {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setId(attachment.getId());
        attachmentDto.setFileName(attachment.getFileName());
        attachmentDto.setFilePath(attachment.getFilePath());
        attachmentDto.setFileType(attachment.getFileType());
        attachmentDto.setFileSize(attachment.getFileSize());
        return attachmentDto;
    }

    @Override
    public Attachment toDomain(AttachmentDto attachmentDto) {
        if (attachmentDto == null) {
            return null;
        }
        Attachment attachment = new Attachment();
        attachment.setId(attachmentDto.getId());
        attachment.setFileName(attachmentDto.getFilePath());
        attachment.setFilePath(attachmentDto.getFilePath());
        attachment.setFileType(attachmentDto.getFileType());
        attachment.setFileSize(attachmentDto.getFileSize());
        return attachment;
    }
}
