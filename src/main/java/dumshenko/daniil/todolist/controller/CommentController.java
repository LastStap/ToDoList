package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final Map<String, CommentDTO> commentMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments() {
        List<CommentDTO> comments = new ArrayList<>(commentMap.values());
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        commentDTO.setId(id);
        commentDTO.setContent(commentDTO.getContent());
        commentDTO.setCreatedAt(now.toString());

        if(commentDTO.getTaskId() != null) {
            commentDTO.setTaskId(null);
        }

        if(commentDTO.getUserId() != null) {
            commentDTO.setUserId(null);
        }

        commentMap.put(id, commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDTO);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable String commentId) {
        CommentDTO commentDTO = commentMap.get(commentId);
        if (commentDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        if (commentMap.containsKey(commentId)) {
            commentMap.remove(commentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable String commentId, @RequestBody CommentDTO commentDTO) {
        Instant now = Instant.now();

        CommentDTO currentComment = commentMap.get(commentId);

        if (currentComment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(!commentDTO.getContent().equals(currentComment.getContent())) {
            currentComment.setContent(commentDTO.getContent());
        }

        currentComment.setUpdatedAt(now.toString());
        commentMap.put(commentId, currentComment);
        return ResponseEntity.status(HttpStatus.OK).body(currentComment);
    }

}
