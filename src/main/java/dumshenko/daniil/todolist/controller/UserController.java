package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.domain.service.UserService;
import dumshenko.daniil.todolist.domain.model.User;

import java.util.List;
import java.util.UUID;

import dumshenko.daniil.todolist.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDTO) {
    User user = userMapper.toDomain(userDTO);

    User createdUser = userService.createUser(user);

    UserDto createdUserDto = userMapper.toDto(createdUser);

    return ResponseEntity.ok(createdUserDto);
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getUsers() {
    List<User> users = userService.getAllUsers();

    List<UserDto> userDtos = users.stream()
            .map(userMapper::toDto)
            .toList();

    return ResponseEntity.ok(userDtos);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUser(@PathVariable UUID userId){
    User user = userService.getUserById(userId);

    UserDto userDto = userMapper.toDto(user);

    return ResponseEntity.ok(userDto);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId, @RequestBody UserDto userDTO) {
    User user = userMapper.toDomain(userId, userDTO);

    User updatedUser = userService.updateUser(user);

    UserDto updatedUserDto = userMapper.toDto(updatedUser);

    return ResponseEntity.ok(updatedUserDto);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){

    userService.deleteUser(userId);

    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {
    ErrorDto errorDto = new ErrorDto(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }
}
