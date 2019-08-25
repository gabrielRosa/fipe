package com.dbc.prova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandDTO implements Serializable {

    private static final long serialVersionUID = -4327958215254822117L;

    private Integer id;
    private String name;

}
