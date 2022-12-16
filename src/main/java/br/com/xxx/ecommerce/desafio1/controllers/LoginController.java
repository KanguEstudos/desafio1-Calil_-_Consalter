package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.entities.User;
import br.com.xxx.ecommerce.desafio1.repositories.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

@RestController
@RequestMapping(value="/v1/auth")
public class LoginController {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
		"7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
		.getBytes(StandardCharsets.UTF_8));

    @PostMapping()
    public ResponseEntity<User> Auth( @RequestBody User user)
    {
        try{
            if(
				user.getEmail().equals("teste@treinaweb.com.br") 
					&& 
				user.getPassword().equals("1234")
			)
            {
                String jwtToken = Jwts.builder()
                    .setSubject(user.getEmail())
                    .setIssuer("localhost:8080")
                    .setIssuedAt(new Date())
                    .setExpiration(
						Date.from(
							LocalDateTime.now().plusMinutes(15L)
								.atZone(ZoneId.systemDefault())
							.toInstant()))
                    .signWith(CHAVE, SignatureAlgorithm.RS512)
                    .compact();
                
                return ResponseEntity.ok().body(null);
            }
            else
                return ResponseEntity.notFound().build();
        }
        catch(Exception ex)
        {
            return  ResponseEntity.status(null).build(); 
        } 
    }
}
