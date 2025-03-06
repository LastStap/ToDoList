package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class UserDto {

  private final UUID id;
  private final String username;
  private final String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private final String password;

  @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
  private final Instant createdAt;

  public UserDto(
      UUID id,
      String username,
      String email,
      String password,
      Instant createdAt) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
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

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserDto userDto = (UserDto) o;
    return Objects.equals(id, userDto.id)
        && Objects.equals(username, userDto.username)
        && Objects.equals(email, userDto.email)
        && Objects.equals(password, userDto.password)
        && Objects.equals(createdAt, userDto.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, createdAt);
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
        + '}';
  }
}
