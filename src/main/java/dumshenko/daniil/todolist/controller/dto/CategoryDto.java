package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CategoryDto {
  private UUID id;
  private String name;
  private String description;

  @JsonProperty("user_id")
  private UUID userId;

  public CategoryDto() {}

  public CategoryDto(UUID id, String name, String description, UUID userId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.userId = userId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "CategoryDTO{"
        + "id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", userId='"
        + userId
        + '\''
        + '}';
  }
}
