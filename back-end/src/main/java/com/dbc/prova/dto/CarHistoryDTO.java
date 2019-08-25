package com.dbc.prova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CarHistoryDTO implements Serializable {

    private static final long serialVersionUID = 6224540065526354569L;

    private Integer id;

    @JsonProperty("ano_modelo")
    private String model;
    private String name;

    @JsonProperty("preco")
    private String price;
    private String valueChanged;
    private String percentageChanged;
    private String baseYear;

}
