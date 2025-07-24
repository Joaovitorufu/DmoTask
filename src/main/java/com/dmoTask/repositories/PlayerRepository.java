package com.dmoTask.repositories;

import com.dmoTask.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    //Exemplo de Buscas por characterName
    Player findByCharacterName(String characterName);
}
