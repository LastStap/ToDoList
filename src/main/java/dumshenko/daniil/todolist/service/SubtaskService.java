package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import dumshenko.daniil.todolist.service.domain.Subtask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubtaskService {
    Subtask createSubtask(String title, String status);

    List<Subtask> getAllSubtasks();

    Subtask getSubtaskById(String id) throws SubtaskNotFoundException;

    Subtask updateSubtask(String id, Subtask subtask) throws SubtaskNotFoundException;

    void deleteSubtask(String id) throws SubtaskNotFoundException;
}
