package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  private TaskStatus status;

  @Column(name = "priority")
  private TaskPriority priority;

  @Column(name = "due_date")
  private Instant dueDate;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity userEntity;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
  private List<TaskCategoryEntity> taskCategoryEntities;
}
