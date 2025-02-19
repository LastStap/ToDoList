package dumshenko.daniil.todolist.service.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

     private String id;
     private String password;
     private String username;
     private String email;
     private String createdAt;
     private String updatedAt;

    public User() {
    }

    public User(String id, String password, String username, String email, String createdAt, String updatedAt) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created_at='" + createdAt + '\'' +
                ", updated_at='" + updatedAt + '\'' +
                '}';
    }
}
