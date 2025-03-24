package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class CommentDto {
  private UUID id;
  private String content;

  @JsonProperty("created_at")
  private Instant createdAt;

  @JsonProperty("task_id")
  private UUID taskId;

  @JsonProperty("user_id")
  private UUID userId;

  public CommentDto() {}

  public CommentDto(
      UUID id, String content, Instant createdAt, UUID taskId, UUID userId) {
    this.id = id;
    this.content = content;
    this.taskId = taskId;
    this.userId = userId;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public UUID getTaskId() {
    return taskId;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "CommentDTO{"
        + "id='"
        + id
        + '\''
        + ", content='"
        + content
        + '\''
        + ", taskId='"
        + taskId
        + '\''
        + ", userId='"
        + userId
        + '\''
        + ", createdAt='"
        + createdAt
        + '\''
        + '}';
  }
}
