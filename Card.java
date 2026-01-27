enum Color {
    RED, YELLOW, GREEN, BLUE, WILD
}

public abstract class Card {
    private Color color;

    public Card(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public String toString() {
        return "[Card " + getColor() + "]";
    }

    public abstract boolean canBePlayedOn(Card topCard);

}

