package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.AuthTokenDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.LoginDto;
import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.domain.service.LoginService;
import dumshenko.daniil.todolist.domain.service.UserService;
import dumshenko.daniil.todolist.exception.PasswordIncorrectException;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.mapper.UserMapper;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);
  private final UserMapper userMapper;
  private final UserService userService;
  private final LoginService loginService;

  @Autowired
  public AuthController(UserMapper userMapper, UserService userService, LoginService loginService) {
    this.userMapper = userMapper;
    this.userService = userService;
    this.loginService = loginService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<Void> signUp(@RequestBody UserDto userDto) {
    User user = userMapper.toDomain(userDto);
    User createdUser;

    try {
      createdUser = userService.createUser(user);
    } catch (Exception e) {
      log.error(
          "Error creating user with email {} and username {}",
          userDto.getEmail(),
          userDto.getUsername());
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    UserDto createdUserDto = userMapper.toDto(createdUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthTokenDto> login(@RequestBody LoginDto loginDto) {

    String token = loginService.login(loginDto.getEmail(), loginDto.getPassword());

    return ResponseEntity.ok(new AuthTokenDto(token));
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {
    ErrorDto errorDto = new ErrorDto("Wrong credentials");

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }

  @ExceptionHandler(PasswordIncorrectException.class)
  public ResponseEntity<ErrorDto> handlePasswordIncorrectException(PasswordIncorrectException e) {
    ErrorDto errorDto = new ErrorDto("Wrong credentials");

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }
}
