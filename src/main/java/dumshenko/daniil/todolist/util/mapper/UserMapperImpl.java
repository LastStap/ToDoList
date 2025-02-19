package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.repository.entity.UserEntity;
import dumshenko.daniil.todolist.service.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    @Override
    public User toDomainFromDto(UserDto userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    }

    @Override
    public User toDomainFromEntity(UserEntity userEntity) {
        User user = new User();

        user.setId(userEntity.getId().toString());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setCreatedAt(userEntity.getCreatedAt());
        return user;
    }
}
