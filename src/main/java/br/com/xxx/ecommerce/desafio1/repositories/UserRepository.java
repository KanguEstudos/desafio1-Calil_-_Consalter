package br.com.xxx.ecommerce.desafio1.repositories;

import br.com.xxx.ecommerce.desafio1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
