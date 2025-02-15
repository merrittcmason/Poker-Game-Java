import java.util.Arrays;

public class Hand {
    public static final int CARDS_IN_HAND = 5;
    private Card[] hand;

    /**
     * Constructs a Hand with the provided array of 5 cards.
     * @param hand an array of 5 non-null Card objects.
     */
    public Hand(Card[] hand) {
        if (hand == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (hand.length != CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid array length");
        }
        for (Card c : hand) {
            if (c == null) {
                throw new IllegalArgumentException("Null element");
            }
        }
        this.hand = hand;
    }

    public Card getCard(int index) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        }
        return hand[index];
    }

    public void replace(int index, Card card) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (card == null) {
            throw new IllegalArgumentException("Null card");
        }
        hand[index] = card;
    }

    /**
     * Returns an array of counts for each card value.
     * Index i holds the number of cards with value i.
     */
    public int[] getCounts() {
        int[] counts = new int[Card.HIGHEST_VALUE + 1];
        for (Card c : hand) {
            counts[c.getValue()]++;
        }
        return counts;
    }

    /**
     * Returns a copy of the hand sorted by value (and suit as tiebreaker).
     */
    public Card[] getSortedHand() {
        Card[] sorted = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sorted);
        return sorted;
    }

    public boolean isFlush() {
        char suit = hand[0].getSuit();
        for (Card c : hand) {
            if (c.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    public boolean isStraight() {
        Card[] sorted = getSortedHand();
        for (int i = 0; i < sorted.length - 1; i++) {
            if (sorted[i].getValue() + 1 != sorted[i+1].getValue()) {
                // Optionally, handle Ace-low straight (A,2,3,4,5)
                return false;
            }
        }
        return true;
    }

    public boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    public boolean isRoyalFlush() {
        if (!isFlush()) return false;
        Card[] sorted = getSortedHand();
        return sorted[0].getValue() == 10 && sorted[1].getValue() == 11 &&
               sorted[2].getValue() == 12 && sorted[3].getValue() == 13 &&
               sorted[4].getValue() == 14;
    }

    public boolean hasFourOfAKind() {
        int[] counts = getCounts();
        for (int count : counts) {
            if (count == 4) return true;
        }
        return false;
    }

    public boolean hasThreeOfAKind() {
        int[] counts = getCounts();
        for (int count : counts) {
            if (count == 3) return true;
        }
        return false;
    }

    public boolean hasTwoPairs() {
        int[] counts = getCounts();
        int pairs = 0;
        for (int count : counts) {
            if (count == 2) pairs++;
        }
        return pairs == 2;
    }

    public boolean hasOnePair() {
        int[] counts = getCounts();
        int pairs = 0;
        for (int count : counts) {
            if (count == 2) pairs++;
        }
        return pairs == 1;
    }

    public boolean isFullHouse() {
        int[] counts = getCounts();
        boolean three = false, two = false;
        for (int count : counts) {
            if (count == 3) three = true;
            if (count == 2) two = true;
        }
        return three && two;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < hand.length; i++) {
            sb.append(hand[i].toString());
            if (i < hand.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
