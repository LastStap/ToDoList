package dumshenko.daniil.todolist.domain.model;

public class Category {
  private String id;
  private String name;
  private String description;
  private String userId;

  public Category() {}

  public Category(String id, String name, String description, String userId) {
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
