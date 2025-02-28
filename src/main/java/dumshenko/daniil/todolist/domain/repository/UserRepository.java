package dumshenko.daniil.todolist.domain.repository;

import dumshenko.daniil.todolist.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID userId);
    Optional<User> findByEmailIgnoreCase(String email);
    void delete(User user);
}
