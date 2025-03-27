package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks")
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
  @Enumerated(EnumType.STRING)
  private TaskStatusEntity status;

  @Column(name = "priority")
  @Enumerated(EnumType.STRING)
  private TaskPriorityEntity priority;

  @Column(name = "due_date")
  private Instant dueDate;

  @Column(name = "created_at")
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity userEntity;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "tasks_categories",
          joinColumns = @JoinColumn(name = "task_id"),
          inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private List<CategoryEntity> categoryEntities;

  public TaskEntity(UUID id, String title, String description, TaskStatusEntity status, TaskPriorityEntity priority,
                    Instant dueDate, Instant createdAt, UserEntity userEntity) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.dueDate = dueDate;
    this.createdAt = createdAt;
    this.userEntity = userEntity;
  }

  public TaskEntity() {

  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatusEntity getStatus() {
    return status;
  }

  public void setStatus(TaskStatusEntity status) {
    this.status = status;
  }

  public TaskPriorityEntity getPriority() {
    return priority;
  }

  public void setPriority(TaskPriorityEntity priority) {
    this.priority = priority;
  }

  public Instant getDueDate() {
    return dueDate;
  }

  public void setDueDate(Instant dueDate) {
    this.dueDate = dueDate;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  public List<CategoryEntity> getCategoryEntities() {
    return categoryEntities;
  }

  public void setCategoryEntities(List<CategoryEntity> categoryEntities) {
    this.categoryEntities = categoryEntities;
  }
}
