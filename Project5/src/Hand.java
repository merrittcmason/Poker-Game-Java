import java.util.*;

/**
 * Represents hand of cards
 *
 * @author Dan Longo
 * @author Suzanne Balik
 * @author Merritt Mason
 */
public class Hand {

    // Declare and initialize the following public static class constant - add more
    // constants, if needed:
    // CARDS_IN_HAND his represents the number of cards a Hand holds in their hand
    // at a time. Set this equal to 5.

    public static final int CARDS_IN_HAND = 5;

    /** Contains cards in hand */
    private Card[] hand;

    /**
     * Counts the number of cards with each value in the hand
     *
     * @return tally array containing number of cards of each value from 2 to 14.
     */
    public int[] getCounts() {
        int[] counts = new int[Card.HIGHEST_VALUE + 1];
        for (int i = 0; i < hand.length; i++) {
            counts[hand[i].getValue()]++;
        }
        return counts;
    }

    /**
     * Creates a copy of the hand sorted first by value, then by suit
     *
     * @return copy of the hand sorted first by value, then by suit
     */
    public Card[] getSortedHand() {
        Card[] sortedHand = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sortedHand);
        return sortedHand;
    }

    // This is the constructor of the Hand class. It is passed an array of Cards to
    // be used as the hand.
    // Throw an IllegalArgumentException with the message “Null array” if the array
    // is null.
    // Throw an IllegalArgumentException with the message “Invalid array length” if
    // the hand array being passed to the constructor does not have a length equal
    // to CARDS_IN_HAND.
    // Throw an IllegalArgumentException with the message “Null element” if one or
    // more of the hand array elements is null.

    public Hand(Card[] hand) {
        if (hand == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (hand.length != CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid array length");
        }
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                throw new IllegalArgumentException("Null element");
            }
        }
        this.hand = hand;
    }

    // This method simply returns the card from the hand array at the index
    // specified by the parameter.
    // Throw an IllegalArgumentException with the message “Invalid index” if the
    // index is less than 0 or
    // greater than or equal to CARDS_IN_HAND.

    public Card getCard(int index) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        }
        return hand[index];
    }

    // This method replaces the card at the given index in the hand array with the
    // card passed to the method.
    // Throw an IllegalArgumentException with the message “Invalid index”, if the
    // index is less than 0 or greater than or equal to CARDS_IN_HAND.
    // Throw an IllegalArgumentException with the message “Null card”, if the card
    // is null.

    public void replace(int index, Card card) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (card == null) {
            throw new IllegalArgumentException("Null card");
        }
        hand[index] = card;
    }

    // This method creates a String representation of the hand, for example, [c2,
    // h12, d8, s2, c13] HINT: Use the Card toString() method for this!

    public String toString() {
        String handString = "[";
        for (int i = 0; i < hand.length; i++) {
            handString += hand[i].toString();
            if (i != hand.length - 1) {
                handString += ", ";
            }
        }
        handString += "]";
        return handString;
    }

    // This method returns whether this Hand and o are equal. If o is not a Hand,
    // the method should return false. Hands are considered equal if
    // they contain all of the same cards. HINT: Sort hands before testing equality.

    public boolean equals(Object o) {
        if (o instanceof Hand) {
            Hand otherHand = (Hand) o;
            Card[] sortedHand = getSortedHand();
            Card[] otherSortedHand = otherHand.getSortedHand();
            for (int i = 0; i < sortedHand.length; i++) {
                if (!sortedHand[i].equals(otherSortedHand[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Returns true if all the cards in the hand have the same suit; false
    // otherwise.

    public boolean isFlush() {
        Card[] sortedHand = getSortedHand();
        for (int i = 0; i < sortedHand.length - 1; i++) {
            if (sortedHand[i].getSuit() != sortedHand[i + 1].getSuit()) {
                return false;
            }
        }
        return true;
    }

    // Returns true if the values of the cards in the hand are in sequence, for
    // example, 6, 7, 8, 9 10; false otherwise.

    public boolean isStraight() {
        Card[] sortedHand = getSortedHand();
        for (int i = 0; i < sortedHand.length - 1; i++) {
            if (sortedHand[i].getValue() + 1 != sortedHand[i + 1].getValue()) {
                return false;
            }
        }
        return true;
    }

    // Returns true if the cards in the hand have the same suit and are in sequence;
    // false otherwise.

    public boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    // Returns true if the cards in the hand have the same suit and the values 10,
    // 11, 12, 13, 14; false otherwise.

    public boolean isRoyalFlush() {
        Card[] sortedHand = getSortedHand();
        return isFlush() && sortedHand[0].getValue() == 10 && sortedHand[1].getValue() == 11
                && sortedHand[2].getValue() == 12 && sortedHand[3].getValue() == 13 && sortedHand[4].getValue() == 14;
    }

    // Returns true if the hand has four cards with the same value; false otherwise.

    public boolean hasFourOfAKind() {
        int[] counts = getCounts();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 4) {
                return true;
            }
        }
        return false;
    }

    // Returns true if the hand has exactly three cards with the same value; false
    // otherwise.

    public boolean hasThreeOfAKind() {
        int[] counts = getCounts();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 3) {
                return true;
            }
        }
        return false;
    }

    // Returns true if the hand has exactly two pairs (two cards with the same
    // value, two cards with the same, but a different value from the first pair,
    // and a card whose value is not the same as that of either pair); false
    // otherwise.

    public boolean hasTwoPairs() {
        int[] counts = getCounts();
        int pairCount = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    // Returns true if the hand contains exactly one pair (two cards with the same
    // value); false otherwise. For example, the hands [c2, d2, c5, d5, h5] and [c2,
    // d2, c5, d4, h10] would be considered to contain exactly one pair; the hand
    // [c2, d2, c5, d5, h10] would not.

    public boolean hasOnePair() {
        int[] counts = getCounts();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 2) {
                return true;
            }
        }
        return false;
    }

    // Returns true if the hand contains three cards with the same value and two
    // other cards with the same value, but different from that of the three cards,
    // for example, [c2, d2, c5, d5, h5]; false otherwise.

    public boolean isFullHouse() {
        return hasThreeOfAKind() && hasOnePair();
    }

}
