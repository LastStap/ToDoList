package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import dumshenko.daniil.todolist.service.domain.Subtask;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    private final Map<String, Subtask> subtasksMap = new HashMap<>();

    @Override
    public Subtask createSubtask(String title, String status) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        Subtask subtask = new Subtask();
        subtask.setId(id);
        subtask.setTitle(title);
        subtask.setStatus(status);
        subtask.setCreatedAt(now.toString());

        subtasksMap.put(id, subtask);

        return subtask;
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasksMap.values());
    }

    @Override
    public Subtask getSubtaskById(String subtaskId) throws SubtaskNotFoundException {
        Subtask subtask = subtasksMap.get(subtaskId);

        if (subtask == null) {
            throw new SubtaskNotFoundException("Subtask with id: " + subtaskId + " doesn't exist.");
        }
        return subtask;
    }

    @Override
    public Subtask updateSubtask(String subtaskId, Subtask subtask) throws SubtaskNotFoundException {
        Instant now = Instant.now();

        Subtask currentSubtask = subtasksMap.get(subtaskId);

        if (currentSubtask == null) {
            throw new SubtaskNotFoundException("Subtask with subtaskId: " + subtaskId + " doesn't exist.");
        }
        if (!subtask.getTitle().equals(currentSubtask.getTitle())) {
            currentSubtask.setTitle(subtask.getTitle());
        }
        if (!subtask.getStatus().equals(currentSubtask.getStatus())) {
            currentSubtask.setStatus(subtask.getStatus());
        }
        currentSubtask.setUpdatedAt(now.toString());

        return subtasksMap.put(subtaskId, currentSubtask);
    }

    @Override
    public void deleteSubtask(String subtaskId) throws SubtaskNotFoundException {
        if (subtasksMap.containsKey(subtaskId)) {
            subtasksMap.remove(subtaskId);
        } else {
            throw new SubtaskNotFoundException("Subtask with id: " + subtaskId + " doesn't exist.");
        }
    }
}
