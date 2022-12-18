package br.com.xxx.ecommerce.desafio1.models;

import br.com.xxx.ecommerce.desafio1.enums.StatusEmail;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Table(name = "emails")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long emailId;
    private String ownerRef;

    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDateEmail;

    private StatusEmail statusEmail;

}