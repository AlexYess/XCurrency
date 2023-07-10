package com.example.CurrencyExchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    List<Currency> findByCodeContainsIgnoreCase(String code);
}
