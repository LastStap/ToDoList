package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

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
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<TaskCategoryEntity> taskCategoryEntities;
}
