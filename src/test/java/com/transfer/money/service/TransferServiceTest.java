package com.transfer.money.service;

import com.transfer.money.dto.Amount;
import com.transfer.money.dto.ConfirmProperties;
import com.transfer.money.dto.Response;
import com.transfer.money.dto.TransferInfo;
import com.transfer.money.exception.*;
import com.transfer.money.repository.CardDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @Autowired
    private CardDatabase cardDatabase;

    @Test
    void serviceCardFromNotFound() throws Exception {
        String number = "3251";
        TransferInfo transferInfo = new TransferInfo(number, null, null, "4960142841701642", null);
        Throwable exception = assertThrows(CardNotFoundException.class, () ->
                transferService.transfer(transferInfo));
        assertNotNull(exception);
        assertEquals("Card not found", exception.getMessage());
    }

    @Test
    void serviceCardToNotFound() throws Exception {
        String number = "6532";
        TransferInfo transferInfo = new TransferInfo("4960142027505908", null, null, number, null);
        Throwable exception = assertThrows(CardNotFoundException.class, () ->
                transferService.transfer(transferInfo));
        assertNotNull(exception);
        assertEquals("Card not found", exception.getMessage());
    }

    @Test
    void serviceIncorrectCVV() throws Exception {
        String numberFrom = "4960142027505908";
        String numberTo = "4960142841701642";
        TransferInfo transferInfo = new TransferInfo(numberFrom, "12/23", (short)125, numberTo, null);

        Throwable exception = assertThrows(InvalidCardInfoException.class, () ->
                transferService.transfer(transferInfo));
        assertNotNull(exception);
        assertEquals("Incorrect card data", exception.getMessage());
    }

    @Test
    void serviceIncorrectDate() throws Exception {
        String numberFrom = "4960142027505908";
        String numberTo = "4960142841701642";
        TransferInfo transferInfo = new TransferInfo(numberFrom, "12/23", (short)125, numberTo, null);

        Throwable exception = assertThrows(InvalidCardInfoException.class, () ->
                transferService.transfer(transferInfo));
        assertNotNull(exception);
        assertEquals("Incorrect card data", exception.getMessage());
    }

    @Test
    void serviceNotMoney() throws Exception {
        String numberFrom = "4960142027505908";
        String numberTo = "4960142841701642";
        TransferInfo transferInfo = new TransferInfo(numberFrom, "10/27", (short)632, numberTo, new Amount(9999999L, "RUB"));

        Throwable exception = assertThrows(InsufficientFundsException.class, () ->
                transferService.transfer(transferInfo));
        assertNotNull(exception);
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void transferSuccess() throws Exception {
        String numberFrom = "4960142027505908";
        String numberTo = "4960142841701642";
        TransferInfo transferInfo = new TransferInfo(numberFrom, "10/27", (short)632, numberTo, new Amount(1000L, "RUB"));

        Response result = transferService.transfer(transferInfo);
        assertNotNull(result.operationId());
    }

    @Test
    void transferConfirmNotCode() throws Exception {
        String numberFrom = "4960142027505908";
        String numberTo = "4960142841701642";
        TransferInfo transferInfo = new TransferInfo(numberFrom, "10/27", (short)632, numberTo, new Amount(1000L, "RUB"));

        Response result = transferService.transfer(transferInfo);
        assertNotNull(result.operationId());

        ConfirmProperties confirmProperties = new ConfirmProperties(result.operationId().toString(), null);
        Throwable exception = assertThrows(InvalidVerificationCodeException.class, () ->
                transferService.confirm(confirmProperties));
        assertNotNull(exception);
        assertEquals("Invalid verification code", exception.getMessage());
    }

    @Test
    void transferConfirmNotFoundTransaction() throws Exception {
        ConfirmProperties confirmProperties = new ConfirmProperties(UUID.randomUUID().toString(), "643");
        Throwable exception = assertThrows(TransactionNotFoundException.class, () ->
                transferService.confirm(confirmProperties));
        assertNotNull(exception);
        assertEquals("Transaction not found", exception.getMessage());
    }

    @Test
    void transferTransactionConfirmed() throws Exception {
        String numberFrom = "4960142027505908";
        String numberTo = "4960142841701642";
        TransferInfo transferInfo = new TransferInfo(numberFrom, "10/27", (short)632, numberTo, new Amount(1000L, "RUB"));

        Response result = transferService.transfer(transferInfo);
        assertNotNull(result.operationId());

        ConfirmProperties confirmProperties = new ConfirmProperties(result.operationId().toString(), "534");
        transferService.confirm(confirmProperties);

        Throwable exception = assertThrows(TransactionConfirmedException.class, () ->
                transferService.confirm(confirmProperties));
        assertNotNull(exception);
        assertEquals("Transaction confirmed", exception.getMessage());
    }

    @Test
    void transferConfirmSuccess() throws Exception {
        String numberFrom = "4960142027505908";
        String numberTo = "4960142841701642";
        TransferInfo transferInfo = new TransferInfo(numberFrom, "10/27", (short)632, numberTo, new Amount(1000L, "RUB"));

        Response result = transferService.transfer(transferInfo);
        assertNotNull(result.operationId());

        ConfirmProperties confirmProperties = new ConfirmProperties(result.operationId().toString(), "534");
        transferService.confirm(confirmProperties);
    }
}
