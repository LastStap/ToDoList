package dumshenko.daniil.todolist.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttachmentDto {

  private String id;

  @JsonProperty("file_name")
  private String fileName;

  @JsonProperty("file_path")
  private String filePath;

  @JsonProperty("file_type")
  private String fileType;

  @JsonProperty("file_size")
  private int fileSize;

  @JsonProperty("task_id")
  private String taskId;

  @JsonProperty("uploaded_at")
  private String uploadedAt;

  public AttachmentDto() {}

  public AttachmentDto(
      String id,
      String fileName,
      String filePath,
      String fileType,
      int fileSize,
      String taskId,
      String uploadedAt) {
    this.id = id;
    this.fileName = fileName;
    this.filePath = filePath;
    this.fileType = fileType;
    this.fileSize = fileSize;
    this.taskId = taskId;
    this.uploadedAt = uploadedAt;
  }

  @Override
  public String toString() {
    return "AttachmentDTO{"
        + "id='"
        + id
        + '\''
        + ", fileName='"
        + fileName
        + '\''
        + ", filePath='"
        + filePath
        + '\''
        + ", fileType='"
        + fileType
        + '\''
        + ", fileSize='"
        + fileSize
        + '\''
        + ", taskId='"
        + taskId
        + '\''
        + ", uploadedAt='"
        + uploadedAt
        + '\''
        + '}';
  }
}
