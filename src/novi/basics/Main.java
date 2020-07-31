package novi.basics;

import java.util.Scanner;

public class Main {
    public static Scanner PLAYERINPUT = new Scanner(System.in);

    public static void main(String[] args) {

        // -- vanaf hier gaan we het spel opnieuw spelen met andere spelers (nadat op het eind  keuze 2 gekozen is)
        boolean resetPlayers = true;
        while (resetPlayers) {
            // de 1e speler om zijn naam vragen
            System.out.println("Player 1, what is your name?");
            // de naam van de 1e speler opslaan
            String player1Name = PLAYERINPUT.next();
            Player player1 = new Player(player1Name, 'x');

            // de 2e speler om zijn naam vragen
            System.out.println("Player 2, what is your name?");
            // de naam van de 2e speler opslaan
            String player2Name = PLAYERINPUT.next();
            Player player2 = new Player(player2Name, 'o');

            boolean rematch = true;
            while (rematch) {
                Game game = new Game(player1, player2);
                game.play();

                // vragen of de spelers nog een keer willen spelen
                System.out.println("What do you want to do next?");
                //1: nog een keer spelen
                System.out.println("1: Rematch with same players");
                //2: van spelers wisselen
                System.out.println("2: Change Players");
                //3: afsluiten
                System.out.println("3: Exit");

                // speler keuze opslaan
                int choice = PLAYERINPUT.nextInt();
                switch (choice) {
                    case 2: rematch = false;
                            break;

                    case 3: rematch = false;
                            resetPlayers = false;
                            break;
                }

                // bij keuze 1: terug naar het maken van het bord
                // bij keuze 2: terug naar de start van de applicatie en het vragen van spelernamen
                // bij keuze 3: het spel en de applicatie helemaal afsluiten

            }
        }
    }
}