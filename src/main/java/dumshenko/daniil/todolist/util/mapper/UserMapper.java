package dumshenko.daniil.todolist.util.mapper;

import dumshenko.daniil.todolist.controller.dto.UserDTO;
import dumshenko.daniil.todolist.service.domain.User;

public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toDomain(UserDTO userDTO);
}
