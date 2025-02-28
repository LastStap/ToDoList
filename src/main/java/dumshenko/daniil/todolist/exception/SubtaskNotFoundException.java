package dumshenko.daniil.todolist.exception;

public class SubtaskNotFoundException extends RuntimeException {
  public SubtaskNotFoundException(String message) {
    super(message);
  }
}
