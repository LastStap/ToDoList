package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.domain.repository.UserRepository;
import dumshenko.daniil.todolist.exception.PasswordIncorrectException;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final TokenService tokenService;

  @Autowired
  public LoginServiceImpl(
      PasswordEncoder passwordEncoder, UserRepository userRepository, TokenService tokenService) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.tokenService = tokenService;
  }

  @Override
  public String login(String email, String password) {
    User user =
        userRepository
            .findByEmailIgnoreCase(email)
            .orElseThrow(() -> new UserNotFoundException(email));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new PasswordIncorrectException(email);
    }

    return tokenService.createToken(user);
  }
}
