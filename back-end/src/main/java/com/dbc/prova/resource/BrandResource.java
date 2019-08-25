package com.dbc.prova.resource;

import com.dbc.prova.dto.BrandDTO;
import com.dbc.prova.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandResource {

    private BrandService brandService;

    public BrandResource(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> findAll() {
        List<BrandDTO> list = brandService.findAll();

        return ResponseEntity.ok(list);
    }

}
