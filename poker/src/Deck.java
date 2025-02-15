import java.util.Random;
import java.util.Arrays;

public class Deck {
    public static final int CARDS_IN_DECK = 52;

    private Card[] cards;
    private int next;
    private int seed;

    /**
     * Constructs a standard 52-card deck (unique cards in order).
     * The deck is not shuffled yet.
     *
     * @param seed if -1, a random game is played; otherwise the deck will be shuffled using this seed.
     */
    public Deck(int seed) {
        cards = new Card[CARDS_IN_DECK];
        next = 0;
        this.seed = seed;
        // Create a standard deck in order: for each suit (clubs, diamonds, hearts, spades)
        // and for each value 2 to 14.
        int index = 0;
        for (char suit : new char[]{Card.CLUBS, Card.DIAMONDS, Card.HEARTS, Card.SPADES}) {
            for (int value = Card.LOWEST_VALUE; value <= Card.HIGHEST_VALUE; value++) {
                cards[index++] = new Card(value, suit);
            }
        }
    }

    /**
     * Shuffles the deck using the Fisher-Yates algorithm.
     * If a seed was provided (seed != -1), that seed is used so the shuffle is repeatable.
     */
    public void shuffle() {
        Random rand;
        if (seed == VideoPoker.RANDOM_GAME) {
            rand = new Random();
        } else {
            rand = new Random(seed);
        }
        for (int i = cards.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        next = 0; // reset the deck pointer after shuffling
    }

    /**
     * Returns the next card in the deck.
     * @throws IllegalStateException if there are no more cards.
     */
    public Card nextCard() {
        if (next >= CARDS_IN_DECK) {
            throw new IllegalStateException("No more cards");
        }
        return cards[next++];
    }

    /**
     * Deals one card from the deck.
     */
    public Card dealOneCard() {
        return nextCard();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deck) {
            Deck d = (Deck) o;
            return Arrays.equals(cards, d.cards) && next == d.next && seed == d.seed;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.length; i++) {
            sb.append("card ").append(i).append(": ").append(cards[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
