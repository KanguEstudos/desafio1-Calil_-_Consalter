package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.User;
import br.com.xxx.ecommerce.desafio1.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
