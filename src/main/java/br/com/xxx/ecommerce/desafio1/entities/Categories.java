package br.com.xxx.ecommerce.desafio1.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) not null")
    private String name;
}
