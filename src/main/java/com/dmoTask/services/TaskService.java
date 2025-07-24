package com.dmoTask.services;

import com.dmoTask.entities.Player;
import com.dmoTask.entities.Task;
import com.dmoTask.enums.TaskType;
import com.dmoTask.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTAsks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTaskByType(TaskType taskType) {
        return taskRepository.findByType(taskType);
    }
    public List<Task> getTaskByPlayerAndType(Player player, TaskType taskType) {
        return taskRepository.findByPlayerAndType(player, taskType);
    }

    public List<Task> getTasksByPlayer(Player player) {
        return taskRepository.findByPlayer(player);
    }
}
