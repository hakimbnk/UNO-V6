public class ReverseCard extends Card implements CardEffect {
     private String value = "ReverseCard";
    public ReverseCard(Color color) {
        super(color);
    }

    @Override
    public void cardEffect(Game game) {
        game.reverseDirection();
    }
    public String getValue(){
        return value;
    }
    @Override
    public boolean canBePlayedOn(Card topCard) {
       return this.getColor() == topCard.getColor() 
           || topCard instanceof ReverseCard;

    }

     @Override
    public String toString() {
        return "[Reverse Card " + getColor() + "]";
    }
}
