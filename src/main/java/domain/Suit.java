package domain;

public enum Suit {
    DIAMONDS("D"),
    CLUBS("C"),
    HEART("H"),
    SPADES("S");

    private final String representation;

    Suit(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
