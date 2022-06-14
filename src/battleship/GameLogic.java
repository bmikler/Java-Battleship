package battleship;

import battleship.gui.GameView;
import battleship.gui.UserInterface;
import battleship.initializer.GameBoardArranger;
import battleship.model.Coordinate;
import battleship.model.Gameboard;
import battleship.model.ShipType;

public class GameLogic {

    private final Gameboard firstPlayerGameBoard;
    private final Gameboard secondPlayerGameBoard;
    private final GameBoardArranger gameBoardArranger;
    private final UserInterface userInterface;
    private final GameView gameView;

    public GameLogic(Gameboard firstPlayerGameBoard, Gameboard secondPlayerGameBoard, GameBoardArranger gameBoardArranger, UserInterface userInterface, GameView gameView) {
        this.firstPlayerGameBoard = firstPlayerGameBoard;
        this.secondPlayerGameBoard = secondPlayerGameBoard;
        this.gameBoardArranger = gameBoardArranger;
        this.userInterface = userInterface;
        this.gameView = gameView;
    }

    public void run() {

        prepareGameboard(firstPlayerGameBoard);

        prepareGameboard(secondPlayerGameBoard);

        String winner = null;
        Gameboard player = firstPlayerGameBoard;
        Gameboard enemy = secondPlayerGameBoard;

        while (winner == null) {

            playerMove(player, enemy);

            if (firstPlayerGameBoard.isWin()) {
                winner = firstPlayerGameBoard.getPlayerName();
            }

            if (secondPlayerGameBoard.isWin()) {
                winner = firstPlayerGameBoard.getPlayerName();
            }

            if (player == firstPlayerGameBoard) {
                player = secondPlayerGameBoard;
                enemy = firstPlayerGameBoard;
            } else {
                player = firstPlayerGameBoard;
                enemy = secondPlayerGameBoard;
            }
        }



    }

    private void playerMove(Gameboard player, Gameboard enemy) {

        gameView.printGameboardDouble(player, enemy);

        boolean targetCorrect = false;


        while (!targetCorrect) {
            try {
                shootTarget(enemy);
                targetCorrect = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        gameView.printEnter();
        userInterface.getEnter();
    }

    private void prepareGameboard(Gameboard gameboard) {;
        gameView.printPrepare(gameboard.getPlayerName());
        gameView.printGameboard(gameboard, false);
        fullFieldUserGameBoard(gameboard);

        gameView.printEnter();
        userInterface.getEnter();

    }

    private void shootTarget(Gameboard gameboard) {

            Coordinate target = userInterface.getTargetCoordinate();
            char gameboardField = gameboard.getField(target.getX(), target.getY());

            if (gameboardField == 'O'){
                gameboard.setHit(target.getX(), target.getY());

                if(gameboard.isWin()) {
                    gameView.printWin();
                } else if (gameboard.isShipSunk(target)){
                    gameView.printSink();
                } else {
                    gameView.printHit();
                }

            }

            if (gameboardField == 'M' || gameboardField == 'X') {
                gameView.printFieldNotEmpty();
            }


            if (gameboardField == '~') {
                gameboard.setMiss(target.getX(), target.getY());
                gameView.printMiss();
            }

    }


    public void fullFieldUserGameBoard(Gameboard gameboard) {

        for (ShipType ship : ShipType.values()) {

            gameView.printInitShip(ship);

            boolean error = true;

            while (error) {

                try {
                    Coordinate[] coordinates = userInterface.getNewShipCoordinatesFromUser();
                    gameBoardArranger.insertBattleShip(gameboard, coordinates, ship);
                    gameView.printGameboard(gameboard, false);
                    error = false;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }
}
