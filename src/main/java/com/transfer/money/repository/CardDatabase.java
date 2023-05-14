package com.transfer.money.repository;

import com.transfer.money.entity.Card;
import com.transfer.money.entity.Transaction;

import java.util.UUID;

public interface CardDatabase {
    Card getCard(String cardNumber) throws Exception;
    Transaction putTransaction(UUID uuid, Transaction transaction);
    Transaction getTransaction(UUID uuid) throws Exception;

    void init();
}
