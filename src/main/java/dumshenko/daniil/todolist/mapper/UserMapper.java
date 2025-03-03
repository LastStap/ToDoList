package dumshenko.daniil.todolist.mapper;

import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.repository.entity.UserEntity;

import java.util.Collections;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto toDto(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        user.getCreatedAt());
  }

  public User toDomain(UserDto userDto) {
    return new User(
        null,
        userDto.getUsername(),
        userDto.getEmail(),
        userDto.getPassword(),
        userDto.getCreatedAt());
  }

  public User toDomain(UUID userId, UserDto userDto) {
    return new User(
        userDto.getId(),
        userDto.getUsername(),
        userDto.getEmail(),
        userDto.getPassword(),
        userDto.getCreatedAt());
  }

  public User toDomain(UserEntity userEntity) {
    return new User(
        userEntity.getId(),
        userEntity.getUsername(),
        userEntity.getEmail(),
        userEntity.getPassword(),
        userEntity.getCreatedAt());
  }

  public UserEntity toEntity(User user) {
    return new UserEntity(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        user.getCreatedAt());
  }
}
