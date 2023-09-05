package com.pedroresende.todolist.services;

import com.pedroresende.todolist.models.entities.Task;
import com.pedroresende.todolist.models.repositories.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task create(Task task) {
    return taskRepository.save(task);
  }

  public List<Task> getAll() {
    return taskRepository.findAll();
  }

  public Optional<Task> getById(Long id) {
    return taskRepository.findById(id);
  }

  public Optional<Task> update(Long id, Task task) {
    Optional<Task> optionalTask = taskRepository.findById(id);

    if (optionalTask.isEmpty()) {
      return Optional.empty();
    }
    task.setId(id);
    return Optional.ofNullable(taskRepository.save(task));
  }

  public void delete(Long id) {
    taskRepository.deleteById(id);
  }
}
