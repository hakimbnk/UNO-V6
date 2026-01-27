public class DrawTwoCard extends Card implements CardEffect {
    private String value = "DrawTwoCard";
    public DrawTwoCard(Color color) {
        super(color);
    }

    @Override
    public void cardEffect(Game game) {
        game.drawCard(2, game.getNextPlayer());
        game.skipTurn();
    }
    public String getValue(){
        return value;
    }

    @Override
    public boolean canBePlayedOn(Card topCard) {
        if (this.getColor() == topCard.getColor()) {
            return true;
        }
        if(topCard instanceof DrawTwoCard){
        DrawTwoCard g = (DrawTwoCard) topCard;
        if (this.getValue() == g.getValue()) {
            return true;
        }
        }
        return false;
    }   
    public String toString() {
        return "[Draw Two Card " + getColor() + "]";
    }
}
