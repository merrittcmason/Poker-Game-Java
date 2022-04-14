import static org.junit.jupiter.api.Assertions.*;
import java.beans.Transient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Card class
 *
 * @author Suzanne Balik
 * @author
 */
public class CardTest {

    /** card two of hearts for testing */
    private Card twoOfHearts;

    // TODO: Add another Card field

    /**
     * Create cards for testing
     */
    @BeforeEach
    public void setUp() {
        twoOfHearts = new Card(2, 'h');
        // TODO: Construct added Card
    }

    /**
     * Tests that constants are correct
     */
    @Test
    public void testConstants() {
        // The following tests test that constants are defined as specified
        assertEquals('c', Card.CLUBS, "CLUBS");
        assertEquals('d', Card.DIAMONDS, "DIAMONDS");
        assertEquals('s', Card.SPADES, "SPADES");
        assertEquals('h', Card.HEARTS, "HEARTS");
        assertEquals(2, Card.LOWEST_VALUE, "LOWEST_VALUE");
        assertEquals(14, Card.HIGHEST_VALUE, "HIGHEST_VALUE");
    }

    /**
     * Tests getValue with two of hearts
     */
    @Test
    public void testGetValueTwoOfHearts() {
        assertEquals(2, twoOfHearts.getValue(), "twoOfHearts value");
    }

    // TODO: Test getValue for added Card. Test should be in its own method.

    @Test
    public void testGetValue() {

    }


    /**
     * Tests getSuit with two of hearts
     */
    @Test
    public void testGetSuitTwoOfHearts() {
        assertEquals('h', twoOfHearts.getSuit(), "twoOfHearts suit");
    }

    // TODO: Test getSuit for added Card. Test should be in its own method.

    /**
     * Tests toString with two of hearts
     */
    @Test
    public void testToStringTwoOfHearts() {
        assertEquals("h2", twoOfHearts.toString(), "twoOfHearts toString");
    }

    // TODO: Test toString for added Card. Test should be in its own method.

    /**
     * Tests equals with two of hearts
     */
    @Test
    public void testEqualsTwoOfHearts() {
        assertTrue(twoOfHearts.equals(twoOfHearts), "twoOfHearts equals with same instance");
        assertTrue(twoOfHearts.equals(new Card(2, 'h')),
                "twoOfHearts equals with different instances");
        assertFalse(twoOfHearts.equals(new Card(4, 'h')), "twoOfHearts with different value");
        assertFalse(twoOfHearts.equals(new Card(2, 's')), "twoOfHearts with different suit");
        assertFalse(twoOfHearts.equals(new Card(5, 'c')),
                "twoOfHearts with different value and suit");
        assertFalse(twoOfHearts.equals(null), "twoOfHearts compared to null object");
        assertFalse(twoOfHearts.equals("twoOfHearts"), "twoOfHearts compared to String");
    }

    // TODO: Test equals for added Card. Test should be in its own method.

    /**
     * Tests compareTo() method - you do not need to add any additional tests
     */
    @Test
    public void testCompareTo() {
        Card fiveOfDiamonds = new Card(5, 'd');
        Card fiveOfHearts = new Card(5, 'h');
        Card twoOfSpades = new Card(2, 's');
        Card sevenOfClubs = new Card(7, 'c');
        assertEquals(-5, twoOfSpades.compareTo(sevenOfClubs), "compare Spades 2 to Clubs 7");
        assertEquals(3, fiveOfHearts.compareTo(twoOfSpades), "compare Hearts 5 to Spades 2");
        assertEquals(-4, fiveOfDiamonds.compareTo(fiveOfHearts), "compare Diamonds 5 to Hearts 5");
    }

    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        // Testing constructor with low invalid value
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Card(1, 'h'), "Constructor value 1");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 1 message");

        // Testing constructor with high invalid value
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Card(15, 's'), "Constructor value 15");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 15 message");

        // Testing constructor with invalid value and invalid suit
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Card(0, 'w'), "Constructor value 0 suit w");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 0 suit w message");

        // Testing constructor with invalid lowercase suit
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Card(5, 'x'), "Constructor suit x");
        assertEquals("Invalid suit", exception.getMessage(),
                "Testing suit x message");

        // Testing constructor with invalid uppercase suit
        exception = assertThrows(IllegalArgumentException.class,
                () -> new Card(5, 'D'), "Constructor suit D");
        assertEquals("Invalid suit", exception.getMessage(),
                "Testing suit D message");
    }
}
