package dumshenko.daniil.todolist.repository.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "attachments")
public class AttachmentEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_path")
  private String filePath;

  @Column(name = "file_type")
  private String fileType;

  @Column(name = "file_size")
  private int fileSize;

  @Column(name = "uploaded_at")
  private String uploadedAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "task_id")
  private TaskEntity taskEntity;
}
