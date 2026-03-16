package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    // GET ALL TODOS
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    // CREATE TODO
    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    // UPDATE TODO
    public Todo updateTodo(Long id, Todo todo) {

        Todo existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        existing.setTitle(todo.getTitle());
        existing.setDescription(todo.getDescription());
        existing.setCompleted(todo.isCompleted());

        return repository.save(existing);
    }

    // DELETE TODO
    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}