package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {
    Optional<TaskEntity> findByTitle(String title);
}
