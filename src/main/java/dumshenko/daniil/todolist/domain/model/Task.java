package dumshenko.daniil.todolist.domain.model;

import java.time.Instant;
import java.util.UUID;

import dumshenko.daniil.todolist.domain.TaskPriority;
import dumshenko.daniil.todolist.domain.TaskStatus;
import lombok.Data;

@Data
public class Task {

  private UUID id;
  private String title;
  private String description;
  private TaskStatus status;
  private TaskPriority priority;
  private Instant dueDate;
  private Instant createdAt;
  private Instant updatedAt;
  private UUID userId;

  public Task() {}

  public Task(UUID id, String title, String description, TaskStatus status, TaskPriority priority, Instant dueDate, Instant createdAt, Instant updatedAt, UUID userId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.dueDate = dueDate;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Task{"
        + "id='"
        + id
        + '\''
        + ", title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", status='"
        + status
        + '\''
        + ", priority='"
        + priority
        + '\''
        + ", dueDate='"
        + dueDate
        + '\''
        + ", createdAt='"
        + createdAt
        + '\''
        + ", updatedAt='"
        + updatedAt
        + '\''
        + ", userId='"
        + userId
        + '\''
        + '}';
  }
}
