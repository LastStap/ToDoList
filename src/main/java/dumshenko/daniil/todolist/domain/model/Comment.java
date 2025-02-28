package dumshenko.daniil.todolist.domain.model;

public class Comment {
  private String id;
  private String content;
  private String taskId;
  private String userId;
  private String createdAt;
  private String updatedAt;

  public Comment() {}

  public Comment(
      String id, String content, String taskId, String userId, String createdAt, String updatedAt) {
    this.id = id;
    this.content = content;
    this.taskId = taskId;
    this.userId = userId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
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
        + ", updatedAt='"
        + updatedAt
        + '\''
        + '}';
  }
}
