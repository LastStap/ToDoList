package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDto {
    private String id;
    private String name;
    private String description;
    @JsonProperty("user_id")
    private String userId;

    public CategoryDto() {}

    public CategoryDto(String id, String name, String description, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
