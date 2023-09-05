package com.pedroresende.todolist.models.repositories;

import com.pedroresende.todolist.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
