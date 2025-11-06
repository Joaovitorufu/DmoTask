package com.dmoTask.services;

import com.dmoTask.entities.Player;
import com.dmoTask.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public Player getPlayerByCharacterName(String characterName) {
        return playerRepository.findByCharacterName(characterName);
    }

    public List<Player> getAllPlayer(){return playerRepository.findAll();}

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id){
        playerRepository.deleteById(id);
    }
}
