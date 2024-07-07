package com.aware.todo.backend.service;

import com.aware.todo.backend.model.Todo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Random;

@Service
public class MemoryTodoService implements ITodoService {

    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public MemoryTodoService() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            //generate random item
            Todo randomTodo = new Todo(
                UUID.randomUUID().toString(), 
                "Random Todo: " + i,
                "This is a randomly generated todo.",
                rand.nextInt(2) == 0
            );
            
            todos.add(randomTodo);
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos);
    }

    @Override
    public Todo getTodoById(String id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Todo createTodo(Todo todo) {
        todo.setId(String.valueOf(counter.incrementAndGet()));
        todos.add(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(String id, Todo updatedTodo) {
        Optional<Todo> todoOptional = todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();

        if (todoOptional.isPresent()) {
            Todo existingTodo = todoOptional.get();
            int index = todos.indexOf(existingTodo);
            updatedTodo.setId(id);
            todos.set(index, updatedTodo);
            return updatedTodo;
        }
        return null;
    }

    @Override
    public void deleteTodo(String id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }

    @Override
    public void deleteTodoById(String id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}