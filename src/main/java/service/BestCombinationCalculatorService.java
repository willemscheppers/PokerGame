package service;

import domain.Card;
import domain.Combination;
import domain.Rank;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.Combination.*;

public class BestCombinationCalculatorService {

    public List<Rank> calculateBestCards(Collection<Card> cards) {
        return cards.stream()
                .map(Card::getRank)
                .sorted()
                .limit(5)
                .toList();
    }

    public Combination getCombination(Collection<Card> cards) {
        if (hasFlush(cards)) {
            return FLUSH;
        }
        if(hasStraight(cards)) {
            return STRAIGHT;
        }
        if(hasThreeOfAKind(cards)) {
            return THREE_OF_A_KIND;
        }
        if (hasPair(cards)) {
            return PAIR;
        }
        return HIGH_CARD;
    }

    private boolean hasFlush(Collection<Card> cards) {
        return cards.stream().collect(Collectors.groupingBy(Card::getSuit, Collectors.counting())).values().stream().anyMatch(c -> c == 5);
    }

    private boolean hasStraight(Collection<Card> cards) {
        List<Card> sortedCards = cards.stream().sorted(Comparator.comparing(Card::getRank)).toList();
        int incrementalCards = 1;
        for (int i = 1; i < sortedCards.size(); i++) {
            Card previousCard = sortedCards.get(i-1);
            Card currentCard = sortedCards.get(i);
            if(currentCard.getRank().ordinal() - previousCard.getRank().ordinal() != 1) {
                incrementalCards = 1;
            } else {
                incrementalCards++;
                if(incrementalCards == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasThreeOfAKind(Collection<Card> cards) {
        return cards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting())).values().stream().anyMatch(c -> c == 3);
    }

    private boolean hasPair(Collection<Card> cards) {
        Map<Rank, Long> counts = cards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return counts.values().stream().anyMatch(count -> count == 2);
    }
}
