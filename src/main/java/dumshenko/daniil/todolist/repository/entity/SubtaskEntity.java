package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "subtask")
public class SubtaskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String title;

    @Column
    private String status;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity taskEntity;

}
