package com.dbc.prova.service;

import com.dbc.prova.dto.BrandDTO;
import com.dbc.prova.property.FipeProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BrandService {

    private final String BRAND_SUFFIX_URL = "/carros/marcas.json";

    private RestTemplate restTemplate;
    private String brandUrl;

    public BrandService(RestTemplateBuilder restTemplateBuilder, FipeProperty fipeProperty) {
        this.restTemplate = restTemplateBuilder.build();
        this.brandUrl = fipeProperty.getBaseUrl() + BRAND_SUFFIX_URL;
    }

    public List<BrandDTO> findAll() {
        BrandDTO[] brandDTOS = restTemplate.getForObject(brandUrl, BrandDTO[].class);

        if (brandDTOS == null) {
            return Collections.emptyList();
        }

        return Arrays.asList(brandDTOS);
    }

}
