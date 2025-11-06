package com.dmoTask.controllers;

import com.dmoTask.dtos.PlayerDTO;
import com.dmoTask.dtos.PlayerResponseDTO;
import com.dmoTask.entities.Player;
import com.dmoTask.mappers.PlayerMapper;
import com.dmoTask.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
    @GetMapping
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers(){
        List<Player> players = playerService.getAllPlayer();
        List<PlayerResponseDTO> dtos = players.stream()
                .map(PlayerMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getPlayerByID(@PathVariable Long id){
        return playerService.getPlayerById(id)
                .map(PlayerMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO){
        Player player = PlayerMapper.toEntity(playerDTO);
        Player savedPlayer = playerService.createPlayer(player);
        return ResponseEntity.ok(PlayerMapper.toResponseDTO(savedPlayer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable Long id,@Valid @RequestBody PlayerDTO playerDTO){
        Optional<Player> existingPlayer = playerService.getPlayerById(id);

        if(existingPlayer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Player playerToUpdate = existingPlayer.get();
        playerToUpdate.setUsername(playerDTO.getUsername());
        playerToUpdate.setCharacterName(playerDTO.getCharacterName());
        Player updated = playerService.createPlayer(playerToUpdate);
        return ResponseEntity.ok(PlayerMapper.toResponseDTO(updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        if(playerService.getPlayerById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }


}
