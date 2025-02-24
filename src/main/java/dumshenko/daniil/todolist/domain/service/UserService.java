package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.mapper.UserMapper;
import dumshenko.daniil.todolist.domain.model.User;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserMapper userMapper;

  @Autowired
  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public User createUser(User user) {
    return null;
  }

  public List<User> getAllUsers() {
    return null;
  }

  public User getUserById(UUID userId) {
    return null;
  }

  public User updateUser(User user){
    return null;
  }

  public void deleteUser(UUID userId) {

  }
}
