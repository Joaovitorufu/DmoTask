package com.dmoTask.dtos;

import com.dmoTask.enums.TaskType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskType type;
    private boolean completed;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private Long playerId;
}
