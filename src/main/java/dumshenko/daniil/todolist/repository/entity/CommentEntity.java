package dumshenko.daniil.todolist.repository.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

}
