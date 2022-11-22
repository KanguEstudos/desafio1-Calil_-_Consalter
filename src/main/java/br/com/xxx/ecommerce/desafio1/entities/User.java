package br.com.xxx.ecommerce.desafio1.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100) not null")
    private String nome;

    @Column(columnDefinition = "varchar(100) not null")
    private Double cpf;

    @Column(columnDefinition = "varchar(255) not null")
    private String email;

    @Column(columnDefinition = "varchar(255) not null")
    private String password;


}
