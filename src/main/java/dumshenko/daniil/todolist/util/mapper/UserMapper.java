package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.service.domain.User;

public interface UserMapper {
    UserDto toUserDTO(User user);
    User toDomain(UserDto userDTO);
}
