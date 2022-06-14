package battleship.gui;

import battleship.model.Gameboard;
import battleship.model.ShipType;

public class GameView {

    public void printInitShip(ShipType ship) {
        System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getSize() + " cells):");
    }

    public void printHit() {
        System.out.println("You hit a ship!");
    }

    public void printMiss() {
        System.out.println("You missed!");
    }

    public void printFieldNotEmpty() {
        System.out.println("Field was already hit!");
    }

    public void printGameboardDouble(Gameboard player, Gameboard enemy) {

        printGameboard(enemy, true);
        System.out.println("---------------------");
        printGameboard(player, false);

    }

    public void printSink() {
        System.out.println("You sank a ship!");
    }

    public void printGameboard(Gameboard gameboard, boolean hideShips) {

        System.out.println();
        System.out.print(" ");
        for (int i = 1; i < 11; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < gameboard.getGameBoardArray().length; i++) {

            System.out.print((char) (i + 65) + " ");

            for (int j = 0; j < gameboard.getGameBoardArray()[0].length; j++) {

                char field = gameboard.getGameBoardArray()[i][j];
                if (hideShips && field == 'O') {
                    System.out.print("~ ");
                } else {
                    System.out.print(field + " ");
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    public void printWin() {
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public void printPrepare(String playerName) {
        System.out.println(playerName + ", place your ships on the game field");
    }

    public void printEnter() {
        System.out.println("Press Enter and pass the move to another player");
    }
}
