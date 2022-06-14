package battleship.model;

import java.util.Arrays;

public class Gameboard {

    private final String playerName;
    private final char[][] gameBoardArray;

    public Gameboard(String playerName) {
        this.playerName = playerName;
        gameBoardArray = new char[10][10];
        init();
    }
    private void init() {
        for (int i = 0; i < gameBoardArray.length; i++) {
            for (int j = 0; j < gameBoardArray[0].length; j++) {
                gameBoardArray[i][j] = '~';
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public char[][] getGameBoardArray() {
        return Arrays.stream(gameBoardArray)
                .map(a ->  Arrays.copyOf(a, a.length))
                .toArray(char[][]::new);
    }

    public char getField(int x, int y){
        return gameBoardArray[x][y];
    }

    public void setHit(int x, int y) {
        gameBoardArray[x][y] = 'X';
    }

    public void setMiss(int x, int y) {
        gameBoardArray[x][y] = 'M';
    }

    public void insertShipVertically(Coordinate start, Coordinate end) {

        for (int i = start.getY(); i <= end.getY(); i++) {
            gameBoardArray[start.getX()][i] = 'O';
        }
    }

    public void insertShipHorizontally(Coordinate start, Coordinate end) {

        for (int i = start.getX(); i <= end.getX(); i++) {
            gameBoardArray[i][start.getY()] = 'O';
        }
    }

    public boolean isGameboardEmptyHorizontally(Coordinate start, Coordinate end) {
        boolean answer = true;

        int lineAbove = start.getX() == 0 ? 0 : start.getX() - 1;
        int lineBelow = start.getX() == 9 ? 9 : start.getX() + 1;

        int fieldBefore = start.getY() == 0 ? 0 : start.getY() - 1;
        int fieldAfter = end.getY() == 9 ? 9 : end.getY() + 1;

        for (int j = lineAbove; j <= lineBelow; j++) {
            for (int i = fieldBefore; i <= fieldAfter; i++) {
                if (gameBoardArray[j][i] != '~') {
                    answer = false;
                    break;
                }
            }

            if (!answer) {
                break;
            }
        }
        return answer;
    }

    public boolean isGameboardEmptyVertically(Coordinate start, Coordinate end) {
        boolean answer = true;

        int lineAbove = start.getY() == 0 ? 0 : start.getY() - 1;
        int lineBelow = start.getY() == 9 ? 9 : start.getY() + 1;

        int fieldBefore = start.getX() == 0 ? 0 : start.getX() - 1;
        int fieldAfter = end.getX() == 9 ? 9 : end.getX() + 1;

        for (int j = lineAbove; j <= lineBelow; j++) {
            for (int i = fieldBefore; i <= fieldAfter; i++) {
                if (gameBoardArray[i][j] != '~') {
                    answer = false;
                    break;
                }
            }

            if (!answer) {
                break;
            }

        }

        return answer;
    }

    public boolean isWin() {
        for (char[] chars : gameBoardArray) {
            for (int j = 0; j < gameBoardArray[0].length; j++) {
                if (chars[j] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isShipSunk(Coordinate coordinate) {

        for (int i = 0; i < coordinate.getX(); i++) {
            if (gameBoardArray[i][coordinate.getY()] == '~'){
                break;
            }
            if (gameBoardArray[i][coordinate.getY()] == 'O'){
                return  false;
            }
        }

        for (int i = coordinate.getX(); i < gameBoardArray.length; i++) {
            if (gameBoardArray[i][coordinate.getY()] == '~'){
                break;
            }
            if (gameBoardArray[i][coordinate.getY()] == 'O'){
                return  false;
            }
        }

        for (int i = 0; i < coordinate.getY(); i++) {
            if (gameBoardArray[coordinate.getX()][i] == '~'){
                break;
            }
            if (gameBoardArray[coordinate.getX()][i] == 'O'){
                return  false;
            }
        }

        for (int i = coordinate.getY(); i < gameBoardArray.length; i++) {
            if (gameBoardArray[coordinate.getX()][i] == '~'){
                break;
            }
            if (gameBoardArray[coordinate.getX()][i] == 'O'){
                return  false;
            }
        }

        return true;


    }

}
