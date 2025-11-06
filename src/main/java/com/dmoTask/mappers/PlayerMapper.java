package com.dmoTask.mappers;

import com.dmoTask.dtos.PlayerDTO;
import com.dmoTask.dtos.PlayerResponseDTO;
import com.dmoTask.entities.Player;

public class PlayerMapper {

    public static PlayerResponseDTO toResponseDTO(Player player) {
        PlayerResponseDTO playerResponseDTO = new PlayerResponseDTO();
        playerResponseDTO.setId(player.getId());
        playerResponseDTO.setUsername(player.getUsername());
        playerResponseDTO.setCharacterName(player.getCharacterName());
        return playerResponseDTO;
    }
    public static Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setUsername(playerDTO.getUsername());
        player.setCharacterName(playerDTO.getCharacterName());
        return player;
    }
}
