class SkipCard extends Card implements CardEffect{

    private String value = "SkipCard";
 public SkipCard(Color color){
    super(color);
 }

 public String getValue(){
    return value;
 }
@Override
    public void cardEffect(Game game) {
        game.skipTurn();
    }

 
@Override
    public boolean canBePlayedOn(Card topCard) {
        if (this.getColor() == topCard.getColor()) {
            return true;
        }
        if(topCard instanceof SkipCard){
            return true;
        }
        return false;
    }

    public String toString() {
        return "[Skip Card " + getColor() + "]";
    }
}

