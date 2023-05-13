package com.transfer.money.service;

import com.transfer.money.entity.Card;
import com.transfer.money.entity.Response;
import com.transfer.money.entity.confirm.ConfirmProperties;
import com.transfer.money.entity.log.LogInfo;
import com.transfer.money.entity.transfer.Transaction;
import com.transfer.money.entity.transfer.TransferInfo;
import com.transfer.money.exception.*;
import com.transfer.money.repository.CardDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    @Value("${ commission:1 }")
    public static final Short COMMISSION = 1;
    private CardDatabase cardDatabase;
    private final LoggingService loggingService;

    public TransferServiceImpl(CardDatabase cardDatabase, LoggingService loggingService) {
        this.cardDatabase = cardDatabase;
        this.loggingService = loggingService;
        cardDatabase.init();
    }

    @Override
    public Response transfer(TransferInfo transferInfo) throws Exception {
        //Получение карт
        Card cardFrom = cardDatabase.getCard(transferInfo.getCardFromNumber());
        Card cardTo = cardDatabase.getCard(transferInfo.getCardToNumber());

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
        cardDatabase.putTransaction(uuid, new Transaction(cardFrom, cardTo, transferInfo, false));

        synchronized (loggingService) {
            loggingService.log(new LogInfo(cardFrom.getNumber(), cardTo.getNumber(), transferInfo.getAmount().getValue(), 1, false));
        }

        //Возвращаем id
        return new Response(uuid);
    }

    @Override
    public Response confirm(ConfirmProperties confirmProperties) throws Exception {
        //Проверяем наличие кода
        if (confirmProperties.getCode() == null || confirmProperties.getCode().isEmpty())
            throw new InvalidVerificationCodeException("Invalid verification code");

        //Ищем транзакцию
        Transaction transaction = cardDatabase.getTransaction(UUID.fromString(confirmProperties.getOperationId()));
        if (transaction == null)
            throw new TransactionNotFoundException("Transaction not found");

        //Проверяем, что транзакция не закрыта
        if (Boolean.TRUE.equals(transaction.getConfirm()))
            throw new TransactionConfirmedException("Transaction confirmed");

        Card cardFrom = transaction.getCardFrom();
        Card cardTo = transaction.getCardTo();
        TransferInfo transferInfo = transaction.getTransferInfo();

        //Перевод средств
        Long value = (transferInfo.getAmount().getValue() / 100) * (100 - COMMISSION);

        cardFrom.setBalance(cardFrom.getBalance() - transferInfo.getAmount().getValue());
        cardTo.setBalance(cardTo.getBalance() + value);
        transaction.setConfirm(true);

        synchronized (loggingService) {
            loggingService.log(new LogInfo(cardFrom.getNumber(), cardTo.getNumber(), transferInfo.getAmount().getValue(), 1, true));
        }

        return new Response(UUID.randomUUID());
    }

    private boolean checkCard(Card card, String date, Short cvv) {
        if (!Objects.equals(card.getCvv(), cvv))
            return false;
        return Objects.equals(card.getDate(), date);
    }
}
