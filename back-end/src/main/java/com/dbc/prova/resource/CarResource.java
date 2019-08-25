package com.dbc.prova.resource;

import com.dbc.prova.dto.CarDTO;
import com.dbc.prova.dto.CarHistoryDTO;
import com.dbc.prova.service.CarService;
import com.dbc.prova.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/car")
public class CarResource {

    private CarService carService;
    private UserService userService;

    public CarResource(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/all/{brandId}")
    public ResponseEntity<List<CarDTO>> findAllByBrandId(@PathVariable("brandId") Integer brandId, Principal principal) {
        List<CarDTO> carDTOList = carService.findAllByBrandId(brandId);

        userService.addHistory(principal.getName(), "Brand id: " + brandId);

        return ResponseEntity.ok(carDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CarHistoryDTO>> findHistoryByIdAndModels(
            @PathVariable Integer id,
            @RequestParam(value = "brandId") Integer brandId,
            Principal principal) throws ExecutionException, InterruptedException {
        List<CarHistoryDTO> carDTOList = carService.findHistoryByIdAndBrandId(id, brandId);

        userService.addHistory(principal.getName(), String.format("Car id: %s, Brand id: %s", id, brandId));

        return ResponseEntity.ok(carDTOList);
    }

}
