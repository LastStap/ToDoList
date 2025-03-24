package dumshenko.daniil.todolist.exception;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException(UUID message) {
    super(String.valueOf(message));
  }
}
