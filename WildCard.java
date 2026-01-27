import java.util.Scanner;
import java.util.Random;
public class WildCard extends Card implements CardEffect {

    private String value = "WildCard";
    public WildCard() {
        super(Color.WILD);
    }

    public String getValue(){
        return value;
    }
@Override
    public void cardEffect(Game game) {
        Scanner sc = new Scanner(System.in);
        Color newColor;
        Player currentPlayer = game.getCurrentPlayer();
        boolean isHumanTurn = !game.isWithBots() || currentPlayer.getName().equals("Joueur 1");
        if (isHumanTurn) {
        System.out.print("Veuillez choisir une couleur (RED, YELLOW, GREEN, BLUE) : ");
        while (true) {
            String input = sc.next().toUpperCase();
            if (input.equals("RED")) {
               setColor(Color.RED);
                break;
            } else if (input.equals("YELLOW")) {
                setColor(Color.YELLOW);
                break;
            } else if (input.equals("GREEN")) {
                setColor(Color.GREEN);
                break;
            } else if (input.equals("BLUE")) {
                setColor(Color.BLUE);
                break;
            } else {
                System.out.println("Couleur invalide. Essayez encore (RED, YELLOW, GREEN, BLUE) :");
            }
        }
       
    }else{
         Color[] cols = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
             Random random = new Random();
             newColor = cols[random.nextInt(4)];
             System.out.println(currentPlayer.getName() + " a choisi la couleur " + newColor);
             setColor(newColor);
    }
        

    }
    
public void reinitialiser() {
        this.setColor(Color.WILD);
    }
@Override
    public boolean canBePlayedOn(Card topCard) {
        return true;
  
    }
   

      public String toString() {
        if (getColor() == Color.WILD) 
            return "[Wild Card]";

        return "[Wild Card " + getColor() + "]";
        
    }

}

