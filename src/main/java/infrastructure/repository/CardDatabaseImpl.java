package infrastructure.repository;

import domain.entity.Card;
import domain.repository.CardDatabase;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class CardDatabaseImpl implements CardDatabase {

    HashMap<String, Card> cards = new HashMap<>();

    public CardDatabaseImpl() {
        //TODO добавляем карты
    }

    @Override
    public void transfer() {

    }
}
