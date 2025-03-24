package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.repository.entity.SubtaskEntity;
import dumshenko.daniil.todolist.repository.entity.TaskEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskJpaRepository extends JpaRepository<SubtaskEntity, UUID> {
    Optional<SubtaskEntity> findByTitle(String title);
}
