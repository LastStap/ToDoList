package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.UserDTO;
import dumshenko.daniil.todolist.exception.UserAlreadyExistsException;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        User createdUser = userService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        UserDTO createdUserDTO = userMapper.toUserDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> allUsersList = userService.getAllUsers();

        List<UserDTO> userDTOList = allUsersList.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) throws UserNotFoundException {

        User userById = userService.getUserById(userId);
        UserDTO userDTO = userMapper.toUserDTO(userById);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        User user = userMapper.toDomain(userDTO);
        User updatedUser = userService.updateUser(user, userId);
        UserDTO updatedUserDTO = userMapper.toUserDTO(updatedUser);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
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
