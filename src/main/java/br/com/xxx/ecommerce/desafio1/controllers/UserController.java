package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.emailvalidation.EmailValidationDTO;
import br.com.xxx.ecommerce.desafio1.entities.User;
import br.com.xxx.ecommerce.desafio1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
                    .getBytes(StandardCharsets.UTF_8));

    @Autowired
    private UserRepository repository;

    @GetMapping("/list")
    public List<User> findAll() {
        List<User> result = repository.findAll();
        return result;
    }

    @PostMapping("/save")
    public ResponseEntity<User> insert(@RequestBody User user) {
        repository.findByCpf(user.getCpf())
                .ifPresent((u) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existente para esse CPF");
                });

        String url = "https://emailvalidation.abstractapi.com/v1/?api_key=";
        String apiKey = "04161c12636041748527be218a21e399";
        String urlFull = url + apiKey + "&email=" + user.getEmail();

        RestTemplate restTemplate = new RestTemplate();
        EmailValidationDTO emailValidationDTO = restTemplate.getForObject(urlFull, EmailValidationDTO.class);
        if (emailValidationDTO.getIs_valid_format().isValue() &&
                emailValidationDTO.getIs_smtp_valid().isValue()) {
            return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail inválido");
    }

    @GetMapping(value = "/list/{name}")
    public List<User> findByName(@PathVariable String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return repository.findById(id).map(
                data -> {
                    data.setName(user.getName());
                    data.setCpf(user.getCpf());
                    data.setEmail(user.getEmail());
                    data.setPassword(user.getPassword());
                    User saved = repository.save(data);
                    return ResponseEntity.ok().body(saved);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> Auth(@RequestBody User user) {
        try{
            if (user.getEmail().equals(repository.findByEmail(user.getEmail()))
                    &&
                user.getPassword().equals(repository.findByPassword(user.getPassword()))) {

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
                return new ResponseEntity<>(jwtToken, HttpStatus.CREATED);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro no If");
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel acessar");
        }
    }
}
