import domain.Card;
import domain.Game;
import domain.Rank;

import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    public static void main(String[] args) {
        playGame();
    }

    private static void playGame()  {
        // Enter data using BufferReader
        new Game().play();
    }

    private static List<Rank> calculateBestCards(ArrayList<Card> cards) {
        return cards.stream()
                .map(Card::getRank)
                .sorted()
                .limit(5)
                .toList();
    }


}
