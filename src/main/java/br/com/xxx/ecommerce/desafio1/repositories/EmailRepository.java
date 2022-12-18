package br.com.xxx.ecommerce.desafio1.repositories;

import br.com.xxx.ecommerce.desafio1.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {


}
