package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.TaskNotFoundException;
import dumshenko.daniil.todolist.service.domain.Task;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final Map<String, Task> taskMap = new HashMap<>();

    @Override
    public Task createTask(String title, String description, String status, String priority, String dueDate) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        task.setCreatedAt(now.toString());

        if (task.getUserId() != null) {
            task.setUserId(null);
        }
        taskMap.put(id, task);

        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public Task getTaskById(String taskId) throws TaskNotFoundException {
        Task task = taskMap.get(taskId);

        if (task == null) {
            throw new TaskNotFoundException("Task with taskId: " + taskId + " doesn't exist.");
        }
        return task;
    }

    @Override
    public Task updateTask(String taskId, Task task) throws TaskNotFoundException {
        Instant now = Instant.now();
        Task currentTask = taskMap.get(taskId);

        if (currentTask == null) {
            throw new TaskNotFoundException("Task with taskId: " + taskId + " doesn't exist.");
        }
        if (!task.getTitle().equals(currentTask.getTitle())) {
            currentTask.setTitle(task.getTitle());
        }
        if (!task.getDescription().equals(currentTask.getDescription())) {
            currentTask.setDescription(task.getDescription());
        }
        if (!task.getStatus().equals(currentTask.getStatus())) {
            currentTask.setStatus(task.getStatus());
        }
        if (!task.getPriority().equals(currentTask.getPriority())) {
            currentTask.setPriority(task.getPriority());
        }
        if (!task.getDueDate().equals(currentTask.getDueDate())) {
            currentTask.setDueDate(task.getDueDate());
        }
        currentTask.setUpdatedAt(now.toString());
        return taskMap.put(taskId, currentTask);
    }

    @Override
    public void deleteTask(String taskId) throws TaskNotFoundException {
        if (taskMap.containsKey(taskId)) {
            taskMap.remove(taskId);
        } else {
            throw new TaskNotFoundException("Task with taskId: " + taskId + " doesn't exist.");
        }
    }
}
