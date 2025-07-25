package com.dmoTask.dtos;

import com.dmoTask.enums.TaskType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    @NotNull
    private String title;

    private String description;
    @NotNull
    private TaskType taskType;

    private boolean completed = false;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotNull
    private Long playerId;

}
