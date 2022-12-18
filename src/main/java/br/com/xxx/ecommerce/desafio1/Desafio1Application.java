package br.com.xxx.ecommerce.desafio1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class Desafio1Application {

    public static void main(String[] args) {
        SpringApplication.run(Desafio1Application.class, args);
    }

}
