package com.transfer.money.repository;

import com.transfer.money.dto.Amount;
import com.transfer.money.dto.Response;
import com.transfer.money.dto.TransferInfo;
import com.transfer.money.entity.Card;
import com.transfer.money.entity.Transaction;
import com.transfer.money.repository.CardDatabase;
import com.transfer.money.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
public class RepoTest {

    @Autowired
    private CardDatabase cardDatabase;

    @Autowired
    private TransferService transferService;

    @Test
    void repoGetCardTest() {
        String number = "4960142027505908";
        Card card = new Card("4960142027505908", "10/27", (short)632, "Norimi", "Hsia", 500000L);
        Card result = cardDatabase.getCard(number);
        assertNotNull(result);
        assertEquals(card.getNumber(), result.getNumber());
        assertEquals(card.getCvv(), result.getCvv());
        assertEquals(card.getBalance(), result.getBalance());
        assertEquals(card.getDate(), result.getDate());
        assertEquals(card.getHolderLastName(), result.getHolderLastName());
        assertEquals(card.getHolderName(), result.getHolderName());
    }

    @Test
    void repoGetTransactionTest() throws Exception {
        TransferInfo transferInfo = new TransferInfo("4960142027505908", "10/27", (short)632, "4960142841701642", new Amount(10000L, "RUR"));
        Response uuid = transferService.transfer(transferInfo);

        Transaction result = cardDatabase.getTransaction(uuid.operationId());
        assertNotNull(result);
        assertEquals(transferInfo.getCardFromNumber(), result.getCardFrom().getNumber());
        assertEquals(transferInfo.getCardFromCVV(), result.getCardFrom().getCvv());
        assertEquals(transferInfo.getCardFromValidTill(), result.getCardFrom().getDate());
        assertEquals(transferInfo.getCardToNumber(), result.getCardTo().getNumber());
        assertEquals(transferInfo.getCardFromNumber(), result.getTransferInfo().getCardFromNumber());
        assertEquals(transferInfo.getCardToNumber(), result.getTransferInfo().getCardToNumber());
        assertEquals(transferInfo.getCardFromCVV(), result.getTransferInfo().getCardFromCVV());
        assertEquals(transferInfo.getCardFromValidTill(), result.getTransferInfo().getCardFromValidTill());
        assertEquals(transferInfo.getAmount().value(), result.getTransferInfo().getAmount().value());
        assertFalse(result.getConfirm());
    }

    @Test
    void repoPutTransaction() {
        Card cardFrom = new Card("4960142027505908", "10/27", (short) 632, "Norimi", "Hsia", 500000L);
        Card cardTo = new Card("4960142841701642", "11/28", (short)454, "Ulrik", "Tsui", 127300L);
        TransferInfo transferInfo = new TransferInfo("4960142027505908", "10/27", (short)632, "4960142841701642", new Amount(10000L, "RUR"));
        UUID uuid = UUID.randomUUID();

        Transaction transaction = new Transaction(cardFrom, cardTo, transferInfo, false);
        cardDatabase.putTransaction(uuid, transaction);
        Transaction result = cardDatabase.getTransaction(uuid);
        assertNotNull(result);
        assertEquals(transaction.getCardFrom().getNumber(), result.getCardFrom().getNumber());
        assertEquals(transaction.getCardTo().getNumber(), result.getCardTo().getNumber());
        assertEquals(transaction.getConfirm(), result.getConfirm());
        assertEquals(transaction.getTransferInfo().getAmount().value(), result.getTransferInfo().getAmount().value());
    }
}
