package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.exception.AttachmentNotFoundException;
import dumshenko.daniil.todolist.domain.model.Attachment;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService {


  public Attachment createAttachment(String fileName, String filePath, String fileType, int fileSize) {
    return null;
  }


  public List<Attachment> getAllAttachments() {
    return null;
  }


  public Attachment getAttachmentById(String attachmentId) throws AttachmentNotFoundException {
    return null;
  }


  public void deleteAttachment(String attachmentId) throws AttachmentNotFoundException {
  }
}
