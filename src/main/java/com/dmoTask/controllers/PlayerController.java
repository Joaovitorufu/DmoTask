package com.dmoTask.controllers;

import com.dmoTask.dtos.PlayerDTO;
import com.dmoTask.entities.Player;
import com.dmoTask.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        return ResponseEntity.ok(playerService.getAllPlayer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerByID(@PathVariable Long id){
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Player> createPlayer(@Valid @RequestBody PlayerDTO playerDTO){
        Player player = new Player();
        player.setUsername(playerDTO.getUsername());
        player.setCharacterName(playerDTO.getCharacterName());
        return ResponseEntity.ok(playerService.createPlayer(player));
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
        return ResponseEntity.ok(updated);
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
