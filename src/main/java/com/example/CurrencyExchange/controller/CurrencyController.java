package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.service.CurrencyService;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;
    private static final String[] baseUrlsArr = new String[]{"https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/", "https://raw.githubusercontent.com/fawazahmed0/currency-api/1/"};
    private static final String[] suffixArr = new String[]{".min.json", ".json"};
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @GetMapping(path = "/currencies")
    public ResponseEntity<JsonNode> getAllCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);
        return response;
    }
    @PostMapping(path = "/currencies")
    public void insertCurrency(@RequestBody Currency newCurrency) {
        try {
            currencyService.insertCurrency(newCurrency);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping(path = "/currencies/{currencyCode}")
    public ResponseEntity<JsonNode> getCurrencyCode(@PathVariable("currencyCode") String code) {
//        Optional<Currency> currentCurrency = currencyService.getCurrency(code);
//        if (currentCurrency.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/%s.json", code);

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(apiUrl, JsonNode.class);
        return response;
    }

//    @GetMapping(path = "/currencies/{currencyCode1}/{currencyCode2}")
//    public String getCurrencyRate(@PathVariable("currencyCode1") String code1, @PathVariable("currencyCode2") String code2) {
//
//        return restTemplate.getForObject("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json", Currency.class).toString();
//    }
}
