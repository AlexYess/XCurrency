package com.example.CurrencyExchange.repository;

import com.example.CurrencyExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findByUserID(Long userID);

    User findByUserID(Long id);

    User findByFirstName(String name);

    List<User> findByLastName(String name);

    List<User> findByUsername(String name);
}
