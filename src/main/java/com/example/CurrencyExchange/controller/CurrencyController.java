package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.CurrencyInput;
import com.example.CurrencyExchange.model.CurrencyObject;
import com.example.CurrencyExchange.repository.CurrencyRepository;
import com.example.CurrencyExchange.service.CurrencyService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;
    private CurrencyRepository currencyRepository;
    private static final String[] baseUrlsArr = new String[]{"https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/", "https://raw.githubusercontent.com/fawazahmed0/currency-api/1/"};
    private static final String[] suffixArr = new String[]{".min.json", ".json"};
    public CurrencyController(CurrencyService currencyService, CurrencyRepository currencyRepository) {
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
    }
    @GetMapping(path = "/currencies")
    public ResponseEntity<JsonNode> getAllCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);

        return response;
    }
//    @PostMapping(path = "/currencies")
//    public void insertCurrency(@RequestBody CurrencyObject newCurrency) {
//        try {
//            currencyService.insertCurrency(newCurrency);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }
    @GetMapping(path = "/currencies/{currencyCode}")
    public ResponseEntity<JsonNode> getCurrencyCode(@PathVariable("currencyCode") String code) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = String.format("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/%s.json", code);

            ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);
            return response;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping(path = "/currencies/{currencyCode1}/{currencyCode2}")
    public ResponseEntity<JsonNode> getCurrencyRate(@PathVariable("currencyCode1") String code1, @PathVariable("currencyCode2") String code2) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = String.format("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/%s/%s.json", code1, code2);

            ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);
            return response;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping(path = "/currencies")
    @ResponseStatus(HttpStatus.CREATED)
    public CurrencyObject addCurrency(@RequestBody CurrencyInput currency) {

        CurrencyObject newCurrency = currency.toNewCurrency();
        currencyRepository.save(newCurrency);

        return newCurrency;
    }

    @GetMapping(path = "/generatecurrencies")
    public void regenerateCurrencies() {
        currencyRepository.deleteAll();

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode currenciesJson = response.getBody();

            // Iterate through JSON and convert to CurrencyObject format
            currenciesJson.fields().forEachRemaining(entry -> {
                String currencyCode = entry.getKey();
                String currencyName = entry.getValue().asText();

                CurrencyObject currencyObject = new CurrencyObject(currencyCode, currencyName);
                currencyRepository.save(currencyObject);
            });
        }
    }
}
