package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.Category;
import br.com.xxx.ecommerce.desafio1.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/v1/category")
public class CategoryController {

    private CategoryRepository repository;


    @GetMapping("/list")
    public List<Category> findAll(){
        List<Category> result = repository.findAll();
        return result;
    }
    @PostMapping("/save")
    public List<Category> save(){
        List<Category> result = repository.save();
        return result;
    }
}
