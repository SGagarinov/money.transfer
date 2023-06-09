package com.transfer.money.repository;

import com.transfer.money.entity.Card;
import com.transfer.money.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardDatabaseImpl implements CardDatabase {

    ConcurrentHashMap<String, Card> cards = new ConcurrentHashMap<>();
    ConcurrentHashMap<UUID, Transaction> transactions = new ConcurrentHashMap<>();

    public void init() {
        cards.put("4960142027505908", new Card("4960142027505908", "10/27", (short)632, "Norimi", "Hsia", 500000L));
        cards.put("4960141276748052", new Card("4960141276748052", "12/30", (short)526, "Tefila", "Watts", 123000L));
        cards.put("4960142841701642", new Card("4960142841701642", "11/28", (short)454, "Ulrik", "Tsui", 127300L));
        cards.put("4960147466192985", new Card("4960147466192985", "11/22", (short)997, "Anel", "Kirillov", 999900L));
    }

    @Override
    public Card getCard(String cardNumber) {
        //Возвращаем карту
        return cards.get(cardNumber);
    }

    @Override
    public Transaction putTransaction(UUID uuid, Transaction transaction) {
        return transactions.put(uuid, transaction);
    }

    @Override
    public Transaction getTransaction(UUID uuid) {
        return transactions.get(uuid);
    }
}
