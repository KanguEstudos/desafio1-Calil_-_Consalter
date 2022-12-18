package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.Product;
import br.com.xxx.ecommerce.desafio1.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/products")
public class ProdutctsController {

    @Autowired
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

    @GetMapping(value = "/list/{title}")
    public List<Product> findByName(@PathVariable String title) {
        return repository.findByTitle(title);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return repository.findById(id).map(
                map -> {
                    map.setTitle(product.getTitle());
                    Product saved = repository.save(map);
                    return ResponseEntity.ok().body(saved);
                }
        ).orElse(ResponseEntity.notFound().build());
    }


}
