package battleship.initializer;

import battleship.model.Coordinate;
import battleship.model.Gameboard;
import battleship.model.ShipType;

public class InsertShipValidator {

    public void validateShip(Gameboard gameboard, Coordinate start, Coordinate end, ShipType shipType) {

        if (!isSizeCorrect(start, end, shipType)) {
            throw new IllegalArgumentException("Error! Wrong length of the " + shipType.getName() + "! Try again:");
        }

        if (!isShipInLine(start, end)) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }

        if (!isLocationCorrect(gameboard, start, end)) {
            throw new IllegalArgumentException("Error! You placed it too close to another one. Try again:");
        }

    }

    private boolean isShipInLine(Coordinate start, Coordinate end) {
        return start.getX() == end.getX() || start.getY() == end.getY();
    }

    private boolean isSizeCorrect(Coordinate start, Coordinate end, ShipType shipType) {

        int first;
        int second;

        if (start.getX() == end.getX()) {
            first = start.getY();
            second = end.getY();
        } else {
            first = start.getX();
            second = end.getX();
        }

        return second - first == shipType.getSize() - 1 || first - second == shipType.getSize() - 1;
    }

    private boolean isLocationCorrect(Gameboard gameboard, Coordinate start, Coordinate end) {

        if (start.getX() == end.getX()) {
            return gameboard.isGameboardEmptyHorizontally(start, end);
        } else {
            return gameboard.isGameboardEmptyVertically(start, end);
        }

    }


}
