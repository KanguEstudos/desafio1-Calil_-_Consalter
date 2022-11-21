package br.com.xxx.ecommerce.desafio1.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @Column(columnDefinition = "Decimal(10,2) not null")
    private Double price;

    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @Column(columnDefinition = "varchar(255) not null")
    private String image;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
