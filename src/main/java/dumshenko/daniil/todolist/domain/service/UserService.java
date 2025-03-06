package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.domain.repository.UserRepository;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import java.time.Instant;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(User user) {
    user.setCreatedAt(Instant.now());

    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(UUID userId) {
    Optional<User> user = userRepository.findById(userId);

    return user.orElseThrow(() -> new UserNotFoundException(userId));
  }

  public User updateUser(UUID userId, User userToUpdateForm) {
    User user = getUserById(userId);

    user.update(userToUpdateForm);
    //    userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    return userRepository.save(user);
  }

  public void deleteUser(UUID userId) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    userRepository.delete(user);
  }
}
