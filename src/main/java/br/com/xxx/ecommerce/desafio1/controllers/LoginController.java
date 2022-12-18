package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

@RestController
@RequestMapping(value = "/v1/auth")
public class LoginController {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
                    .getBytes(StandardCharsets.UTF_8));

    // aqui ainda ta faltando ajustar a Logica
    @PostMapping(value = "/user")
    public String Auth(@RequestBody User user) {
        try {

            if (user.getEmail().equals("felipe.c@email.com")
                    &&
                    user.getPassword().equals("S3nh4")) {
                String jwtToken = Jwts.builder()
                        .setSubject(user.getEmail())
                        .setIssuer("localhost:8080")
                        .setIssuedAt(new Date())
                        .setExpiration(
                                Date.from(
                                        LocalDateTime.now().plusMinutes(15L)
                                                .atZone(ZoneId.systemDefault())
                                                .toInstant()))
                        .signWith(SignatureAlgorithm.HS256, CHAVE)
                        .compact();
                return jwtToken;
            } else {
                return "Não foi possivel retornar os dados";
            }

        } catch (Exception ex) {
            return ex.toString();
        }
    }
}
