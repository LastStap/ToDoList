package dumshenko.daniil.todolist.domain.model;

import java.time.Instant;
import java.util.UUID;

public class User {

  private UUID id;
  private String username;
  private String email;
  private String password;
  private Instant createdAt;

  public User() {}

  public User(UUID id, String username, String email, String password, Instant createdAt) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", createdAt=" + createdAt +
            '}';
  }

  public void update(User user) {
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.password = user.getPassword();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }
}
