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

        printBoard();

        int round = 1;
        while(round <= maxRounds) {

            String activePlayerName = activePlayer.getName();
            System.out.println(activePlayerName + ", please choose a field");

            try {
                int chosenField = PLAYERINPUT.nextInt();
                int chosenIndex = chosenField - 1;

                if (setField(chosenIndex)) {

                    printBoard();

                    if (isGameWon()) {
                        winGame();
                        break;
                    } else if (isLastRound(round)) {
                        drawGame();
                    }

                    changePlayer();
                    round++;
                }
            } catch (Exception e) {
                handleError("Please choose one of the available fields (1-9)");
            }
        }
    }

    private void handleError(String message) {
        System.out.println(message);
        PLAYERINPUT.nextLine();
    }

    public boolean isGameWon() {
        int[][] winconditions = {
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
        };

        for (int[] wincondition: winconditions) {
            if( board[wincondition[0]].getToken() == board[wincondition[1]].getToken() &&
                board[wincondition[1]].getToken() == board[wincondition[2]].getToken()) {
                return true;
            }
        }
        return false;
    }

    public void winGame() {
        activePlayer.addScore();
        System.out.println(activePlayer.getName()+ " has won!");
        System.out.println("Score " + player1.getName() + ": " + player1.getScore());
        System.out.println("Score " + player2.getName() + ": " + player2.getScore());
    }

    public boolean isLastRound(int round) {
        return round == maxRounds - 1;
    }

    public void drawGame() {
        drawCount++;
        System.out.println("It's a draw!");
        System.out.println("There have been " + drawCount + " draws");
    }

    public void changePlayer() {
        if (activePlayer == player1) {
            // maak de actieve speler, speler 2
            activePlayer = player2;
        }
        // anders
        else {
            // maak de actieve speler weer speler 1
            activePlayer = player1;
        }
    }

    public boolean setField(int chosenIndex) {
        boolean result = false;
        if(chosenIndex < 9 && chosenIndex >= 0) {
            //als het veld leeg is, wanneer er geen token staat
            if (board[chosenIndex].isEmpty()) {
                // wanneer de speler een token op het bord kan plaatsen

                // de token van de actieve speler op het gekozen veld plaatsen
                char token = activePlayer.getToken();
                board[chosenIndex].setToken(token);
                result = true;
            } else {
                handleError("this field is not available, choose another");
            }
        } else {
            handleError("the chosen field does not exist, try again");
        }
        return result;
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
