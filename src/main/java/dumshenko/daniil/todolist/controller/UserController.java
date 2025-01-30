package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<String, UserDTO> usersMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> userDTOList = new ArrayList<>(usersMap.values());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTOList);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        userDTO.setId(id);
        userDTO.setUsername(userDTO.getUsername());
        userDTO.setPassword(userDTO.getPassword());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setCreatedAt(now.toString());

        usersMap.put(id, userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) {
        UserDTO userDTO = usersMap.get(userId);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String userId) {
        if(usersMap.containsKey(userId)){
            usersMap.remove(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        Instant now = Instant.now();

        UserDTO currentUser = usersMap.get(userId);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(!userDTO.getUsername().equals(currentUser.getUsername())){
            currentUser.setUsername(userDTO.getUsername());
        }
        if(!userDTO.getEmail().equals(currentUser.getEmail())){
            currentUser.setEmail(userDTO.getEmail());
        }
        if(!userDTO.getPassword().equals(currentUser.getPassword())){
            currentUser.setPassword(userDTO.getPassword());
        }
        currentUser.setUpdatedAt(now.toString());
        usersMap.put(userId, currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(currentUser);
    }
}
