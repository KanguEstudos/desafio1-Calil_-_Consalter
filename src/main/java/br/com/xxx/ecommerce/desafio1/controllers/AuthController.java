package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.dtos.EmailDTO;
import br.com.xxx.ecommerce.desafio1.enums.StatusEmail;
import br.com.xxx.ecommerce.desafio1.models.EmailModel;
import br.com.xxx.ecommerce.desafio1.returns.GenericReturn;
import br.com.xxx.ecommerce.desafio1.entities.User;
import br.com.xxx.ecommerce.desafio1.repositories.UserRepository;
import br.com.xxx.ecommerce.desafio1.returns.Token;
import br.com.xxx.ecommerce.desafio1.services.EmailService;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping(value = "/v1/auth")
public class AuthController {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
                    .getBytes(StandardCharsets.UTF_8));

    private final AmqpTemplate queuePublisher;

    @Autowired
    EmailService emailService;
    @Autowired
    private UserRepository repository;

    public AuthController(AmqpTemplate queuePublisher) {
        this.queuePublisher = queuePublisher;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<GenericReturn> show(@RequestBody User user) {
        GenericReturn dataReturn = new GenericReturn();
        var data = repository.findByEmail(user.getEmail());

        String token = "23674t2g3j4hb23y4tr623yt4";

        boolean status = false;
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "E-mail não encontrado";

        if (data.isPresent()) {
            EmailDTO emailDTO = new EmailDTO();

            String url = String.format("http://localhost/reset-password/%s", token);
            String text = String.format(
                "Clique <a href='%s'>Aqui</a> para alterar sua senha",
                url
            );

            emailDTO.setOwnerRef("Geovanne K.");
            emailDTO.setEmailFrom("geovanne.k4ngu@gmail.com");
            emailDTO.setSubject("Alteração de senha");
            emailDTO.setText(text);
            emailDTO.setEmailTo(user.getEmail());

            EmailModel emailModel = new EmailModel();
            BeanUtils.copyProperties(emailDTO, emailModel);

            var send = emailService.sendEmail(emailModel);

            System.out.println(send.getText());

            Gson gson = new Gson();
            String json = gson.toJson(emailDTO);

            queuePublisher.convertAndSend("emails", "routing-key-emails", json);

            status = true;
            message = "Verificação concluída! Um e-mail será enviado para alteração de senha";
            httpStatus = HttpStatus.OK;
        }

        dataReturn.setStatus(status);
        dataReturn.setMessage(message);

        return new ResponseEntity<>(dataReturn, httpStatus);
    }

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody User user) {
        try{
            var dataUser = repository.findByEmail(user.getEmail());
            GenericReturn returns = new GenericReturn();
            Token token = new Token();

            if (Objects.equals(dataUser.get().getPassword(), user.getPassword())) {
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

                token.setToken(jwtToken);

                return new ResponseEntity<>(token, HttpStatus.CREATED);

            } else {
                returns.setStatus(false);
                returns.setMessage("Usuário não encontrado");

                return new ResponseEntity<>(returns, HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel acessar");
        }
    }

}
