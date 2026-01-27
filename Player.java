import java.util.ArrayList;

public class Player {
    //attribues

    private String name;
    private ArrayList<Card> hand;

    //constructeur

    public Player(String name)
    {
        this.name=name;
        this.hand=new ArrayList<>();
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    //methods

    public void drawcard(deckliste deck)
    {
        Card C=deck.draw();
        if (C!= null) {
            hand.add(C);
        }

    }
    public void showhand()
    {
        System.out.println(name + "'s hand:");
        for (int i=0;i<hand.size();i++)
        {
        System.out.println( (i) + " " + hand.get(i));
        }
    }

    public Card playcard(int index) // index :numero de la carte
    {
    if(index>=0 && index<hand.size())
            return hand.remove(index);  
        return null;
    
    }

    public boolean hasonecard()
    {
        if (hand.size()==1)
            return true;
        return false;
         
    }

    public boolean hasnocard()
    {
        if (hand.size()==0) 
            return true;
        return false ;
        
    }

}