package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;

@Data
public class TaskDto {

  private final UUID id;
  private final String title;
  private final String description;
  private final TaskStatusDto status;
  private final TaskPriorityDto priority;

  @JsonProperty("due_date")
  private Instant dueDate;

  @JsonProperty("created_at")
  private Instant createdAt;

  @JsonProperty("updated_at")
  private Instant updatedAt;

  @JsonProperty("user_id")
  private UUID userId;

  public TaskDto(UUID id, String title, String description, TaskStatusDto status, TaskPriorityDto priority) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.status = status;
      this.priority = priority;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    TaskDto taskDto = (TaskDto) o;
    return Objects.equals(id, taskDto.id)
        && Objects.equals(title, taskDto.title)
        && Objects.equals(description, taskDto.description)
        && Objects.equals(status, taskDto.status)
        && Objects.equals(priority, taskDto.priority)
        && Objects.equals(dueDate, taskDto.dueDate)
        && Objects.equals(createdAt, taskDto.createdAt)
        && Objects.equals(updatedAt, taskDto.updatedAt)
        && Objects.equals(userId, taskDto.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, title, description, status, priority, dueDate, createdAt, updatedAt, userId);
  }

  @Override
  public String toString() {
    return "TaskDTO{"
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
        + '}';
  }
}
