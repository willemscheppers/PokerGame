package service;

import domain.Card;
import domain.Combination;
import domain.Rank;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.Combination.HIGH_CARD;
import static domain.Combination.PAIR;

public class BestCombinationCalculatorService {

    public List<Rank> calculateBestCards(Collection<Card> cards) {
        return cards.stream()
                .map(Card::getRank)
                .sorted()
                .limit(5)
                .toList();
    }

    public Combination getCombination(Collection<Card> cards) {
        if (hasPair(cards)) {
            return PAIR;
        }
        return HIGH_CARD;
    }

    private boolean hasPair(Collection<Card> cards) {
        Map<Rank, Long> counts = cards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return counts.values().stream().anyMatch(count -> count == 2);
    }
}
