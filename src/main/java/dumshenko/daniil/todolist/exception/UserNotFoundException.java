package dumshenko.daniil.todolist.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(UUID userId) {
    super("User with id: " + userId + " doesn't exist.");
  }
}
