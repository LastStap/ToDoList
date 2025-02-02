package dumshenko.daniil.todolist.service;

import dumshenko.daniil.todolist.controller.dto.SubtaskDTO;
import dumshenko.daniil.todolist.exception.SubtaskNotFoundException;
import dumshenko.daniil.todolist.service.domain.Subtask;

import java.time.Instant;
import java.util.*;

public class SubtaskServiceImpl implements SubtaskService {

    private final Map<String, SubtaskDTO> subtasksMap = new HashMap<>();

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
        return List.of();
    }

    @Override
    public Subtask getSubtaskById(String id) throws SubtaskNotFoundException {
        return null;
    }

    @Override
    public Subtask updateSubtask(String id, Subtask subtask) throws SubtaskNotFoundException {
        return null;
    }

    @Override
    public void deleteSubtask(String id) throws SubtaskNotFoundException {

    }
}
