package com.aware.todo.backend.controller;

import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.beans.factory.annotation.Autowired;
import com.aware.todo.backend.service.ITodoService;
import com.aware.todo.backend.model.Todo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final ITodoService todoService;

    @Autowired
    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        //don't allow clients to specify their own IDs
        todo.setId(UUID.randomUUID().toString());

        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodoById(@PathVariable String id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable String id) {
        todoService.deleteTodoById(id);
    }
}
