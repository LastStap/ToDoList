package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.repository.entity.UserEntity;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto toDto(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        user.getPassword(),
        user.getEmail(),
        user.getCreatedAt(),
        user.getUpdatedAt());
  }

  public User toDomain(UserDto userDto) {
    UUID userId;
    if (userDto.getId() != null) {
      userId = userDto.getId();
    } else{
      userId = null;
    }
    return new User(
        userId,
        userDto.getUsername(),
        userDto.getPassword(),
        userDto.getEmail(),
        userDto.getCreatedAt(),
        userDto.getUpdatedAt());
  }

  public User toDomain(UUID userId, UserDto userDto) {
    return new User(
        userDto.getId(),
        userDto.getUsername(),
        userDto.getPassword(),
        userDto.getEmail(),
        userDto.getCreatedAt(),
        userDto.getUpdatedAt());
  }

  public User toDomain(UserEntity userEntity) {
    return new User(
        userEntity.getId(),
        userEntity.getUsername(),
        userEntity.getPassword(),
        userEntity.getEmail(),
        userEntity.getCreatedAt(),
        userEntity.getUpdatedAt());
  }

  public UserEntity toEntity(User user) {
    return new UserEntity(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt());
  }
}
