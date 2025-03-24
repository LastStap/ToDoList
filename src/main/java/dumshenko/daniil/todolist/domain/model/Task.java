package dumshenko.daniil.todolist.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Task {

  private UUID id;
  private String title;
  private String description;
  private TaskStatus status;
  private TaskPriority priority;
  private Instant dueDate;
  private Instant createdAt;
  private UUID userId;

  public Task() {}

  public Task(UUID id, String title, String description, TaskStatus status, TaskPriority priority, Instant dueDate, Instant createdAt, UUID userId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.dueDate = dueDate;
    this.createdAt = createdAt;
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
        + ", userId='"
        + userId
        + '\''
        + '}';
  }

  public void update(Task task) {
    this.title = task.getTitle();
    this.description = task.getDescription();
    this.status = task.getStatus();
    this.priority = task.getPriority();
    this.dueDate = task.getDueDate();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public TaskPriority getPriority() {
    return priority;
  }

  public void setPriority(TaskPriority priority) {
    this.priority = priority;
  }

  public Instant getDueDate() {
    return dueDate;
  }

  public void setDueDate(Instant dueDate) {
    this.dueDate = dueDate;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }
}
