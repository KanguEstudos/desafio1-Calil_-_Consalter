package br.com.xxx.ecommerce.desafio1.repositories;

import br.com.xxx.ecommerce.desafio1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByNameContainingIgnoreCase(String name);
}
