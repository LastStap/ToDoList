package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.AttachmentNotFoundException;
import dumshenko.daniil.todolist.service.domain.Attachment;

import java.util.List;

public interface AttachmentService {

    Attachment createAttachment(String fileName, String filePath, String fileType, int fileSize);

    List<Attachment> getAllAttachments();

    Attachment getAttachmentById(String attachmentId) throws AttachmentNotFoundException;

    void deleteAttachment(String attachmentId) throws AttachmentNotFoundException;
}
