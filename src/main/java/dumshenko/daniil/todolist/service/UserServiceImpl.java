package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.UserNotFoundException;
import dumshenko.daniil.todolist.repository.UserRepository;
import dumshenko.daniil.todolist.repository.entity.UserEntity;
import dumshenko.daniil.todolist.service.domain.User;
import dumshenko.daniil.todolist.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Map<String, User> usersMap;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.usersMap = new HashMap<>();
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(String username, String password, String email) {
        Instant now = Instant.now();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        userEntity.setCreatedAt(now);
        userEntity.setUpdatedAt(now);

        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toDomainFromEntity(savedUser);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDomainFromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public User getUserById(UUID userId) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

        if (optionalUserEntity.isPresent()) {
            return userMapper.toDomainFromEntity(optionalUserEntity.get());
        }
        throw new UserNotFoundException("User with id: " + userId + " doesn't exist.");
    }

    @Override
    public User updateUser(User user, UUID userId) throws UserNotFoundException {
        Instant now = Instant.now();
        User currentUser = usersMap.get(userId);

        if (user == null) {
            throw new UserNotFoundException("User with id: " + userId + " doesn't exist.");
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
        currentUser.setUpdatedAt(now);

        return usersMap.put(String.valueOf(userId), currentUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        if(usersMap.containsKey(userId)) {
            usersMap.remove(userId);
        } else {
            throw new UserNotFoundException("User with id: " + userId + " doesn't exist.");
        }
    }
}
