package com.aware.todo.backend.service;

import com.aware.todo.backend.model.Todo;
import java.util.List;

public interface ITodoService {

    List<Todo> getAllTodos();

    Todo getTodoById(String id);

    Todo createTodo(Todo todo);

    Todo updateTodo(String id, Todo todo);

    void deleteTodo(String id);

    void deleteTodoById(String id);
}