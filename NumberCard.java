enum Value {
    ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE
}

public class NumberCard extends Card {
    private Value value;
    public NumberCard(Color color, int number) {
        super(color);
        this.value = toNumberValue(number);
    }

    public Value getValue() {
        return value;
    }

    private static Value toNumberValue(int number) {
        switch (number) {
            case 0: return Value.ZERO;
            case 1: return Value.ONE;
            case 2: return Value.TWO;
            case 3: return Value.THREE;
            case 4: return Value.FOUR;
            case 5: return Value.FIVE;
            case 6: return Value.SIX;
            case 7: return Value.SEVEN;
            case 8: return Value.EIGHT;
            case 9: return Value.NINE;
            default:
                System.out.println("Numéro de carte invalide : " + number);
                return null;
        }
    }

public String toString() {
        return "[" + value + " " + getColor() + "]";
    }

@Override
    public boolean canBePlayedOn(Card topCard) {
        if (this.getColor() == topCard.getColor()) {
            return true;
        }
        if(topCard instanceof NumberCard){
        NumberCard g = (NumberCard) topCard;
        if (this.getValue() == g.getValue()) {
            return true;
        }
        }
        return false;
    }
}
