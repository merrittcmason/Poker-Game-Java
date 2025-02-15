import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VideoPokerGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 315;
    public static final int X = 100;
    public static final int Y = 100;
    public static final int FONT_SIZE = 15;
    public static final int TEXT_WIDTH = 10;

    private JLabel lblPoints;
    private JTextField txtScore;
    private JLabel[] cards;
    private ImageIcon[] icons;
    private JButton[] btnCards;
    private JButton btnScoreHand;
    private JButton btnNewGame;
    private JButton btnQuit;
    private VideoPoker pm;

    /**
     * Constructs the GUI and initializes components.
     * @param seed if -1, a random game is played; otherwise, the game is repeatable.
     */
    public VideoPokerGUI(int seed) {
        super("Video Poker");

        // Debug: print working directory to help verify resource paths.
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        // Test-load an image (make sure your images are in the /cards folder in the classpath).
        ImageIcon testIcon = new ImageIcon(getClass().getResource("/cards/h10.gif"));
        System.out.println("Test image width: " + testIcon.getIconWidth());

        // Initialize the game model.
        pm = new VideoPoker(seed);

        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        Container c = getContentPane();

        // Score panel.
        JPanel pnlScore = new JPanel();
        pnlScore.setLayout(new GridLayout(1, 2));
        lblPoints = new JLabel("Points: " + pm.getPoints());
        lblPoints.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        txtScore = new JTextField("", TEXT_WIDTH);
        txtScore.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        txtScore.setHorizontalAlignment(JTextField.CENTER);
        txtScore.setEditable(false);
        txtScore.setBackground(Color.WHITE);
        txtScore.setText("Press New Game to Start!");
        pnlScore.add(lblPoints);
        pnlScore.add(txtScore);

        // Cards panel.
        JPanel pnlCards = new JPanel();
        pnlCards.setLayout(new GridLayout(2, VideoPoker.CARDS_IN_HAND));
        cards = new JLabel[VideoPoker.CARDS_IN_HAND];
        icons = new ImageIcon[VideoPoker.CARDS_IN_HAND];
        btnCards = new JButton[VideoPoker.CARDS_IN_HAND];

        // Initialize card labels with dummy images.
        for (int i = 0; i < VideoPoker.CARDS_IN_HAND; i++) {
            icons[i] = new ImageIcon(getClass().getResource("/cards/h" + (i + 10) + ".gif"));
            cards[i] = new JLabel();
            cards[i].setIcon(icons[i]);
            JPanel panel = new JPanel();
            panel.add(cards[i]);
            pnlCards.add(panel);
        }

        // Initialize Replace buttons.
        for (int i = 0; i < VideoPoker.CARDS_IN_HAND; i++) {
            btnCards[i] = new JButton("Replace");
            btnCards[i].setBackground(Color.RED);
            btnCards[i].addActionListener(this);
            btnCards[i].setEnabled(false);
            pnlCards.add(btnCards[i]);
        }

        // Buttons panel.
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(1, 3));
        btnScoreHand = new JButton("Score Hand");
        btnScoreHand.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        btnScoreHand.addActionListener(this);
        btnScoreHand.setEnabled(false);
        btnNewGame = new JButton("New Game");
        btnNewGame.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        btnNewGame.addActionListener(this);
        btnQuit = new JButton("Quit");
        btnQuit.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        btnQuit.addActionListener(this);
        pnlButtons.add(btnScoreHand);
        pnlButtons.add(btnNewGame);
        pnlButtons.add(btnQuit);

        // Add panels to the frame.
        c.add(pnlScore, BorderLayout.NORTH);
        c.add(pnlCards, BorderLayout.CENTER);
        c.add(pnlButtons, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewGame) {
            txtScore.setText("");
            pm.newGame();
            btnScoreHand.setEnabled(true);

            for (int i = 0; i < VideoPoker.CARDS_IN_HAND; i++) {
                icons[i] = new ImageIcon(getClass().getResource(pm.getCardFileName(i)));
                cards[i].setIcon(icons[i]);
                btnCards[i].setEnabled(true);
            }
            lblPoints.setText("Points: " + pm.getPoints());
        } else if (e.getSource() == btnScoreHand) {
            // Disable replace buttons.
            for (int i = 0; i < VideoPoker.CARDS_IN_HAND; i++) {
                btnCards[i].setEnabled(false);
            }
            btnScoreHand.setEnabled(false);
            // Update card images (in case any replacements were made).
            for (int i = 0; i < VideoPoker.CARDS_IN_HAND; i++) {
                icons[i] = new ImageIcon(getClass().getResource(pm.getCardFileName(i)));
                cards[i].setIcon(icons[i]);
            }
            txtScore.setText(pm.scoreHand());
            lblPoints.setText("Points: " + pm.getPoints());
        } else if (e.getSource() == btnQuit) {
            System.exit(0);
        } else {
            // Handle Replace button clicks.
            for (int i = 0; i < VideoPoker.CARDS_IN_HAND; i++) {
                if (e.getSource() == btnCards[i]) {
                    pm.replaceCard(i);
                    icons[i] = new ImageIcon(getClass().getResource(pm.getCardFileName(i)));
                    cards[i].setIcon(icons[i]);
                    btnCards[i].setEnabled(false);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                new VideoPokerGUI(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                System.out.println("Usage: java VideoPokerGUI <seed>");
            }
        } else if (args.length == 0) {
            new VideoPokerGUI(VideoPoker.RANDOM_GAME);
        } else {
            System.out.println("Usage: java VideoPokerGUI <seed>");
        }
    }
}
