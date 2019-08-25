package com.dbc.prova.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class CredentialDTO implements Serializable {

    private static final long serialVersionUID = -3370023941118555371L;

    @NotEmpty(message = "Required!")
    @Email(message = "Email invalid!")
    private String email;

    @NotEmpty(message = "Required!")
    private String password;

}
