package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.AttachmentNotFoundException;
import dumshenko.daniil.todolist.service.domain.Attachment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final Map<String, Attachment> attachmentMap = new HashMap<>();

    @Override
    public Attachment createAttachment(String fileName, String filePath, String fileType, int fileSize) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        Attachment attachment = new Attachment();
        attachment.setId(id);
        attachment.setFileName(fileName);
        attachment.setFileSize(fileSize);
        attachment.setFileType(fileType);
        attachment.setFilePath(filePath);
        attachment.setUploadedAt(now.toString());

        if(attachment.getTaskId() != null) {
            attachment.setTaskId(null);
        }
        attachmentMap.put(id, attachment);

        return attachment;
    }

    @Override
    public List<Attachment> getAllAttachments() {
        return new ArrayList<>(attachmentMap.values());
    }

    @Override
    public Attachment getAttachmentById(String attachmentId) throws AttachmentNotFoundException {
        Attachment attachment = attachmentMap.get(attachmentId);

        if(attachment == null) {
            throw new AttachmentNotFoundException("Attachment with attachmentId: " + attachmentId + " doesn't exist.");
        }

        return attachment;
    }

    @Override
    public void deleteAttachment(String attachmentId) throws AttachmentNotFoundException {
        if (attachmentMap.containsKey(attachmentId)) {
            attachmentMap.remove(attachmentId);
        } else {
            throw new AttachmentNotFoundException("Attachment with attachmentId: " + attachmentId + " doesn't exist.");
        }
    }
}
