package domain;

public enum Rank {
    ACE("A"),
    KING("K"),
    QUEEN("Q"),
    JACK("J"),
    TEN("10"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ;

    private final String inputNeededForRank;

    Rank(String inputNeededForRank){
        this.inputNeededForRank = inputNeededForRank;
   }

    public String getRepresentation() {
        return inputNeededForRank;
    }
}
