package dumshenko.daniil.todolist.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

public class User {

  private UUID id;
  private String password;
  private String username;
  private String email;
  private Instant createdAt;
  private Instant updatedAt;

  public User() {}

  public User(UUID id, String password, String username, String email, Instant createdAt, Instant updatedAt) {
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

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
