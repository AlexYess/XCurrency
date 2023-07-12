package com.example.CurrencyExchange.repository;

import com.example.CurrencyExchange.model.Friend;
import com.example.CurrencyExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Long> findFriendIDByUserID(Long userID);

    List<Friend> findAllByUserID(Long userID);

    void deleteByUserIDAndFriendID(Long userID, Long friendID);

    Friend  findByUserID(Long userID);
}
