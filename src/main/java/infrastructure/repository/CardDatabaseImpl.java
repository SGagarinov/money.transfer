package infrastructure.repository;

import com.transfer.money.transfer.config.ServiceConfig;
import domain.entity.Card;
import domain.entity.transfer.TransferInfo;
import domain.repository.CardDatabase;
import infrastructure.repository.exception.CardNotFoundException;
import infrastructure.repository.exception.InsufficientFundsException;
import infrastructure.repository.exception.InvalidCardInfoException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Objects;

@Repository
public class CardDatabaseImpl implements CardDatabase {

    HashMap<String, Card> cards = new HashMap<>();

    public CardDatabaseImpl() {
        cards.put("4960142027505908", new Card("4960142027505908", "10/2027", (short)632, "Norimi", "Hsia", 5000.0));
        cards.put("4960141276748052", new Card("4960141276748052", "12/2030", (short)526, "Tefila", "Watts", 1230.0));
        cards.put("4960142841701642", new Card("4960142841701642", "11/2028", (short)454, "Ulrik", "Tsui", 1273.0));
        cards.put("4960147466192985", new Card("4960147466192985", "11/2022", (short)997, "Anel", "Kirillov", 9999.0));
    }

    @Override
    public void transfer(TransferInfo transferInfo) {
        try {
            Card cardFrom = cards.get(transferInfo.getProperties().getCardFromNumber());
            Card cardTo = cards.get(transferInfo.getProperties().getCardToNumber());
            if (cardFrom == null || cardTo == null)
                throw new CardNotFoundException("Card not found", new RuntimeException());
            boolean check = checkCard(cardFrom, transferInfo.getProperties().getCardFromValidTill(), transferInfo.getProperties().getCardFromCVV());
            if (!check)
                throw new InvalidCardInfoException("Incorrect card data", new RuntimeException());
            if (transferInfo.getAmount().getValue() > cardFrom.getBalance())
                throw new InsufficientFundsException("Insufficient funds", new RuntimeException());
        }
        catch (Exception ex) {

        }
    }

    private boolean checkCard(Card card, String date, Short cvv) {
        if (!Objects.equals(card.getCvv(), cvv))
            return false;
        return Objects.equals(card.getDate(), date);
    }

    private Double toUSD(Double input) {
        return input/ ServiceConfig.DOLLAR;
    }

    private Double toEUR(Double input) {
        return input/ ServiceConfig.EURO;
    }
}
