package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "task_category")
public class TaskCategoryEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private TaskEntity task;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;
}
