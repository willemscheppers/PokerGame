package service;

import domain.Card;
import domain.Combination;
import domain.Rank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static domain.Combination.*;

public class BestCombinationCalculatorService {

    public List<Rank> calculateBestCards(Collection<Card> cards) {
        return cards.stream()
                .map(Card::getRank)
                .sorted()
                .limit(5)
                .toList();
    }

    interface CombinationCheck {
        Optional<Combination> check(Collection<Card> cards);
    }

    public Combination getCombination(Collection<Card> cards) {
        return Stream.of(
                new FourOfAKind(),
                new FullHouse(),
                new Flush(),
                new Straight(),
                new ThreeOfAKind(),
                new Pair()
        ).flatMap(
                (CombinationCheck combo) -> combo.check(cards).stream()
        ).findFirst().orElse(
                HIGH_CARD
        );
    }

    class FourOfAKind implements CombinationCheck {
        @Override
        public Optional<Combination> check(Collection<Card> cards) {
            if (hasNumberOfRank(cards, 4)) {
                return Optional.of(FOUR_OF_A_KIND);
            }
            return Optional.empty();
        }
    }

    static class FullHouse implements CombinationCheck {
        @Override
        public Optional<Combination> check(Collection<Card> cards) {
            Collection<Long> values = cards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting())).values();
            if (values.contains(2L) && values.contains(3L)) {
                return Optional.of(FULL_HOUSE);
            }
            return Optional.empty();
        }

    }

    static class Flush implements CombinationCheck {
        @Override
        public Optional<Combination> check(Collection<Card> cards) {
            if (cards.stream().collect(Collectors.groupingBy(Card::getSuit, Collectors.counting())).values().stream().anyMatch(c -> c == 5)) {
                return Optional.of(FLUSH);
            }
            return Optional.empty();
        }
    }

    static class Straight implements CombinationCheck {
        @Override
        public Optional<Combination> check(Collection<Card> cards) {
            List<Card> sortedCards = cards.stream().sorted(Comparator.comparing(Card::getRank)).toList();
            int incrementalCards = 1;
            for (int i = 1; i < sortedCards.size(); i++) {
                Card previousCard = sortedCards.get(i - 1);
                Card currentCard = sortedCards.get(i);
                if (currentCard.getRank().ordinal() - previousCard.getRank().ordinal() != 1) {
                    incrementalCards = 1;
                } else {
                    incrementalCards++;
                    if (incrementalCards == 5) {
                        return Optional.of(STRAIGHT);
                    }
                }
            }
            return Optional.empty();
        }
    }


    class ThreeOfAKind implements CombinationCheck {
        @Override
        public Optional<Combination> check(Collection<Card> cards) {
            if (hasNumberOfRank(cards, 3)) {
                return Optional.of(THREE_OF_A_KIND);
            }
            return Optional.empty();
        }
    }

    class Pair implements CombinationCheck {
        @Override
        public Optional<Combination> check(Collection<Card> cards) {
            if (hasNumberOfRank(cards, 2)) {
                return Optional.of(PAIR);
            }
            return Optional.empty();
        }
    }

    private boolean hasNumberOfRank(Collection<Card> cards, int number) {
        return cards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting())).values().stream().anyMatch(c -> c == number);
    }
}
