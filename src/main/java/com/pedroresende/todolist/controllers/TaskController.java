package com.pedroresende.todolist.controllers;

import com.pedroresende.todolist.models.entities.Task;
import com.pedroresende.todolist.services.TaskService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada de controller da rota "tasks".
 */
@RestController
@RequestMapping("tasks")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping
  public ResponseEntity<Task> create(@RequestBody Task task) {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(task));
  }

  @GetMapping
  public ResponseEntity<List<Task>> getAll() {
    return ResponseEntity.ok(taskService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getById(@PathVariable Long id) {
    Optional<Task> optionalTask = taskService.getById(id);
    return optionalTask.map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
    Optional<Task> optionalTask = taskService.update(id, task);
    return optionalTask.map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    taskService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
