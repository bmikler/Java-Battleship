package battleship;

import battleship.gui.GameView;
import battleship.gui.UserInterface;
import battleship.initializer.GameBoardArranger;
import battleship.initializer.InsertShipValidator;
import battleship.model.Gameboard;

public class Main {

    public static void main(String[] args) {
        // Write your code here

        Gameboard gameboard1 = new Gameboard("Player 1");
        Gameboard gameboard2 = new Gameboard("Player 2");

        GameLogic gameLogic = new GameLogic(gameboard1, gameboard2,
                new GameBoardArranger(new InsertShipValidator()),
                new UserInterface(),
                new GameView()
        );

        gameLogic.run();


    }
}
