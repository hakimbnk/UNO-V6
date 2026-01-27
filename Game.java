import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private deckliste deck;
    private int currentPlayerIndex;
    private boolean direction; // true for clockwise, false for counter-clockwise
    private ArrayList<Card> discardPile;
    private boolean withBots;

    public Game(ArrayList<String> playerNames,boolean withBots) {
        this.withBots = withBots;
        
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        deck = new deckliste();
        discardPile = new ArrayList<>();
        currentPlayerIndex = 0;
        direction = true; 

        
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.drawcard(deck);
            }
        }

        // Start discard pile
        Card firstCard = deck.draw();
       
        
        while (firstCard instanceof ReverseCard ||
           firstCard instanceof SkipCard ||
           firstCard instanceof DrawTwoCard ||
           firstCard instanceof WildCard ||
           firstCard instanceof WildDrawFourCard) {
        deck.addCard(firstCard); 
        firstCard = deck.draw(); 
        }

        discardPile.add(firstCard);
    }

    public void nextTurn() {
        if (direction) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }

    public boolean isWithBots() {
        return withBots;
    }

    public void skipTurn() {
        nextTurn();
    }

    public void reverseDirection() {
        direction = !direction;
        if (players.size() == 2) {
            skipTurn();
        }
    }

    public void drawCard(int amount, Player target) {
        // faut verifier si le deck est empty pour reshuffle
        for (int i = 0; i < amount; i++) {
            target.drawcard(deck);
            if (deck.isEmpty()) {
                deck.shuffle();
            }
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Player getNextPlayer() {
        int nextIndex;
        if (direction) {
            nextIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            nextIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
        return players.get(nextIndex);
    }

    public deckliste getDeck() {
        return deck;
    }

    public void reshufflePileIntoDeck() {
        if (!discardPile.isEmpty()) {

        // Save the top card (the one currently in play)
        Card topCard = getTopPile();
        discardPile.remove(topCard);

        // Add the rest of the discard pile to the deck
        for (Card card : discardPile) {
            deck.addCard(card);
        }

        // Clear the discard pile and restore the top card
        discardPile.clear();
        discardPile.add(topCard);

        // Shuffle the replenished deck
        deck.shuffle();
        }
    }

    public Card getTopPile() {
        if (discardPile.isEmpty()) {
            return null;
        }
        return discardPile.get(discardPile.size() - 1);
    }

    public Player checkWinner() {
        if (getCurrentPlayer().hasnocard()) {
            return getCurrentPlayer();
        }
        return null;
    }

    public void addToDiscardPile(Card card) {
        discardPile.add(card);
    }


}