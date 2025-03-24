package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subtask")
public class SubtaskEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column private String title;

  @Column private String status;

  @Column(name = "created_at")
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "task_id", nullable = false)
  private TaskEntity taskEntity;

  public SubtaskEntity(
      UUID id, String title, String status, Instant createdAt, TaskEntity taskEntity) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.createdAt = createdAt;
    this.taskEntity = taskEntity;
  }

  public SubtaskEntity() {}

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SubtaskEntity that = (SubtaskEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(title, that.title)
        && Objects.equals(status, that.status)
        && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(taskEntity, that.taskEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, status, createdAt, taskEntity);
  }

  @Override
  public String toString() {
    return "SubtaskEntity{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", status='"
        + status
        + '\''
        + ", createdAt='"
        + createdAt
        + '\''
        + ", taskEntity="
        + taskEntity
        + '}';
  }
}
