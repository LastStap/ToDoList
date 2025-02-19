package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.service.UserService;
import dumshenko.daniil.todolist.service.domain.User;
import dumshenko.daniil.todolist.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

        User createdUser = userService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        UserDto createdUserDto = userMapper.toUserDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> allUsersList = userService.getAllUsers();

        List<UserDto> userDtoList = allUsersList.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) throws UserNotFoundException {

        User userById = userService.getUserById(userId);
        UserDto userDTO = userMapper.toUserDTO(userById);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDTO) {
        User user = userMapper.toDomainFromDto(userDTO);
        User updatedUser = userService.updateUser(user, userId);
        UserDto updatedUserDto = userMapper.toUserDTO(updatedUser);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) throws UserNotFoundException {

        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {

        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
