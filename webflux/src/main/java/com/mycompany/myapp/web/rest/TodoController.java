package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Todo;
import com.mycompany.myapp.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
public class TodoController {

    private final Logger log = LoggerFactory.getLogger(TodoController.class);

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /*
    curl  --header "Content-Type: application/json" \
          --request POST \
          --data '{"description":"configuration","details":"congratulations, you have set up R2DBC correctly!","done": "true"}' \
          http://127.0.0.1:8080
    */
    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Todo> createTodo(@RequestBody Todo todo) {
        return Mono.just(todoRepository.save(todo));
    }

    // curl http://127.0.0.1:8080
    @GetMapping("/todo")
    public Flux<Todo> getTodos() {
        return Flux.fromIterable(todoRepository.findAll());
    }
}