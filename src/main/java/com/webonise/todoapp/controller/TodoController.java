package com.webonise.todoapp.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.todoapp.model.Todo;
import com.webonise.todoapp.service.impl.TodoServiceImpl;

@RestController
@RequestMapping("/todos")
public class TodoController {
  @Autowired
  private TodoServiceImpl todoService;

  @GetMapping
  public List<Todo> getTodos() {
    return todoService.getTodos();
  }

  @PostMapping
  public Optional<Todo> addTodo(@RequestBody Todo todo) {
    return todoService.saveTodo(todo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTodo(@PathVariable int id) {
    return todoService.deleteTodo(id);
  }

  @PutMapping
  public ResponseEntity<Object> updateTodo(@RequestBody Todo todo) {
    return todoService.updateTodoById(todo);
  }
}
