import java.util.ArrayList;
import java.util.Collections;


public class deckliste {
    
     private ArrayList<Card> cards;

    public deckliste(){
         cards = new ArrayList<>();
    
         //initdeck

        Color[] colors ={Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

        for(int j =0; j< colors.length; j++){
            Color color = colors[j];
           
             cards.add(new NumberCard(color, 0));

            for(int n =1; n<=9; n++){
                cards.add(new NumberCard(color, n));
                cards.add(new NumberCard(color, n));
            }

            cards.add(new SkipCard(color));
            cards.add(new SkipCard(color));

            cards.add(new ReverseCard(color));
            cards.add(new ReverseCard(color));

            cards.add(new DrawTwoCard(color));
            cards.add(new DrawTwoCard(color));
            
    }

    for(int j=0; j<4; j++){
        cards.add(new WildCard());
        cards.add(new WildDrawFourCard());
    }


    shuffle();
}

    public Card draw(){    
        if(cards.isEmpty()){
            return null;
        }
        
        return cards.remove(0); 
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public void addCard(Card c){
         cards.add(0, c);
    }

    public int size() {
        return cards.size();
    }

    public void shuffle(){
         Collections.shuffle(cards);
    }
    
    
}
