package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateCommentDTO {

    private String content;
    @JsonProperty("task_id")
    private String taskId;
    @JsonProperty("user_id")
    private String userId;

    public CreateCommentDTO() {
    }

    public CreateCommentDTO(String content, String taskId, String userId) {
        this.content = content;
        this.taskId = taskId;
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateCommentDTO that = (CreateCommentDTO) o;
        return Objects.equals(content, that.content) && Objects.equals(taskId, that.taskId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, taskId, userId);
    }
}
