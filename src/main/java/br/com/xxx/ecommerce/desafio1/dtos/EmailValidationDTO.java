package br.com.xxx.ecommerce.desafio1.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailValidationDTO implements Serializable {

    public EmailValidationDTO() {

    }

    private String email;
    private String autocorrect;
    private String deliverability;
    private String quality_score;
    Is_valid_format Is_valid_format;
    Is_free_email Is_free_email;
    Is_disposable_email Is_disposable_email;
    Is_role_email Is_role_email;
    Is_catchall_email Is_catchall_email;
    Is_mx_found Is_mx_found;
    Is_smtp_valid Is_smtp_valid;

    @Data
    public class Is_smtp_valid {
        private boolean value;
        private String text;
    }

    @Data
    public class Is_mx_found {
        private boolean value;
        private String text;
    }

    @Data
    public class Is_catchall_email {
        private boolean value;
        private String text;
    }

    @Data
    public class Is_role_email {
        private boolean value;
        private String text;
    }

    @Data
    public class Is_disposable_email {
        private boolean value;
        private String text;
    }

    @Data
    public class Is_free_email {
        private boolean value;
        private String text;
    }

    @Data
    public class Is_valid_format {
        private boolean value;
        private String text;
    }
}