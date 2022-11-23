package service;

import domain.Card;
import domain.Rank;

import java.util.Collection;
import java.util.List;

public class BestCombinationCalculatorService {

    public List<Rank> calculateBestCards(Collection<Card> cards) {

        return cards.stream()
                .map(Card::getRank)
                .sorted()
                .limit(5)
                .toList();
    }
}
