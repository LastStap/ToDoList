package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
public class UserDto {

    private UUID id;
    private String password;
    private String username;
    private String email;
    @JsonProperty("created_at")
    private Instant createdAt;
    @JsonProperty("updated_at")
    private Instant updatedAt;

    public UserDto() {
    }

    public UserDto(String username, String email, Instant createdAt, Instant updatedAt, UUID id, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserDTO{" + "id='" + id + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", created_at='" + createdAt + '\'' + ", updated_at='" + updatedAt + '\'' + '}';
    }
}
