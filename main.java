import java.util.ArrayList;
import java.util.Scanner;


 class Main {
    public static void main(String[] args) {
        ArrayList<String> playerNames = new ArrayList<>();
        
        int n;
        Scanner scanner = new Scanner(System.in);
        
        do{
            System.out.print("Entrer le nombre de joueurs :");
            n = scanner.nextInt();
        }while(n < 1 || n > 10);
        scanner.nextLine();
        System.out.print("Voulez-vous jouer avec des bots ? (oui/non) : ");
        String rep=scanner.nextLine().toLowerCase();
        if(rep.equals("oui")){

        playerNames.add("Joueur 1");
            for (int i = 1; i < n; i++) {
                playerNames.add("Bot " +(i + 1)); 
            }
        }else{
            for (int i = 0; i < n; i++) {
                System.out.print("Entrer le nom du joueur " + (i + 1) + " : ");
                String name = scanner.nextLine();
                playerNames.add(name);
            }
        }

       Game game = new Game(playerNames,rep.equals("oui"));
        System.out.println("Début de la partie UNO :");

        boolean gameRunning = true;
        while (gameRunning) {
            Player currentPlayer = game.getCurrentPlayer();
            Card topCard = game.getTopPile();

            System.out.println("\n-----------------------------");
            System.out.println("Carte du dessus : " + topCard);
            System.out.println("Tour de : " + currentPlayer.getName());

            Card playedCard = null;

            if (!rep.equals("oui") || currentPlayer.getName().equals("Joueur 1")) {
                // Tour du joueur humain
                currentPlayer.showhand();
                boolean validMove = false;

                while (!validMove) {
                    System.out.print("Choisissez une carte (index) ou -1 pour piocher : ");
                    if (scanner.hasNextInt()) {
                        int choice = scanner.nextInt();
                         scanner.nextLine();
                        if (choice == -1) {
                            currentPlayer.drawcard(game.getDeck());
                            System.out.println("Vous avez pioche une carte.");
                            validMove = true; 
                        } else {
                            if (choice >= 0 && choice < currentPlayer.getHand().size()) {
                                Card c = currentPlayer.getHand().get(choice);
                                if (c.canBePlayedOn(topCard)) {
                                    playedCard = currentPlayer.playcard(choice);
                                    validMove = true;
                                } else {
                                    System.out.println("Cette carte ne peut pas être jouee !");
                                }
                            } else {
                                System.out.println("Index invalide.");
                            }
                        }
                    } else {
                        System.out.println("Entree invalide.");
                        scanner.next(); 
                    }
                }
            } else {
                // Tour du Bot
               

                ArrayList<Card> hand = currentPlayer.getHand();
                for (int i = 0; i < hand.size(); i++) {
                    if (hand.get(i).canBePlayedOn(topCard)) {
                        playedCard = currentPlayer.playcard(i);
                        break;
                    }
                }
                if (playedCard == null) {
                    currentPlayer.drawcard(game.getDeck());
                    System.out.println(currentPlayer.getName() + " a pioche.");
                } else {
                    System.out.println(currentPlayer.getName() + " a joue " + playedCard);
                }
            }

            // Traitement de la carte jouée
            if (playedCard != null) {
                game.addToDiscardPile(playedCard);

                if(currentPlayer.hasonecard()) {
                    System.out.println(currentPlayer.getName() + "UNOOOOOOOOOOOOOOO");
                }
                // Vérification de victoire
                if (currentPlayer.hasnocard()) {
                    System.out.println("\n " + currentPlayer.getName() + " A GAGNEEEEEE ");
                    gameRunning = false;
                    break;
                }

                // Effets des cartes spéciales
                if (playedCard instanceof CardEffect) {
                    CardEffect p = (CardEffect) playedCard;
                    p.cardEffect(game);
                }
            }


            game.nextTurn();
        }
        System.out.println("Fin de la partie UNO.");
        scanner.close();
    }

}


