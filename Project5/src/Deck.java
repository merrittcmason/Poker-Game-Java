import java.util.Arrays;
import java.util.Random;

public class Deck {

    // Declare and initialize the following public static class constants - add more
    // constants, if needed:

    // CARDS_IN_DECK - An integer value to represent the number of cards in the
    // deck. Set this equal to 52.

    public static final int CARDS_IN_DECK = 52;

    // Declare the following private instance field:

    // cards - An array of Card objects that will be used to manage the
    // CARDS_IN_DECK cards in the deck. Each element in the array is a single Card
    // object.
    // next - An integer that holds the index of the next card to be dealt from the
    // array of Cards. As a side note, this variable will start at 0 and increase by
    // 1 each time a card is dealt (see methods below).
    // seed - An integer variable that maintains the random seed provided for
    // testing.

    private Card[] cards;
    private int next;
    private int seed;

    // The following is the list of methods and constructor you must complete.

    // This is the constructor of the Deck class. You must first initialize your
    // array of Card objects to hold 52 cards using your class constant
    // CARDS_IN_DECK. After your array is initialized, you must go through the array
    // one element at a time and actually create a Card object. All cards must be
    // created (values 2-14 for each of the four suits), and there must not be any
    // duplicates. The cards array must be created in this order (Clubs 2-14,
    // Diamonds 2-14, Hearts 2-14, Spades 2-14). The seed parameter should be stored
    // in the instance field for the seed. HINT: Use Card.CLUBS, Card.LOWEST_VALUE,
    // etc., in this constructor.
    public Deck(int seed) {
        cards = new Card[CARDS_IN_DECK];
        next = 0;
        this.seed = seed;
        Random rand = new Random(seed);
        for (int i = 0; i < CARDS_IN_DECK; i++) {
            int value = rand.nextInt(13) + 2;
            char suit = (char) (rand.nextInt(4) + 'a');
            cards[i] = new Card(value, suit);
        }
    }
    // public void shuffle(): This method handles resetting the deck for a new hand
    // to be played. This method “shuffles” the deck by using the Random class to
    // generate random numbers. Use the following simple shuffle algorithm that
    // guarantees that all permutations will be equally likely:
    // for i = n - 1 down to 1 do
    // swap a[i] with a random element among a[0],...,a[i]
    // (so if the chosen element is a[i], it stays put)

    // For example, if i is 29, you would generate a random number between 0 and 29,
    // perhaps, 19. If the 2 of Clubs is in element 29 of the array and the 3 of
    // Hearts is in element 19 of the array, after these two cards are swapped
    // element 29 will hold the 3 of Hearts, and element 19 will hold the 2 of
    // Clubs.
    // If the random seed is not -1, the Random object you create should use the
    // provided random seed. This will ensure that the deck will be shuffled the
    // same way every time you run the program with the same random seed. For
    // example,
    // If the random seed is -1, create a Random object with no seed, which will
    // create a different game every time you play. For example,
    // Finally, the index used for selecting the next card to be dealt from the deck
    // must be reset to 0.

    public void shuffle() {
        Random rand = new Random();
        if (seed != -1) {
            rand = new Random(seed);
        }
        for (int i = cards.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        next = 0;
    }

    // This method returns the next card in the deck based on the next instance
    // field, which knows the position within the array where the next card to be
    // dealt is located. Before returning the card, make sure you update this value
    // so that the next time the method is called, the next card in the deck will be
    // returned (increase it by 1). Throw an IllegalStateException with the message
    // “No more cards”, if next is greater than or equal to CARDS_IN_DECK when the
    // method is called.
    public Card nextCard() {
        if (next >= CARDS_IN_DECK) {
            throw new IllegalStateException("No more cards");
        }
        return cards[next++];
    }

    // This method returns whether this Deck and o are equal. If o is not a Deck,
    // the method should return false. Decks are considered equal if they have the
    // same cards array (array of cards in the same order), the same integer value
    // for the index of the next card, and the same seed.
    public boolean equals(Object o) {
        if (!(o instanceof Deck)) {
            return false;
        }
        Deck other = (Deck) o;
        return Arrays.equals(cards, other.cards) && next == other.next && seed == other.seed;
    }

    // This method returns a String representation of the Deck which will come in
    // handy for Unit Testing. HINT: call the toString() method of the Card class
    // for each Card in the array of Card objects. For example,
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.length; i++) {
            sb.append(cards[i].toString());
            if (i < cards.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
