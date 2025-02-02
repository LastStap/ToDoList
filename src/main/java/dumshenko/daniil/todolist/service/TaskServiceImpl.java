package dumshenko.daniil.todolist.service;

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
        task.setDueDate(task.getDueDate());
        task.setCreatedAt(now.toString());

        if(task.getUserId() != null) {
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
    public Task getTaskById(String id) {
        return null;
    }

    @Override
    public Task updateTask(String id, Task task) {
        return null;
    }

    @Override
    public void deleteTask(String id) {

    }
}
