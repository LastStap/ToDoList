package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "categories")
public class CategoryEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "created_at")
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity userEntity;

  @ManyToMany(mappedBy = "categoryEntities", fetch = FetchType.EAGER)
  private List<TaskEntity> taskEntities;

  public CategoryEntity() {}

  public CategoryEntity(
      UUID id,
      String name,
      String description,
      Instant createdAt,
      UserEntity userEntity,
      List<TaskEntity> taskEntities) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.createdAt = createdAt;
    this.userEntity = userEntity;
    this.taskEntities = taskEntities;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CategoryEntity that = (CategoryEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(description, that.description)
        && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(userEntity, that.userEntity)
        && Objects.equals(taskEntities, that.taskEntities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, createdAt, userEntity, taskEntities);
  }

  @Override
  public String toString() {
    return "CategoryEntity{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", createdAt='"
        + createdAt
        + '\''
        + ", userEntity="
        + userEntity
        + ", taskCategoryEntities="
        + taskEntities
        + '}';
  }
}
