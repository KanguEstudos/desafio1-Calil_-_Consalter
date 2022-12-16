package br.com.xxx.ecommerce.desafio1.repositories;

import br.com.xxx.ecommerce.desafio1.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContainingIgnoreCase(String name);
}
