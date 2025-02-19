package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.service.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(String username, String password, String email);

    List<User> getAllUsers();

    User getUserById(UUID userId) throws UserNotFoundException;

    User updateUser(User user, UUID userId) throws UserNotFoundException;

    void deleteUser(UUID userId) throws UserNotFoundException;

}
