package domain;

import service.BestCombinationCalculatorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {

    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public void play() {
        // Reading hand data using readLine
        System.out.println("(2-10JQKA){C,H,D,S}");
        Card card1 = getCard("Card 1:", reader);
        Card card2 = getCard("Card 2:", reader);


        // Reading table data using readLine
        Card flop1 = getCard("Flop card 1", reader);
        Card flop2 = getCard("Flop card 2", reader);
        Card flop3 = getCard("Flop card 3", reader);
        Card turn = getCard("Turn card ", reader);
        Card river = getCard("River card ", reader);

        //Find best combination for player
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(flop1);
        cards.add(flop2);
        cards.add(flop3);
        cards.add(turn);
        cards.add(river);

        BestCombinationCalculatorService calculatorService = new BestCombinationCalculatorService();
        List<Rank> ranks = calculatorService.calculateBestCards(cards);

        ranks.forEach(System.out::println);
    }

    private Card readCard(BufferedReader reader) {
        try {
            String cardString = reader.readLine();
            return Card.createCard(cardString);
        }catch (IOException e){
            System.out.println("error while reading card");
            return null;
        }
    }

    private Card getCard(String x, BufferedReader reader) {
        System.out.println(x);
        return readCard(reader);
    }
}
