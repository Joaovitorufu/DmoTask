package com.dmoTask.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerDTO {
    @NotNull
    private String username;
    @NotNull
    private String characterName;
}
