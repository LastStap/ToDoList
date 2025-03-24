package dumshenko.daniil.todolist.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Comment {
  private UUID id;
  private String content;
  private Instant createdAt;
  private UUID taskId;
  private UUID userId;

  public Comment() {}

  public Comment(UUID id, String content, Instant createdAt, UUID taskId, UUID userId) {
    this.id = id;
    this.content = content;
    this.createdAt = createdAt;
    this.taskId = taskId;
    this.userId = userId;
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

  public void update(Comment comment) {
    this.content = comment.getContent();
  }
}
