package domain;

import java.util.Arrays;

public class Card {
    Suit suit;
    Rank rank;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card createCard(String input) {
        input = input.trim();
        input = input.toUpperCase();

        String suitString = input.substring(input.length() - 1);
        String rankString = input.substring(0,input.length() - 1);
        try {
            Suit suit = Arrays.stream(Suit.values())
                    .filter(suit1 -> suit1.getRepresentation().equals(suitString))
                    .findFirst().orElseThrow();
            Rank rank = Arrays.stream(Rank.values())
                    .filter(rank1 -> rank1.getRepresentation().equals(rankString))
                    .findFirst().orElseThrow();
            return new Card(rank,suit);
        }catch (Exception e){
            System.out.println("Card creation failed");
            return null;
        }

    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
