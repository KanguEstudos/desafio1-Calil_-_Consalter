package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.User;
import br.com.xxx.ecommerce.desafio1.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/user")
public class UserController {
    private UserRepository repository;

    @GetMapping
    public List<User> findAll(){
        List<User> result = repository.findAll();
        return result;
    }

    @PostMapping("/save")
    public User insert(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping(value = "/list/{name}")
    public List<User> findByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return repository.findById(id).map(
                map -> {
                    map.setNome(user.getNome());
                    User saved = repository.save(user);
                    return ResponseEntity.ok().body(saved);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
