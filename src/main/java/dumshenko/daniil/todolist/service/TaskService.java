package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.TaskNotFoundException;
import dumshenko.daniil.todolist.service.domain.Task;

import java.util.List;

public interface TaskService {

    Task createTask(String title, String description, String status, String priority, String dueDate);

    List<Task> getAllTasks();

    Task getTaskById(String taskId) throws TaskNotFoundException;

    Task updateTask(String taskId, Task task) throws TaskNotFoundException;

    void deleteTask(String taskId) throws TaskNotFoundException;

}
