# Poker Game (Java)

Java Video Poker is a graphical simulation of a classic video poker machine. The game deals you a five-card hand from a shuffled deck, lets you choose which cards to replace, and then scores your hand according to standard poker rankings. Points are awarded based on the type of hand you get, such as a Royal Flush, Straight, or One Pair.

---
<img width="592" alt="Screenshot 2025-02-18 at 4 31 12 PM" src="https://github.com/user-attachments/assets/463deafa-e772-4554-917b-9153f136cd71" />

---

## Features

- **Shuffled 52-Card Deck:** A complete deck is generated and shuffled each game.
- **Interactive GUI:** Built using Java Swing, the GUI displays card images, buttons to replace cards, and shows your current score.
- **Poker Hand Scoring:** Your hand is scored using standard poker rankings:
  - Royal Flush
  - Straight Flush
  - Four of a Kind
  - Full House
  - Flush
  - Straight
  - Three of a Kind
  - Two Pairs
  - One Pair
  - No Pair
- **Point System:** Start with a fixed number of points; each new game costs a few points while successful hands award bonus points.

---

## How It Works

1. **Starting a Game:**
   - When the game launches, a new hand is automatically dealt (or you can start a new game via the “New Game” button).
   - The GUI displays your five cards along with your current point total.

2. **Replacing Cards:**
   - You can choose to replace any card by clicking on its associated “Replace” button.
   - The selected card is replaced with the next card from the deck.

3. **Scoring the Hand:**
   - Once you are satisfied with your hand, click the “Score Hand” button.
   - The program evaluates your hand based on poker rules, awards points accordingly, and displays the result (e.g., “Straight Flush” or “One Pair”).

4. **Continuing Play:**
   - After scoring, you can start a new game (which deducts points for the new game) and try to improve your hand.
   - The game continues until you decide to quit or run out of points.

---

https://github.com/user-attachments/assets/2b2d6e77-8534-4974-a121-2cc6e55ea9a8

---
## Project Structure

- **Card.java:**  
  Represents an individual playing card. It stores the card’s suit and numeric value and implements methods for comparison and string conversion.

- **Deck.java:**  
  Manages a standard 52-card deck, including proper shuffling (using the Fisher-Yates algorithm) and dealing of cards.

- **Hand.java:**  
  Models a five-card hand with functions to sort the hand and evaluate different poker hand types.

- **VideoPoker.java:**  
  Contains the game logic, including methods to start a new game, replace cards, and score a hand based on standard poker rules.

- **VideoPokerGUI.java:**  
  Provides a graphical user interface using Java Swing. It displays cards (using images), buttons for game actions, and text fields for points and hand descriptions.

---

## How to Compile and Run

### Requirements
Java Development Kit (JDK) 8 or later

```java -version```

### Compilation
Navigate to your project directory and compile the Java files.

```cd /path/to/your/project```

```javac *.java```

### Running the Program
After compiling, launch the game by running the main GUI class.

```java VideoPokerGUI```

Ensure that any required card image resources are correctly placed in the classpath (for example, in a folder named `cards` inside your project directory).

---

## Usage Tips

- **New Game:**  
  Click the “New Game” button to deal a fresh hand and deduct a few points.

- **Replace Cards:**  
  Use the “Replace” buttons beneath each card to change unwanted cards.

- **Score Hand:**  
  Click “Score Hand” to evaluate your current hand and update your point total.

- **Quit:**  
  Use the “Quit” button to exit the game.

---

## Troubleshooting

- **Image Resources:**  
  Make sure your card images (e.g., `h10.gif`, `s14.gif`) are placed in the correct folder and referenced properly in the code.

- **Fixed vs. Random Shuffling:**  
  If you’re using a seed for testing, the same hands might appear each time. For a truly random game experience, use a random seed.



*Licensed on February 18th, 2025 by Merritt Mason.*
