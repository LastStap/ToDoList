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
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreatedAt(now.toString());

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
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable String commentId) {
        if (commentMap.containsKey(commentId)) {
            commentMap.remove(commentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable String commentId, @RequestBody CommentDTO comment) {
        Instant now = Instant.now();

        CommentDTO commentDTO = commentMap.get(commentId);

        if (commentDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(!comment.getContent().equals(commentDTO.getContent())) {
            commentDTO.setContent(comment.getContent());
        }

        commentDTO.setUpdatedAt(now.toString());
        commentMap.put(commentId, commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }

}
