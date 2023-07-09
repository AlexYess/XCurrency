package com.example.CurrencyExchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.List;

public class CurrencyRepository {
    @Repository
    public interface SongRepository extends JpaRepository<Currency, Long> {
        List<Currency> findByTitleContainsIgnoreCase(String name);
    }
}
