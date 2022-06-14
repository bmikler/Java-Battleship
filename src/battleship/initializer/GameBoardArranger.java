package battleship.initializer;


import battleship.model.Coordinate;
import battleship.model.Gameboard;
import battleship.model.ShipType;

import java.util.Arrays;
import java.util.Comparator;

public class GameBoardArranger {

    private final InsertShipValidator validator;

    public GameBoardArranger(InsertShipValidator validator) {
        this.validator = validator;
    }

    public void insertBattleShip(Gameboard gameboard, Coordinate[] coordinates, ShipType ship) {

        Coordinate start = Arrays.stream(coordinates)
                .min(Comparator.comparing(c -> c.getX() + c.getY()))
                .orElseThrow(() -> new RuntimeException("Coordinate not found"));

        Coordinate end = Arrays.stream(coordinates)
                .max(Comparator.comparing(c -> c.getX() + c.getY()))
                .orElseThrow(() -> new RuntimeException("Coordinate not found"));

        validator.validateShip(gameboard, start, end, ship);

        if (start.getX() == end.getX()) {
            gameboard.insertShipVertically(start, end);
        } else {
            gameboard.insertShipHorizontally(start, end);
        }

    }






}
