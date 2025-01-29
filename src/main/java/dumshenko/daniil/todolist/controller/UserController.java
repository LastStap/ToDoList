package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CreateUserDTO;
import dumshenko.daniil.todolist.controller.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private Map<String ,UserDTO> usersMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();

        usersMap.values().forEach(userDTO -> userDTOList.add(userDTO));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTOList);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(createUserDTO.getUsername());
        userDTO.setEmail(createUserDTO.getEmail());
        userDTO.setPassword(createUserDTO.getPassword());
        userDTO.setCreated_at(now.toString());

        usersMap.put(id, userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
}
