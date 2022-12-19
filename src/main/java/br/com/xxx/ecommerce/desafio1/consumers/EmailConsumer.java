package br.com.xxx.ecommerce.desafio1.consumers;

import br.com.xxx.ecommerce.desafio1.dtos.EmailDTO;
import br.com.xxx.ecommerce.desafio1.models.EmailModel;
import br.com.xxx.ecommerce.desafio1.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload String data) {
        Gson gson = new Gson();
        EmailDTO emailDTO = gson.fromJson(data, EmailDTO.class);
        //System.out.println(emailDTO);
        System.out.println("");

        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        emailService.sendEmail(emailModel);
        System.out.println("Email Text: " + emailModel.getText());
        System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
    }
    /*public void listen(@Payload EmailDTO emailDTO) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        emailService.sendEmail(emailModel);
        System.out.println("Email Text: " + emailModel.getText());
        System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
    }*/
    /*public void listen(@Payload String data) {
        Gson gson = new Gson();
        EmailDTO emailDTO = gson.fromJson(data, EmailDTO.class);
        System.out.println(emailDTO);
    }*/

}