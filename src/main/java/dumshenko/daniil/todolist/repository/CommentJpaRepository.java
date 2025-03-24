package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.repository.entity.CommentEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, UUID> {
    Optional<CommentEntity> findByTitle(String title);
}
