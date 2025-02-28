package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class UserDto {

  private final UUID id;
  private final String username;
  private final String email;
  private final String password;

  @JsonProperty("created_at")
  private final Instant createdAt;

  @JsonProperty("updated_at")
  private final Instant updatedAt;

  public UserDto(
      UUID id,
      String username,
      String email,
      String password,
      Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
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
        && Objects.equals(username, userDto.username)
        && Objects.equals(email, userDto.email)
        && Objects.equals(password, userDto.password)
        && Objects.equals(createdAt, userDto.createdAt)
        && Objects.equals(updatedAt, userDto.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "UserDto{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", email='"
        + email
        + '\''
        + ", password='"
        + password
        + '\''
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + '}';
  }
}
