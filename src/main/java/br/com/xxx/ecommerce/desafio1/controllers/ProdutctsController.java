package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.Product;
import br.com.xxx.ecommerce.desafio1.repositories.ProductsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/products")
public class ProdutctsController {

    private ProductsRepository repository;
    @PostMapping("/save")
    public Product insert(@RequestBody Product category) {
        return repository.save(category);
    }

    @GetMapping("/list")
    public List<Product> findAll(){
        List<Product> result = repository.findAll();
        return result;
    }

    @GetMapping(value = "/list/{name}")
    public List<Product> findByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return repository.findById(id).map(
                map -> {
                    map.setTitle(product.getTitle());
                    Product saved = repository.save(product);
                    return ResponseEntity.ok().body(saved);
                }
        ).orElse(ResponseEntity.notFound().build());
    }


}
