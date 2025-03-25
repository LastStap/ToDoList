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
  private User user;

  public Task() {}

  public Task(UUID id, String title, String description, TaskStatus status, TaskPriority priority, Instant dueDate, Instant createdAt, User user) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.dueDate = dueDate;
    this.createdAt = createdAt;
    this.user = user;
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
        + user.getId()
        + '\''
        + '}';
  }

  public void update(Task taskToUpdateFrom) {
    this.title = taskToUpdateFrom.getTitle();
    this.description = taskToUpdateFrom.getDescription();
    this.status = taskToUpdateFrom.getStatus();
    this.priority = taskToUpdateFrom.getPriority();
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
