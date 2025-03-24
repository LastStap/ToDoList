package dumshenko.daniil.todolist.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {
  public TaskNotFoundException(UUID message) {
    super(String.valueOf(message));
  }
}
