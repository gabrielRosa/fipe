package com.dbc.prova.service;

import com.dbc.prova.dto.CarDTO;
import com.dbc.prova.dto.CarHistoryDTO;
import com.dbc.prova.property.FipeProperty;
import com.dbc.prova.service.util.CurrencyUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class CarService {

    private static final String CAR_URL = "/carros/veiculo";
    private static final String CARS_URL = "/carros/veiculos";

    private RestTemplate restTemplate;
    private String baseUrl;

    public CarService(RestTemplateBuilder restTemplateBuilder, FipeProperty fipeProperty) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = fipeProperty.getBaseUrl();
    }

    public List<CarDTO> findAllByBrandId(Integer brandId) {
        String brandString = String.valueOf(brandId);
        CarDTO[] carDTOS = restTemplate.getForObject(getUrl(CARS_URL, brandString), CarDTO[].class);

        if (carDTOS == null) {
            return Collections.emptyList();
        }

        return Arrays.asList(carDTOS);
    }

    public List<CarHistoryDTO> findHistoryByIdAndBrandId(Integer id, Integer brandId) throws ExecutionException, InterruptedException {
        List<String> models = findModelsByIdAndBrandId(id, brandId);
        List<CompletableFuture<CarHistoryDTO>> modelsContentFutures = models.stream().map(s -> findHistoryByIdAndModel(id, brandId, s)).collect(Collectors.toList());
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(modelsContentFutures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<CarHistoryDTO>> allModelContentsFuture = allFutures.thenApply(v -> modelsContentFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        List <CarHistoryDTO> result = allModelContentsFuture.get();

        calculateHistory(result);

        return result;
    }

    private void calculateHistory(List<CarHistoryDTO> carHistoryDTOs) {
        carHistoryDTOs.sort(Comparator.comparingInt(o -> parseInt(o.getModel())));

        double base = 0;
        String baseYear = null;

        for (int i = 0; i < carHistoryDTOs.size(); i++) {
            CarHistoryDTO c = carHistoryDTOs.get(i);
            double price = CurrencyUtil.toDouble(c.getPrice(), Locale.getDefault()).orElseThrow(RuntimeException::new);

            if (i == 0) {
                c.setValueChanged(String.valueOf(0));
            } else {
                double changeValue = price - base;
                double changePercentage = ((changeValue * 100) / base);

                c.setValueChanged(String.valueOf(changeValue));
                c.setPercentageChanged(String.valueOf(CurrencyUtil.round(changePercentage, 2)));
                c.setBaseYear(baseYear);
            }

            base = price;
            baseYear = c.getModel();
        }

    }

    private CompletableFuture<CarHistoryDTO> findHistoryByIdAndModel(Integer id, Integer brandId, String model) {
        String idString = String.valueOf(id);
        String brandString = String.valueOf(brandId);

        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(getUrl(CAR_URL, brandString, idString, model), CarHistoryDTO.class));
    }

    private List<String> findModelsByIdAndBrandId(Integer id, Integer brandId) {
        String brandString = String.valueOf(brandId);
        String idString = String.valueOf(id);
        CarDTO[] carDTOs = restTemplate.getForObject(getUrl(CAR_URL, brandString, idString), CarDTO[].class);

        if (carDTOs == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(carDTOs).map(CarDTO::getModel).collect(Collectors.toList());
    }

    private String getUrl(String path, String ... params) {
        StringBuilder sb = new StringBuilder();

        sb.append(baseUrl);
        sb.append(path);

        for (String s : params) {
            sb.append("/");
            sb.append(s);
        }

        sb.append(".json");

        return sb.toString();
    }

}
