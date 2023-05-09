package com.transfer.money.infrastructure.repository;

import com.transfer.money.domain.entity.log.LogInfo;
import com.transfer.money.infrastructure.exception.*;
import com.transfer.money.transfer.config.ServiceConfig;
import com.transfer.money.domain.entity.Card;
import com.transfer.money.domain.entity.confirm.ConfirmProperties;
import com.transfer.money.domain.entity.transfer.Transaction;
import com.transfer.money.domain.entity.transfer.TransferInfo;
import com.transfer.money.domain.repository.CardDatabase;
import com.transfer.money.transfer.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Repository
public class CardDatabaseImpl implements CardDatabase {

    HashMap<String, Card> cards = new HashMap<>();
    HashMap<UUID, Transaction> transactions = new HashMap<>();
    @Autowired
    LoggingService loggingService;


    public CardDatabaseImpl() {

    }

    public void init() {
        cards.put("4960142027505908", new Card("4960142027505908", "10/27", (short)632, "Norimi", "Hsia", 500000L));
        cards.put("4960141276748052", new Card("4960141276748052", "12/30", (short)526, "Tefila", "Watts", 123000L));
        cards.put("4960142841701642", new Card("4960142841701642", "11/28", (short)454, "Ulrik", "Tsui", 127300L));
        cards.put("4960147466192985", new Card("4960147466192985", "11/22", (short)997, "Anel", "Kirillov", 999900L));
    }

    @Override
    public UUID transfer(TransferInfo transferInfo) throws Exception {
        //Получение карт
        Card cardFrom = cards.get(transferInfo.getCardFromNumber());
        Card cardTo = cards.get(transferInfo.getCardToNumber());

        //Проверка карт
        if (cardFrom == null || cardTo == null)
            throw new CardNotFoundException("Card not found");
        boolean check = checkCard(cardFrom, transferInfo.getCardFromValidTill(), transferInfo.getCardFromCVV());
        if (!check)
            throw new InvalidCardInfoException("Incorrect card data");
        if (transferInfo.getAmount().getValue() > cardFrom.getBalance())
            throw new InsufficientFundsException("Insufficient funds");

        UUID uuid = UUID.randomUUID();

        //Добавляем транзакцию
        transactions.put(uuid, new Transaction(cardFrom, cardTo, transferInfo, false));

        loggingService.log(new LogInfo(cardFrom.getNumber(), cardTo.getNumber(), transferInfo.getAmount().getValue(), 1, false));
        //Возвращаем id
        return uuid;
    }

    @Override
    public UUID confirmOperation(ConfirmProperties confirmProperties) throws Exception {

        //Проверяем наличие кода
        if (confirmProperties.getCode() == null || confirmProperties.getCode().isEmpty())
            throw new InvalidVerificationCodeException("Invalid verification code");

        //Ищем транзакцию
        Transaction transaction = transactions.get(UUID.fromString(confirmProperties.getOperationId()));
        if (transaction == null)
            throw new TransactionNotFoundException("Transaction not found");

        //Проверяем, что транзакция не закрыта
        if (Boolean.TRUE.equals(transaction.getConfirm()))
            throw new TransactionConfirmedException("Transaction confirmed");

        Card cardFrom = transaction.getCardFrom();
        Card cardTo = transaction.getCardTo();
        TransferInfo transferInfo = transaction.getTransferInfo();

        //Перевод средств
        Long value = (transferInfo.getAmount().getValue() / 100) * (100 - ServiceConfig.COMMISSION);

        cardFrom.setBalance(cardFrom.getBalance() - transferInfo.getAmount().getValue());
        cardTo.setBalance(cardTo.getBalance() + value);
        transaction.setConfirm(true);
        loggingService.log(new LogInfo(cardFrom.getNumber(), cardTo.getNumber(), transferInfo.getAmount().getValue(), 1, true));

        return UUID.randomUUID();
    }

    private boolean checkCard(Card card, String date, Short cvv) {
        if (!Objects.equals(card.getCvv(), cvv))
            return false;
        return Objects.equals(card.getDate(), date);
    }

    //Перевод в доллары
    private Double toUSD(Double input) {
        return input/ServiceConfig.DOLLAR;
    }

    //Перевод в евро
    private Double toEUR(Double input) {
        return input/ServiceConfig.EURO;
    }
}
