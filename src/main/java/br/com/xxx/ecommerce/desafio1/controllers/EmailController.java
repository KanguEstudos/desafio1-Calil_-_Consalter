package br.com.xxx.ecommerce.desafio1.controllers;

import br.com.xxx.ecommerce.desafio1.dtos.EmailDTO;
import br.com.xxx.ecommerce.desafio1.models.EmailModel;
import br.com.xxx.ecommerce.desafio1.services.EmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {

        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);

//        String routingkey = "kangu.ms";
//        rabbitTemplate.convertAndSend(routingkey, emailModel);

        emailService.sendEmail(emailModel);

        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);

    }

    @GetMapping("/test")
    public String[] teste()
    {




        String[] teste = {"Fusca", "Brasilia", "Gurgel"};

        String url = "https://brasilapi.com.br/api/cep/v2/13346490";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        System.out.println(result);

        return teste;

    }

}
