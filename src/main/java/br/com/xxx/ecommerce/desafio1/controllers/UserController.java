package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.User;
import br.com.xxx.ecommerce.desafio1.repositories.UserRepository;
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
}
