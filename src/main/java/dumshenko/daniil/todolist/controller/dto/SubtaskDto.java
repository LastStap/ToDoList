package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public class SubtaskDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID id;
  private String title;
  private String status;

  @JsonProperty("created_at")
  private Instant createdAt;

  @JsonProperty("task_id")
  private UUID taskId;

  public SubtaskDto() {}

  public SubtaskDto(
      UUID id, String title, String status, Instant createdAt, UUID taskId) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.createdAt = createdAt;
    this.taskId = taskId;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public UUID getTaskId() {
    return taskId;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  @Override
  public String toString() {
    return "SubtaskDTO{"
        + "id='"
        + id
        + '\''
        + ", title='"
        + title
        + '\''
        + ", status='"
        + status
        + '\''
        + ", createdAt='"
        + createdAt
        + '\''
        + ", taskId='"
        + taskId
        + '\''
        + '}';
  }
}
