package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.Category;
import br.com.xxx.ecommerce.desafio1.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.*;

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
    public Category insert(@RequestBody Category category) {
        return repository.save(category);
    }

    @GetMapping(value = "/list/{name}")
    public List<Category> findByName(@PathVariable String name) {
        return repository.findByName(name);
    }
}
