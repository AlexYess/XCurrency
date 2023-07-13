package com.example.CurrencyExchange;

import com.example.CurrencyExchange.controller.CurrencyController;
import com.example.CurrencyExchange.dto.CurrencyInput;
import com.example.CurrencyExchange.model.CurrencyObject;
import com.example.CurrencyExchange.repository.CurrencyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CurrencyExchangeApplication {

	private CurrencyController currencyController;

	public CurrencyExchangeApplication(CurrencyController currencyController) {
		this.currencyController = currencyController;
	}

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeApplication.class, args);
	}
}
