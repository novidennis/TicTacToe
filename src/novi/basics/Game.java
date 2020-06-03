package novi.basics;

import static novi.basics.Main.PLAYERINPUT;

public class Game {

    private Field[] board;
    private int maxRounds;

    private Player player1;
    private Player player2;

    private int drawCount;

    private Player activePlayer;

    //constructor
    public Game(Player player1, Player player2) {
        // speelbord opslaan (1 - 9)
        // uitleg: omdat we altijd met een bord starten met 9 getallen, kunnen we het Tic Tac Toe bord ook direct een waarde geven
        // zie demo project code voor de andere manier met de for loop
        //board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        board = new Field[9];

        for (int fieldIndex = 0; fieldIndex < 9; fieldIndex++) {
            board[fieldIndex] = new Field(fieldIndex + 1);
        }

        // maximale aantal rondes opslaan
        maxRounds = board.length;

        this.player1 = player1;
        this.player2 = player2;

        drawCount = 0;
        activePlayer = player1;
    }

    public void play() {

        // -- vanaf hier gaan we het spel opnieuw spelen met dezelfde spelers (nadat op het eind keuze 1 gekozen is)

        // speelbord tonen
        printBoard();

        // starten met de beurt (maximaal 9 beurten)
        for (int round = 0; round < maxRounds; round++) {

            // naam van de actieve speler opslaan
            String activePlayerName = activePlayer.getName();

            // actieve speler vragen om een veld te kiezen (1 - 9)
            System.out.println(activePlayerName + ", please choose a field");

            //setField();
            // gekozen veld van de actieve speler opslaan
            int chosenField = PLAYERINPUT.nextInt();
            int chosenIndex = chosenField - 1;

            // als het veld bestaat
            if(chosenIndex < 9 && chosenIndex >= 0) {
                //als het veld leeg is, wanneer er geen token staat
                if(board[chosenIndex].isEmpty()) {
                    // wanneer de speler een token op het bord kan plaatsen

                    // de token van de actieve speler op het gekozen veld plaatsen
                    char token = activePlayer.getToken();
                    board[chosenIndex].setToken(token);

                    // het nieuwe speelbord tonen
                    printBoard();
                    // als het spel gewonnen is
                    // tonen wie er gewonnen heeft (de actieve speler)
                    // de actieve speler krijgt een punt
                    // de scores van de spelers tonen

                    // wanneer we in de laatste beurt zijn en niemand gewonnen heeft
                    // aantal keer gelijk spel ophogen
                    // aantal keer gelijk spel tonen

                    // de beurt doorgeven aan de volgende speler (van speler wisselen)
                    // als de actieve speler, speler 1 is:
                    //changePlayer();
                    if(activePlayer == player1) {
                        // maak de actieve speler, speler 2
                        activePlayer = player2;
                    }
                    // anders
                    else {
                        // maak de actieve speler weer speler 1
                        activePlayer = player1;
                    }

                } //of al bezet is
                else {
                    maxRounds++;
                    System.out.println("this field is not available, choose another");
                }
            }
            // als het veld niet bestaat
            else {
                // het mamimale aantal beurten verhogen
                maxRounds++;
                // foutmelding tonen aan de speler
                System.out.println("the chosen field does not exist, try again");
            }



            // -- terug naar het begin van de volgende beurt

        }
    }

    public void printBoard() {
        for (int fieldIndex = 0; fieldIndex < board.length; fieldIndex++) {
            System.out.print(board[fieldIndex].getToken() + " ");
            // als we het tweede veld geprint hebben of het vijfde veld geprint hebben
            // dan gaan we naar de volgende regel
            if(fieldIndex == 2 || fieldIndex == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
