
public class VideoPoker {

    // RANDOM_GAME - Indicates that a random game should be played, set to -1.
    // CARDS_IN_HAND - The number of cards in a hand, set to 5.
    // STARTING_POINTS - The number of points that the player has when the game
    // begins, set to 100.
    // POINTS_FOR_NEW_GAME - The number of points needed to play a new game, set to
    // 10.
    // ROYAL_FLUSH - The number of points awarded for a Royal Flush, set to 100.
    // STRAIGHT_FLUSH - The number of points awarded for a Straight Flush, set to
    // 60.
    // FOUR_OF_A_KIND - The number of points awarded for Four of a Kind, set to 50.
    // FULL_HOUSE - The number of points awarded for a Full House, set to 40.
    // FLUSH - The number of points awarded for a Flush, set to 30.
    // STRAIGHT - The number of points awarded for a Straight, set to 25.
    // THREE_OF_A_KIND - The number of points awarded for Three of a Kind, set to
    // 15.
    // TWO_PAIRS - The number of points awarded for Two Pairs, set to 10.
    // ONE_PAIR - The number of points awarded for One Pair, set to 7.

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

    // deck - This is the Deck of cards that will be used to play the game.
    // hand - This is the Hand of Cards in the game.
    // points - This is the current number of points.

    private Deck deck;
    private Hand hand;
    private int points;

    // Creates a deck using the seed. Sets points to STARTING_POINTS

    public VideoPoker(int seed) {
        deck = new Deck(seed);
        hand = new Hand(null);
        points = STARTING_POINTS;
    }

    // Returns the number of points

    public int getPoints() {
        return points;
    }

    // Returns the name of the image file for the Card at the given index in the
    // hand. The name should include the cards directory followed by the card
    // followed by the .gif extension, for example, the String “cards/h5.gif” is the
    // name of the card image file for the five of Hearts. Hint: Use the Card
    // toString() method.

    public String getCardFileName(int index) {
        return "cards/" + hand.getCard(index).toString() + ".gif";
    }

    // Returns the card at the given index in the hand.

    public Card getCard(int index) {
        return hand.getCard(index);
    }

    // Subtracts POINTS_FOR_NEW_GAME from points and shuffles the deck. A new array
    // of CARDS_IN_HAND Cards is created and filled by calling the deck nextCard()
    // method CARDS_IN_HAND times. The Card array is used to create a new Hand.

    public void newGame() {
        points -= POINTS_FOR_NEW_GAME;
        deck.shuffle();
        Card[] cards = new Card[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            cards[i] = deck.nextCard();
        }
        hand = new Hand(cards);
    }

    // Gets the next Card from the deck and requests the hand to replace the card at
    // the given index with the new Card.

    public void replaceCard(int index) {
        Card card = deck.nextCard();
        hand.replace(index, card);
    }

    // Returns the String given below and adds the correct number of points to the
    // total points based on the type of hand.
    // If the hand is a royal flush, returns “Royal Flush”.
    // Else if the hand is a straight flush, returns “Straight Flush”.
    // Else if the hand has four of a kind, returns “Four of a Kind”.
    // Else if the hand is a full house, returns “Full House”.
    // Else if the hand is a flush, returns “Flush”.
    // Else if the hand is a straight, returns “Straight”.
    // Else if the hand has three of a kind, returns “Three of a Kind”.
    // Else if the hand has two pairs, returns “Two Pairs”.
    // Else if the hand has one pair, returns “One Pair”.
    // Else returns “No Pair”.

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
