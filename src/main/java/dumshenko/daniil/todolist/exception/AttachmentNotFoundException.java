package dumshenko.daniil.todolist.exception;

public class AttachmentNotFoundException extends RuntimeException {
  public AttachmentNotFoundException(String message) {
    super(message);
  }
}
