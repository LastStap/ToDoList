package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.service.domain.User;

import java.util.List;

public interface UserService {

    User createUser(String username, String password, String email);

    List<User> getAllUsers();

    User getUserById(String userId) throws UserNotFoundException;

    User updateUser(User user, String userId) throws UserNotFoundException;

    void deleteUser(String userId) throws UserNotFoundException;

}
