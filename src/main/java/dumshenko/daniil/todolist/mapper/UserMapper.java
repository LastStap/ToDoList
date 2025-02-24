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
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt());
    }

    public User toDomain(UUID userId , UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt());
    }

    public User toDomain(UserEntity userEntity) {
        User user = new User();

        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setCreatedAt(userEntity.getCreatedAt());
        user.setUpdatedAt(userEntity.getUpdatedAt());
        return user;
    }

}
