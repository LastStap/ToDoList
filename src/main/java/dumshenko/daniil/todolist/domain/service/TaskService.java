package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.Task;
import java.time.Instant;
import java.util.*;


import dumshenko.daniil.todolist.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskMapper taskMapper;

  @Autowired
  public TaskService(TaskMapper taskMapper) {
    this.taskMapper = taskMapper;
  }

  public Task createTask() {
    return null;
  }

  public List<Task> getAllTasks() {
    return null;
  }

  public Task getTaskById(String taskId){
    return null;
  }

  public Task updateTask(Task task){
    return null;
  }

  public void deleteTask(String taskId){

  }
}
