package br.com.xxx.ecommerce.desafio1.repositories;

import br.com.xxx.ecommerce.desafio1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
}
