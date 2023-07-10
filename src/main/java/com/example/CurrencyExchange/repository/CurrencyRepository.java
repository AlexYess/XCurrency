package com.example.CurrencyExchange.repository;

import com.example.CurrencyExchange.model.CurrencyObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyObject, String> {
    List<CurrencyObject> findByNameContainsIgnoreCase(String name);
}
