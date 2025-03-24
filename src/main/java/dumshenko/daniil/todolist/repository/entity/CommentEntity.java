package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "comment")
public class CommentEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "content")
  private String content;

  @Column(name = "created_at")
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "task_id", nullable = false)
  private TaskEntity task;

  public CommentEntity(
      UUID id, String content, Instant createdAt, UserEntity user, TaskEntity task) {
    this.id = id;
    this.content = content;
    this.createdAt = createdAt;
    this.user = user;
    this.task = task;
  }

  public CommentEntity() {}

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CommentEntity that = (CommentEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(content, that.content)
        && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(user, that.user)
        && Objects.equals(task, that.task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content, createdAt, user, task);
  }

  @Override
  public String toString() {
    return "CommentEntity{"
        + "id="
        + id
        + ", content='"
        + content
        + '\''
        + ", createdAt='"
        + createdAt
        + '\''
        + ", user="
        + user
        + ", task="
        + task
        + '}';
  }
}
