package com.training.demo.services;
import com.training.demo.models.Todo;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class TodoService {
    private final Map<Long, Todo> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public Collection<Todo> findAll() {
        return store.values();
    }

    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Todo create(String title) {
        Long id = sequence.getAndIncrement();
        Todo todo = new Todo(id, title);
        store.put(id, todo);
        return todo;
    }

    public Optional<Todo> update(Long id, String title, Boolean completed) {
        Todo todo = store.get(id);
        if (todo == null) return Optional.empty();
        if (title != null)     todo.setTitle(title);
        if (completed != null) todo.setCompleted(completed);
        return Optional.of(todo);
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
