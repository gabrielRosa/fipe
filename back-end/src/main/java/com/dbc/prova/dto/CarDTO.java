package com.dbc.prova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO implements Serializable {

    private static final long serialVersionUID = -9045007531565515350L;

    private String id;
    private String name;

    @JsonProperty("fipe_codigo")
    private String model;

}
