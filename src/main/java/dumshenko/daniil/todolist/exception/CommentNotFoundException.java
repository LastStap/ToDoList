package dumshenko.daniil.todolist.exception;

import java.util.UUID;

public class CommentNotFoundException extends RuntimeException {
  public CommentNotFoundException(UUID message) {
    super(String.valueOf(message));
  }
}
