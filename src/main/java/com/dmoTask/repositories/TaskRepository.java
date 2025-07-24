package com.dmoTask.repositories;

import com.dmoTask.entities.Player;
import com.dmoTask.entities.Task;
import com.dmoTask.enums.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByPlayer(Player player);

    List<Task> findByType(TaskType taskType);

    List<Task> findByPlayerAndType(Player player, TaskType type);

}
