package com.dmoTask.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String characterName;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}
