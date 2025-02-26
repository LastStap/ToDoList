package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class UserDto {

  private final UUID id;
  private final String password;
  private final String username;
  private final String email;

  @JsonProperty("created_at")
  private final Instant createdAt;

  @JsonProperty("updated_at")
  private final Instant updatedAt;

  public UserDto(UUID id, String password, String username, String email, Instant createdAt, Instant updatedAt) {
      this.id = id;
      this.password = password;
      this.username = username;
      this.email = email;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
  }

  public UUID getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserDto userDto = (UserDto) o;
    return Objects.equals(id, userDto.id)
        && Objects.equals(password, userDto.password)
        && Objects.equals(username, userDto.username)
        && Objects.equals(email, userDto.email)
        && Objects.equals(createdAt, userDto.createdAt)
        && Objects.equals(updatedAt, userDto.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, password, username, email, createdAt, updatedAt);
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
