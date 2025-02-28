package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.domain.repository.UserRepository;
import dumshenko.daniil.todolist.mapper.UserMapper;
import dumshenko.daniil.todolist.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper, UserJpaRepository userJpaRepository) {
        this.userMapper = userMapper;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);

        UserEntity savedUserEntity = userJpaRepository.save(userEntity);

        return userMapper.toDomain(savedUserEntity);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(userMapper::toDomain).toList();
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return userJpaRepository.findById(userId).map(userMapper::toDomain);
    }
    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return userJpaRepository.findByEmail(email.toLowerCase()).map(userMapper::toDomain);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.deleteById(user.getId());
    }
}
