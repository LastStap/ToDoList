package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.controller.dto.UserDTO;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.service.domain.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, User> usersMap = new HashMap<>();

    @Override
    public User createUser(String username, String password, String email) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreatedAt(now.toString());

        usersMap.put(id, user);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public User getUserById(String userId) {
        User user = usersMap.get(userId);

        if (user == null) {
            throw new UserNotFoundException("Person with id: " + userId + " doesn't exist.");
        }
        return user;
    }

    @Override
    public User updateUser(User user, String userId) throws UserNotFoundException {
        Instant now = Instant.now();
        User currentUser = usersMap.get(userId);

        if (user == null) {
            throw new UserNotFoundException("Person with id: " + userId + " doesn't exist.");
        }
        if (!user.getUsername().equals(currentUser.getUsername())) {
            currentUser.setUsername(user.getUsername());
        }
        if (!user.getEmail().equals(currentUser.getEmail())) {
            currentUser.setEmail(user.getEmail());
        }
        if (!user.getPassword().equals(currentUser.getPassword())) {
            currentUser.setPassword(user.getPassword());
        }
        currentUser.setUpdatedAt(now.toString());

        return usersMap.put(userId, currentUser);
    }

    @Override
    public void deleteUser(String userId) {
        if(usersMap.containsKey(userId)) {
            usersMap.remove(userId);
        } else {
            throw new UserNotFoundException("Person with id: " + userId + " doesn't exist.");
        }
    }
}
