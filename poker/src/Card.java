public class Card implements Comparable<Card> {

    public static final char CLUBS = 'c';
    public static final char DIAMONDS = 'd';
    public static final char SPADES = 's';
    public static final char HEARTS = 'h';
    public static final int LOWEST_VALUE = 2;
    public static final int HIGHEST_VALUE = 14;

    private int value;
    private char suit;

    /**
     * Constructs a Card with the given value and suit.
     * Throws an IllegalArgumentException if the value is not between 2 and 14,
     * or if the suit is not one of c, d, s, h.
     */
    public Card(int value, char suit)  {
        if (value < LOWEST_VALUE || value > HIGHEST_VALUE) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (suit != CLUBS && suit != DIAMONDS && suit != SPADES && suit != HEARTS) {
            throw new IllegalArgumentException("Invalid suit");
        }
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    /**
     * Compares first by value, then by suit.
     */
    public int compareTo(Card other) {
        if (this.value != other.value) {
            return this.value - other.value;
        } else {
            return this.suit - other.suit;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            Card other = (Card) o;
            return this.value == other.value && this.suit == other.suit;
        }
        return false;
    }

    /**
     * Returns a string like "c2", "d5", "h11" or "s14".
     * This implementation always returns the numeric value for consistency with image file names.
     */
    @Override
    public String toString() {
        return suit + Integer.toString(value);
    }
}
