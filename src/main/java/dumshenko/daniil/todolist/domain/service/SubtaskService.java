package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import dumshenko.daniil.todolist.domain.model.Subtask;
import java.time.Instant;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class SubtaskService {

  public Subtask createSubtask(String title, String status) {
    return null;
  }


  public List<Subtask> getAllSubtasks() {
    return null;
  }

  public Subtask getSubtaskById(String subtaskId){
    return null;
  }


  public Subtask updateSubtask(String subtaskId, Subtask subtask) {
    return null;
  }


  public void deleteSubtask(String subtaskId) {

  }
}
