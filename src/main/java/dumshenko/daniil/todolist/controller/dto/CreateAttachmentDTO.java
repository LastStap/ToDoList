package dumshenko.daniil.todolist.controller.dto;

import java.util.Objects;

public class CreateAttachmentDTO {

    private String file;

    public CreateAttachmentDTO() {
    }

    public CreateAttachmentDTO(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateAttachmentDTO that = (CreateAttachmentDTO) o;
        return Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(file);
    }
}
