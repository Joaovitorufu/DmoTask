package com.dmoTask.entities;

import com.dmoTask.enums.TaskType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskType type; //DIARIA, SEMANAL, SAZONAL

    private boolean completed;

    private LocalDate startDate;  //para sazonais ou controle de recorrência
    private LocalDate endDate;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

}
