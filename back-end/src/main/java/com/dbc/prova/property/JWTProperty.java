package com.dbc.prova.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(PropertyPath.JWT_PROPERTIES)
@Validated
@Getter
@Setter
public class JWTProperty {

    @NotBlank
    private String secret;

    @NotNull
    private Long expiration;

}
