public class VideoPoker {

    // Game configuration and scoring constants.
    public static final int RANDOM_GAME = -1;
    public static final int CARDS_IN_HAND = 5;
    public static final int STARTING_POINTS = 100;
    public static final int POINTS_FOR_NEW_GAME = 10;
    public static final int ROYAL_FLUSH = 100;
    public static final int STRAIGHT_FLUSH = 60;
    public static final int FOUR_OF_A_KIND = 50;
    public static final int FULL_HOUSE = 40;
    public static final int FLUSH = 30;
    public static final int STRAIGHT = 25;
    public static final int THREE_OF_A_KIND = 15;
    public static final int TWO_PAIRS = 10;
    public static final int ONE_PAIR = 7;

    private Deck deck;
    private Hand hand;
    private int points;

    /**
     * Constructs a new VideoPoker game.
     * The deck is created using the given seed, then shuffled,
     * and an initial 5-card hand is dealt.
     */
    public VideoPoker(int seed) {
        deck = new Deck(seed);
        deck.shuffle();
        Card[] initialHand = new Card[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            initialHand[i] = deck.dealOneCard();
        }
        hand = new Hand(initialHand);
        points = STARTING_POINTS;
    }

    public int getPoints() {
        return points;
    }

    /**
     * Returns the file name for the image representing the card at the given index.
     * Assumes images are in the "cards" folder and are named like "h5.gif".
     */
    public String getCardFileName(int index) {
        return "cards/" + hand.getCard(index).toString() + ".gif";
    }

    public Card getCard(int index) {
        return hand.getCard(index);
    }

    /**
     * Starts a new game by subtracting points, shuffling the deck, and dealing a new hand.
     */
    public void newGame() {
        if (points < POINTS_FOR_NEW_GAME) {
            System.out.println("Not enough points to play a new game!");
            return;
        }
        points -= POINTS_FOR_NEW_GAME;
        // Create a brand new deck using the RANDOM_GAME constant so that the deck shuffles randomly.
        deck = new Deck(RANDOM_GAME);
        deck.shuffle();
        Card[] newHand = new Card[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            newHand[i] = deck.dealOneCard();
        }
        hand = new Hand(newHand);
    }

    /**
     * Replaces the card at the specified index with the next card from the deck.
     */
    public void replaceCard(int index) {
        Card newCard = deck.dealOneCard();
        hand.replace(index, newCard);
    }

    /**
     * Scores the current hand according to poker rules, updates points, and returns a String describing the hand.
     */
    public String scoreHand() {
        if (hand.isRoyalFlush()) {
            points += ROYAL_FLUSH;
            return "Royal Flush";
        } else if (hand.isStraightFlush()) {
            points += STRAIGHT_FLUSH;
            return "Straight Flush";
        } else if (hand.hasFourOfAKind()) {
            points += FOUR_OF_A_KIND;
            return "Four of a Kind";
        } else if (hand.isFullHouse()) {
            points += FULL_HOUSE;
            return "Full House";
        } else if (hand.isFlush()) {
            points += FLUSH;
            return "Flush";
        } else if (hand.isStraight()) {
            points += STRAIGHT;
            return "Straight";
        } else if (hand.hasThreeOfAKind()) {
            points += THREE_OF_A_KIND;
            return "Three of a Kind";
        } else if (hand.hasTwoPairs()) {
            points += TWO_PAIRS;
            return "Two Pairs";
        } else if (hand.hasOnePair()) {
            points += ONE_PAIR;
            return "One Pair";
        } else {
            return "No Pair";
        }
    }
}
