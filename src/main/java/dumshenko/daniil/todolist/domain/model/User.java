package dumshenko.daniil.todolist.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class User {

  private UUID id;
  private String password;
  private String username;
  private String email;
  private Instant createdAt;
  private Instant updatedAt;

  public User() {}

  public User(
      UUID id,
      String password,
      String username,
      String email,
      Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.password = password;
    this.username = username;
    this.email = email;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "UserDTO{"
        + "id='"
        + id
        + '\''
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", email='"
        + email
        + '\''
        + ", created_at='"
        + createdAt
        + '\''
        + ", updated_at='"
        + updatedAt
        + '\''
        + '}';
  }
}
