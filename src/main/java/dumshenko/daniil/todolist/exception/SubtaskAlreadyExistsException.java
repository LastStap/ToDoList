package dumshenko.daniil.todolist.exception;

public class SubtaskAlreadyExistsException extends RuntimeException {
    public SubtaskAlreadyExistsException(String message) {
        super(message);
    }
}
