package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateTaskDTO {

    private String title;
    private String description;
    private String status;
    private String priority;
    @JsonProperty("due_date")
    private String dueDate;
    @JsonProperty("user_id")
    private String userId;

    public CreateTaskDTO() {
    }

    public CreateTaskDTO(String title, String description, String status, String priority, String dueDate, String userId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
        CreateTaskDTO that = (CreateTaskDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(status, that.status) && Objects.equals(priority, that.priority) && Objects.equals(dueDate, that.dueDate) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status, priority, dueDate, userId);
    }
}
