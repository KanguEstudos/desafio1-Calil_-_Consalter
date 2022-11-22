package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.Product;
import br.com.xxx.ecommerce.desafio1.repositories.ProductsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/v1/products")
public class ProdutctsController {

    private ProductsRepository repository;


    @GetMapping("/list")
    public List<Product> findAll(){
        List<Product> result = repository.findAll();
        return result;
    }

}
