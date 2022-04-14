public class Card implements Comparable<Card> {

    // CLUBS - A char value that represents the suit “Clubs.” Set this equal to ‘c’.
    // DIAMONDS - A char value that represents the suit “Diamonds.” Set this equal
    // to ‘d’.
    // SPADES - A char value that represents the suit “Spades.” Set this equal to
    // ‘s’.
    // HEARTS - A char value that represents the suit “Hearts.” Set this equal to
    // ‘h’.
    // LOWEST_VALUE - The lowest numeric value for a card. Set this equal to 2.
    // HIGHEST_VALUE - The highest numeric value for a card. Set this equal to 14.

    public static final char CLUBS = 'c';
    public static final char DIAMONDS = 'd';
    public static final char SPADES = 's';
    public static final char HEARTS = 'h';
    public static final int LOWEST_VALUE = 2;
    public static final int HIGHEST_VALUE = 14;

    // value - An integer that represents the value shown on the card (a Jack would
    // be 11, a Queen would be 12, a King would be 13, and an Ace would be 14)
    // suit - A char that represents the suit shown on the card, based on the values
    // seen in the class constants

    private int value;
    private char suit;

    /**
     * This method is used for sorting the cards in a player's hand in a game of
     * Poker. Cards are sorted first by value, then by suit.
     *
     * @param other
     *              The Card object to which this Card is being compared.
     * @return negative value if this Card should be before the other Card,
     *         positive value if this Card should be after the other Card.
     */
    public int compareTo(Card other) {
        if (this.value != other.value) {
            return this.value - other.value;
        } else {
            return this.suit - other.suit;
        }
    }

    // The following is the list of methods and constructor you must complete.

    // public Card(int value, char suit): This is the constructor of the class. It
    // accepts a value and a suit to assign to the instance fields.
    // Throw an IllegalArgumentException with the message “Invalid value”, if the
    // value is not valid, and the message “Invalid suit”, if the suit is not valid.
    // NOTE: You must check for these exceptions in this order.
    // public int getValue(): This is the getter method for the instance field that
    // knows the value of the card. It simply returns the instance field.
    // public char getSuit(): This is the getter method for the instance field that
    // knows the suit of the card. It simply returns the instance field.
    // public boolean equals(Object o): This method returns whether this Card and o
    // are equal. If o is not a Card, the method should return false. Cards are
    // considered equal if they have the same value and same suit.
    // public String toString(): This method returns the the first letter of the
    // suit (lowercase) followed by the numeric value of the card. You must put
    // together a String based on the suit and value of the card and return it. For
    // example:
    // 2 of Clubs - “c2”
    // 5 of Diamonds - “d5”
    // Jack of Hearts - “h11”
    // Ace of Spades - “s14”

    public Card(int value, int i) {
        if (value < LOWEST_VALUE || value > HIGHEST_VALUE) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (i != CLUBS && i != DIAMONDS && i != SPADES && i != HEARTS) {
            throw new IllegalArgumentException("Invalid suit");
        }
        this.value = value;
        this.suit = i;
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    public boolean equals(Object o) {
        if (o instanceof Card) {
            Card other = (Card) o;
            return this.value == other.value && this.suit == other.suit;
        } else {
            return false;
        }
    }

    public String toString() {
        String valueString = "";
        if (value == 11) {
            valueString = "J";
        } else if (value == 12) {
            valueString = "Q";
        } else if (value == 13) {
            valueString = "K";
        } else if (value == 14) {
            valueString = "A";
        } else {
            valueString = Integer.toString(value);
        }
        return Character.toString(suit) + valueString;
    }

}
