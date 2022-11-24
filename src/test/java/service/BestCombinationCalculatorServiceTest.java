package service;

import domain.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static domain.Combination.*;
import static org.assertj.core.api.Assertions.assertThat;

class BestCombinationCalculatorServiceTest {

    BestCombinationCalculatorService service = new BestCombinationCalculatorService();

    @Test
    void getBestCombination_highCard() {
        Collection<Card> cards = new ArrayList<>();
        cards.add(Card.createCard("2D"));
        cards.add(Card.createCard("4C"));
        cards.add(Card.createCard("6H"));
        cards.add(Card.createCard("8H"));
        cards.add(Card.createCard("10D"));
        cards.add(Card.createCard("AD"));
        cards.add(Card.createCard("JH"));

        assertThat(service.getCombination(cards)).isEqualTo(HIGH_CARD);
    }

    @Test
    void getBestCombination_pair() {
        Collection<Card> cards = new ArrayList<>();
        cards.add(Card.createCard("2D"));
        cards.add(Card.createCard("2C"));
        cards.add(Card.createCard("6H"));
        cards.add(Card.createCard("8H"));
        cards.add(Card.createCard("10D"));
        cards.add(Card.createCard("AD"));
        cards.add(Card.createCard("JH"));

        assertThat(service.getCombination(cards)).isEqualTo(PAIR);
    }

    @Test
    void getBestCombination_straight() {
        Collection<Card> cards = new ArrayList<>();
        cards.add(Card.createCard("2D"));
        cards.add(Card.createCard("3C"));
        cards.add(Card.createCard("4H"));
        cards.add(Card.createCard("5H"));
        cards.add(Card.createCard("6D"));
        cards.add(Card.createCard("AD"));
        cards.add(Card.createCard("JH"));

        assertThat(service.getCombination(cards)).isEqualTo(STRAIGHT);
    }
}