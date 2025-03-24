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
@Table(name = "category")
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

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity userEntity;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  private List<TaskCategoryEntity> taskCategoryEntities;

  public CategoryEntity() {}

  public CategoryEntity(
      UUID id,
      String name,
      String description,
      Instant createdAt,
      UserEntity userEntity,
      List<TaskCategoryEntity> taskCategoryEntities) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.createdAt = createdAt;
    this.userEntity = userEntity;
    this.taskCategoryEntities = taskCategoryEntities;
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
        && Objects.equals(taskCategoryEntities, that.taskCategoryEntities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, createdAt, userEntity, taskCategoryEntities);
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
        + taskCategoryEntities
        + '}';
  }
}
