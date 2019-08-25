package com.dbc.prova.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties(PropertyPath.FIPE_PROPERTIES)
@Validated
public class FipeProperty {

    @NotBlank
    @Getter
    @Setter
    private String baseUrl;

}
