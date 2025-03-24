package dumshenko.daniil.todolist.exception;

import java.util.UUID;

public class SubtaskNotFoundException extends RuntimeException {
  public SubtaskNotFoundException(UUID message) {
    super(String.valueOf(message));
  }
}
