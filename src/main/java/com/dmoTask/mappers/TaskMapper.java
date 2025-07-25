package com.dmoTask.mappers;

import com.dmoTask.dtos.TaskDTO;
import com.dmoTask.dtos.TaskResponseDTO;
import com.dmoTask.entities.Player;
import com.dmoTask.entities.Task;

import java.time.LocalDateTime;

public class TaskMapper {
    public static Task toEntity(TaskDTO taskDTO, Player player){
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setType(taskDTO.getTaskType());
        task.setCompleted(taskDTO.isCompleted());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setCreatedAt(LocalDateTime.now());
        task.setPlayer(player);

        return task;
    }
    public static TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO taskResponseDto = new TaskResponseDTO();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setType(task.getType());
        taskResponseDto.setCompleted(task.isCompleted());
        taskResponseDto.setStartDate(task.getStartDate());
        taskResponseDto.setEndDate(task.getEndDate());
        taskResponseDto.setCreatedAt(task.getCreatedAt());
        taskResponseDto.setPlayerId(task.getPlayer().getId());
        return taskResponseDto;
    }
}
