package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.AttachmentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    private final Map<String, AttachmentDTO> attachmentMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<AttachmentDTO>> getAttachments() {
        List<AttachmentDTO> attachments = new ArrayList<>(attachmentMap.values());
        return ResponseEntity.status(HttpStatus.OK).body(attachments);
    }

    @PostMapping
    public ResponseEntity<AttachmentDTO> createAttachment(@RequestBody AttachmentDTO attachment) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setId(id);
        attachmentDTO.setFileName(attachment.getFileName());
        attachmentDTO.setFileSize(attachment.getFileSize());
        attachmentDTO.setFileType(attachment.getFileType());
        attachmentDTO.setFilePath(attachment.getFilePath());
        attachmentDTO.setUploadedAt(now.toString());

        attachmentMap.put(id, attachmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentDTO);
    }

    @GetMapping("/{attachmentId}")
    public ResponseEntity<AttachmentDTO> getAttachment(@PathVariable String attachmentId) {
        AttachmentDTO attachmentDTO = attachmentMap.get(attachmentId);
        if (attachmentDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(attachmentDTO);
    }

    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<AttachmentDTO> deleteAttachment(@PathVariable String attachmentId) {
        if (attachmentMap.containsKey(attachmentId)) {
            attachmentMap.remove(attachmentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
