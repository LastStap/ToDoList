package dumshenko.daniil.todolist.controller.dto;

import java.util.Objects;

public class CreateSubtaskDTO {

    private String title;
    private String status;

    public CreateSubtaskDTO() {}

    public CreateSubtaskDTO(String title, String status) {
        this.title = title;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateSubtaskDTO that = (CreateSubtaskDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, status);
    }
}
