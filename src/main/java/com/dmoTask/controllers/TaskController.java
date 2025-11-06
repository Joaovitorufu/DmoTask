package com.dmoTask.controllers;

import com.dmoTask.dtos.TaskDTO;
import com.dmoTask.entities.Player;
import com.dmoTask.entities.Task;
import com.dmoTask.enums.TaskType;
import com.dmoTask.mappers.TaskMapper;
import com.dmoTask.services.PlayerService;
import com.dmoTask.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final PlayerService playerService;

    public TaskController(TaskService taskService, PlayerService playerService){
        this.taskService = taskService;
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTAsks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("{taskType}")
    public ResponseEntity<List<Task>> getTaskByType(@PathVariable TaskType taskType){
        return ResponseEntity.ok(taskService.getTaskByType(taskType));
    }

    public ResponseEntity<?> getTaskByPLayer(
            @RequestParam(required = false) Long playerId,
            @RequestParam(required = false) String characterName,
            @RequestParam(required = false) TaskType taskType) {

        Optional<Player> playerOptional = Optional.empty();

        if (playerId != null) {
            playerOptional = playerService.getPlayerById(playerId);
        } else if (characterName != null) {
            Player player = playerService.getPlayerByCharacterName(characterName);
            if (player != null) {
                playerOptional = Optional.of(player);
            }
        }
        if (playerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Player não encontrado");
        }
        Player player = playerOptional.get();

        if (taskType != null) {
            return ResponseEntity.ok(taskService.getTaskByPlayerAndType(player, taskType));
        } else {
            return ResponseEntity.ok(taskService.getTasksByPlayer(player));
        }
    }
    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Optional<Player> playerOpt = playerService.getPlayerById(taskDTO.getPlayerId());

        if (playerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Player não encontrado");
        }

        Task task = TaskMapper.toEntity(taskDTO, playerOpt.get());
        Task saved = taskService.saveTask(task);
        return ResponseEntity.ok(TaskMapper.toResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        Optional<Task> existingTask = taskService.getTaskById(id);

        if (existingTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Player> playerOpt = playerService.getPlayerById(taskDTO.getPlayerId());
        if (playerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Player não encontrado");
        }

        Task taskToUpdate = existingTask.get();
        taskToUpdate.setTitle(taskDTO.getTitle());
        taskToUpdate.setDescription(taskDTO.getDescription());
        taskToUpdate.setType(taskDTO.getTaskType());
        taskToUpdate.setCompleted(taskDTO.isCompleted());
        taskToUpdate.setStartDate(taskDTO.getStartDate());
        taskToUpdate.setEndDate(taskDTO.getEndDate());
        taskToUpdate.setPlayer(playerOpt.get());

        Task updated = taskService.saveTask(taskToUpdate);
        return ResponseEntity.ok(TaskMapper.toResponseDTO(updated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        //verifica se a tarefa existe antes de tentar deletar
        Optional<Task> existingTask = taskService.getTaskById(id);
        if(existingTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}

